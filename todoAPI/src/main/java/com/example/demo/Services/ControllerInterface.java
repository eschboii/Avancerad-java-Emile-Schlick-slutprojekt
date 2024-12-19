package com.example.demo.Services;

import com.example.demo.Models.Todo;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ControllerInterface {
    Map<Integer, Todo> getAllTodo();
    ResponseEntity<Todo> getTodoById(int id);
    ResponseEntity<Todo> getTodoByTask(String task);
    ResponseEntity<Map<Integer, Todo>> addTodo(Todo todo);
    ResponseEntity<Todo> updateTodo(int id, Todo updateTodo);
    ResponseEntity<String> deleteTodo(int id);
}
