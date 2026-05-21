package com.example.crudoperations.controller;
import com.example.crudoperations.model.Todo;
import com.example.crudoperations.service.TodoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // 1. GET ALL TODOS
    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    // 2. GET TODO BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable Long id) {

        Todo todo = todoService.getTodoById(id);

        if (todo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Todo not found");
        }

        return ResponseEntity.ok(todo);
    }

    // 3. CREATE TODO
    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {

        Todo created = todoService.createTodo(todo);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(created);
    }

    // 4. UPDATE TODO
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTodo(
            @PathVariable Long id,
            @RequestBody Todo todo) {

        Todo updated = todoService.updateTodo(id, todo);

        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Todo not found");
        }

        return ResponseEntity.ok(updated);
    }

    // 5. DELETE TODO
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable Long id) {

        boolean deleted = todoService.deleteTodo(id);

        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Todo not found");
        }

        return ResponseEntity.ok("Todo deleted successfully");
    }
}