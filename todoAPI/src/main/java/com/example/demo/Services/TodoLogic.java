package com.example.demo.Services;

import com.example.demo.Models.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TodoLogic {
    private Map<Integer, Todo> toDo = new HashMap<>();

    public TodoLogic() {
        toDo.put(1, new Todo(1, "Hjälpa Oliver", "Ibland behöver folk hjälp"));
        toDo.put(2, new Todo(2, "Skriva Readme", "Skriva en readme för slutprojektet"));
    }

    public List<Todo> getAllTodos() {
        return new ArrayList<>(toDo.values());
    }

    public boolean addTodo(Todo todo) {
        if (todo != null && todo.getId() > 0 && !toDo.containsKey(todo.getId())) {
            toDo.put(todo.getId(), todo);
            return true;
        }
        return false;
    }

    public Todo updateTodo(int id, Todo updateTodo) {
        if (toDo.containsKey(id)) {
            toDo.put(id, updateTodo);
            return updateTodo;
        }
        return null;
    }

    public boolean deleteTodo(int id) {
        if (toDo.containsKey(id)) {
            toDo.remove(id);
            return true;
        }
        return false;
    }

    public Todo getTodoById(int id) {
        return toDo.get(id);
    }

    public Todo getTodoByTask(String task) {
        for (Todo todo : toDo.values()) {
            if (todo.getTask().equalsIgnoreCase(task)) {
                return todo;
            }
        }
        return null;
    }

    public ResponseEntity<Todo> responseStatus(Todo response) {
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<List<Todo>> responseAdded(boolean added) {
        if (added) {
            return ResponseEntity.status(HttpStatus.CREATED).body(getAllTodos());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<String> responseDeleted(boolean deleted, int id) {
        if (deleted) {
            return ResponseEntity.ok("Task with ID " + id + " has been deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task with ID " + id + " was not found.");
        }
    }
}