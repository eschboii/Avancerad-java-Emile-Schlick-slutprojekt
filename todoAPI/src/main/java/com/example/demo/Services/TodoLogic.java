package com.example.demo.Services;

import com.example.demo.Models.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class TodoLogic {
    private ArrayList<Todo> toDo = new ArrayList<>();

    public TodoLogic() {
        toDo.add(new Todo(1, "Oliver Bukakkemon 1", "Heja"));
        toDo.add(new Todo(2, "Emile DP", "Nicodemos"));
    }

    private void setTodoId(){

    }

    public ArrayList<Todo> getTodo() {
        return toDo;
    }



}
