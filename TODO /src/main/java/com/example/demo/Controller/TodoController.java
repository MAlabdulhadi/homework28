package com.example.demo.Controller;


import com.example.demo.Model.MyUser;
import com.example.demo.Model.Todo;
import com.example.demo.Service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/get")
    public ResponseEntity getTodos(@AuthenticationPrincipal MyUser myUser) {
        List<Todo> todoList = todoService.getTodos(myUser.getId());
        return ResponseEntity.status(200).body(todoList);
    }

    @PostMapping("/add")
    public ResponseEntity addTodo(@AuthenticationPrincipal MyUser myUser, @Valid @RequestBody Todo todo) {
        todoService.addTodo(myUser.getId(), todo);
        return ResponseEntity.status(200).body("todo added");
    }

    @PutMapping("/update/{idTodo}")
    public ResponseEntity updateTodo(@AuthenticationPrincipal MyUser myUser, @Valid @RequestBody Todo todo, @PathVariable Integer idTodo) {
        todoService.updateTodo(myUser.getId(), todo, idTodo);
        return ResponseEntity.status(200).body("done updated");
    }

    @DeleteMapping("/delete/{idTodo}")
    public ResponseEntity deleteTodo(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer idTodo) {
        todoService.deleteTodo(myUser.getId(), idTodo);
        return ResponseEntity.status(200).body("done delete!");

    }
}
