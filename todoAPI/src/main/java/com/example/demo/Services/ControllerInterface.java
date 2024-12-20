package com.example.demo.Services;

import com.example.demo.Models.Todo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ControllerInterface {
    ResponseEntity<List<Todo>> getAllTodo();
    ResponseEntity<Todo> getTodoById(int id);
    ResponseEntity<Todo> getTodoByTask(String task);
    ResponseEntity<List<Todo>> addTodo(Todo todo);
    ResponseEntity<Todo> updateTodo(int id, Todo updateTodo);
    ResponseEntity<String> deleteTodo(int id);
}
