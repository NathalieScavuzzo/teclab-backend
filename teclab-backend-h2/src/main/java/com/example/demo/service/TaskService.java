package com.example.demo.service;

import com.example.demo.model.Task;
import com.example.demo.model.TaskStatus;
import com.example.demo.model.User;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TaskService {

  @Autowired private TaskRepository taskRepository;

  public Page<Task> search(Long userId, TaskStatus status, String title, int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
    String q = title == null ? "" : title;
    if (status != null) {
      return taskRepository.findByUser_IdAndStatusAndTitleContainingIgnoreCase(userId, status, q, pageable);
    }
    return taskRepository.findByUser_IdAndTitleContainingIgnoreCase(userId, q, pageable);
  }

  public Task getOwned(Long id, Long userId) {
    return taskRepository.findByIdAndUser_Id(id, userId)
        .orElseThrow(() -> new NoSuchElementException("Tarea no encontrada"));
  }

  public Task create(Task t, User owner) {
    t.setId(null);
    t.setUser(owner);
    if (t.getStatus() == null) t.setStatus(TaskStatus.PENDIENTE);
    return taskRepository.save(t);
  }

  public Task update(Long id, Task data, Long userId) {
    Task current = getOwned(id, userId);
    current.setTitle(data.getTitle());
    current.setDescription(data.getDescription());
    current.setStatus(data.getStatus());
    current.setDueDate(data.getDueDate());
    return taskRepository.save(current);
  }

  public void delete(Long id, Long userId) {
    Task current = getOwned(id, userId);
    taskRepository.delete(current);
  }
}
