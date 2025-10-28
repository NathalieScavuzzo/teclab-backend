package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.model.TaskStatus;
import com.example.demo.model.User;
import com.example.demo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class TaskController {

  @Autowired private TaskService taskService;

  private Long currentUserId(Authentication authentication) {
    User u = (User) authentication.getPrincipal();
    return u.getId();
  }

  @GetMapping
  public ResponseEntity<Page<Task>> list(
      @RequestParam(required = false) TaskStatus status,
      @RequestParam(required = false, defaultValue = "") String title,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      Authentication auth) {

    Page<Task> result = taskService.search(currentUserId(auth), status, title, page, size);
    return ResponseEntity.ok(result);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Task> get(@PathVariable Long id, Authentication auth) {
    return ResponseEntity.ok(taskService.getOwned(id, currentUserId(auth)));
  }

  @PostMapping
  public ResponseEntity<Task> create(@Valid @RequestBody Task task, Authentication auth) {
    User owner = (User) auth.getPrincipal();
    return ResponseEntity.ok(taskService.create(task, owner));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Task> update(@PathVariable Long id, @Valid @RequestBody Task task, Authentication auth) {
    return ResponseEntity.ok(taskService.update(id, task, currentUserId(auth)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id, Authentication auth) {
    taskService.delete(id, currentUserId(auth));
    return ResponseEntity.noContent().build();
  }
}
