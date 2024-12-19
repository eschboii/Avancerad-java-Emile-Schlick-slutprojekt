package com.example.demo.Controllers;

import com.example.demo.Models.Todo;
import com.example.demo.Services.ControllerInterface;
import com.example.demo.Services.TodoLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/todo")
public class TodoController implements ControllerInterface {

    private final TodoLogic todologic;

    @Autowired
    public TodoController(TodoLogic todologic) {
        this.todologic = todologic;
    }

    @Override
    @PostMapping
    public ResponseEntity<Map<Integer, Todo>> addTodo(@RequestBody Todo todo) {
        boolean added = todologic.addTodo(todo);
        return todologic.responseAdded(added);
    }

    @Override
    @GetMapping
    public Map<Integer, Todo> getAllTodo() {
        return todologic.getTodo();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable int id) {
        Todo todo = todologic.getTodoById(id);
        return todologic.responseStatus(todo);
    }

    @Override
    @GetMapping("/task/{task}")
    public ResponseEntity<Todo> getTodoByTask(@PathVariable String task) {
        Todo todo = todologic.getTodoByTask(task);
        return todologic.responseStatus(todo);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable int id, @RequestBody Todo updateTodo) {
        Todo updated = todologic.updateTodo(id, updateTodo);
        return todologic.responseStatus(updated);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable int id) {
        boolean removed = todologic.deleteTodo(id);
        return todologic.responseDeleted(removed, id);
    }
}
