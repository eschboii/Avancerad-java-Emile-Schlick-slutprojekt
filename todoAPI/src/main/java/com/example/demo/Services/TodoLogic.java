package com.example.demo.Services;

import com.example.demo.Models.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TodoLogic {
    private Map<Integer, Todo> toDo = new HashMap<>();

    public TodoLogic() {
        toDo.put(1, new Todo(1, "Oliver Bukakkemon 1", "Heja"));
        toDo.put(2, new Todo(2, "Emile DP", "Nicodemos"));
    }

    public Map<Integer, Todo> getTodo() {
        return toDo;
    }

    public boolean addTodo(Todo todo) {
        if (todo != null && todo.getId() > 0) {
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

    public ResponseEntity<Map<Integer, Todo>> responseAdded(boolean added) {
        if (added) {
            return ResponseEntity.status(HttpStatus.CREATED).body(getTodo());
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