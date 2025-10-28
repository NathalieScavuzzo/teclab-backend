package com.example.demo.repository;

import com.example.demo.model.Task;
import com.example.demo.model.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
  Page<Task> findByUser_IdAndStatusAndTitleContainingIgnoreCase(Long userId, TaskStatus status, String title, Pageable pageable);
  Page<Task> findByUser_IdAndTitleContainingIgnoreCase(Long userId, String title, Pageable pageable);
  Optional<Task> findByIdAndUser_Id(Long id, Long userId);
}
