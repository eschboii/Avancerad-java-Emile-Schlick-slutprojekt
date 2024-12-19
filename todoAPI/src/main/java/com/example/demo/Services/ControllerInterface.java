package com.example.demo.Services;

import com.example.demo.Models.Todo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ControllerInterface {

    public List<Todo> getAllTodo();
    public ResponseEntity<Todo> getTodoById(@PathVariable int id);
    public ResponseEntity<Todo> getTodoByTask(@PathVariable String task);
    public ResponseEntity<List<Todo>> addTodo(@RequestBody Todo todo);
    public ResponseEntity<Todo> updateTodo(@PathVariable int id, @RequestBody Todo updateTodo);
    public ResponseEntity<String> deleteTodo(@PathVariable int id);
}
