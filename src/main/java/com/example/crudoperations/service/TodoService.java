package com.example.crudoperations.service;
import com.example.crudoperations.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private List<Todo> todos = new ArrayList<>();

    // GET ALL
    public List<Todo> getAllTodos() {
        return todos;
    }

    // GET BY ID
    public Todo getTodoById(Long id) {
        return todos.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // CREATE
    public Todo createTodo(Todo todo) {
        todos.add(todo);
        return todo;
    }

    // UPDATE
    public Todo updateTodo(Long id, Todo updatedTodo) {

        Todo existing = getTodoById(id);

        if (existing != null) {
            existing.setTitle(updatedTodo.getTitle());
            existing.setCompleted(updatedTodo.isCompleted());
        }

        return existing;
    }

    // DELETE
    public boolean deleteTodo(Long id) {

        Todo todo = getTodoById(id);

        if (todo != null) {
            todos.remove(todo);
            return true;
        }

        return false;
    }
}