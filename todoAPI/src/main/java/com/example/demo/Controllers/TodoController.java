package com.example.demo.Controllers;

import com.example.demo.Models.Todo;
import com.example.demo.Services.ControllerInterface;
import com.example.demo.Services.TodoLogic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController implements ControllerInterface {
    TodoLogic todologic = new TodoLogic();

    @GetMapping
    public List<Todo> getAllTodo() {
        return todologic.getTodo();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable int id) {
        return todologic.getTodo().stream()
                .filter(todo -> todo.getId() == id)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/task/{task}")
    public ResponseEntity<Todo> getTodoByTask(@PathVariable String task) {
        return todologic.getTodo().stream()
                .filter(todo -> todo.getTask().equalsIgnoreCase(task))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<List<Todo>> addTodo(@RequestBody Todo todo) {
        if (todo != null && todo.getId() > 0) {
            todologic.getTodo().add(todo);
            return ResponseEntity.status(HttpStatus.CREATED).body(todologic.getTodo());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable int id, @RequestBody Todo updateTodo) {
        for(Todo todo : todologic.getTodo()){
            if(todo.getId() == id){
                todo.setTask(updateTodo.getTask());
                todo.setDescription(updateTodo.getDescription());

                return ResponseEntity.ok(todo);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable int id) {
        boolean removed = false;

        for(int i = 0; i<todologic.getTodo().size(); i++){
            if(todologic.getTodo().get(i).getId()==id){
                todologic.getTodo().remove(i);
                removed = true;
            }
        }

        if(removed){
            return ResponseEntity.ok("Task with ID " + id +" has been deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task with ID" + id + " was not found.");
        }
    }
}
