package com.todo.controller;

import com.todo.dto.Todo;
import com.todo.exception.InvalidTodoException;
import com.todo.exception.TodoNotFoundException;
import com.todo.mapper.TodoMapper;
import com.todo.repo.TodoRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequiredArgsConstructor
public class TodoController {

    // this is for demo purposes. remove it when using for production use.
    private static HashMap<String, String> defaultRoutes;
    static {
        defaultRoutes = new HashMap<String, String>();
        defaultRoutes.put("GET", "/todos");
        defaultRoutes.put("GET", "/todos/{id}");
        defaultRoutes.put("POST", "/todos");
        defaultRoutes.put("PUT", "/todos/{id}");
        defaultRoutes.put("DELETE", "/todos/{id}");
    }

    private final TodoRepo todoRepo;
    private final TodoMapper todoMapper;

    @GetMapping("/")
    public Map<String, String> getDefaultRoutes() {
        return defaultRoutes;
    }

    @GetMapping("/todos")
    public List<Todo> getTodos() {
        return todoRepo.findAll().stream().map(todoMapper::generate).toList();
    }

    @GetMapping("/todos/{id}")
    public Todo getTodo(@PathVariable long id) throws TodoNotFoundException {
        var todo = todoRepo.findById(id);
        if (todo.isPresent()) {
            return todoMapper.generate(todo.get());
        } else {
            throw new TodoNotFoundException();
        }
    }

    @PostMapping("/todos")
    public Todo createTodo(@RequestBody Todo todo) {
        todoRepo.save(todoMapper.generate(todo));
        return todo;
    }

    @PutMapping("/todos/{id}")
    public Todo updateTodo(@PathVariable long id, @RequestBody Todo todo)
            throws TodoNotFoundException, InvalidTodoException {
        var todoEntity = todoRepo.findById(id);
        if (todoEntity.isEmpty()) {
            throw new TodoNotFoundException();
        }
        todoEntity.get().setTask(todo.getTask());
        todoRepo.save(todoEntity.get());
        return todoMapper.generate(todoEntity.get());
    }

    @DeleteMapping("/todos/{id}")
    public void deleteTodo(@PathVariable long id) throws TodoNotFoundException {
        var todoEntity = todoRepo.findById(id);
        if (todoEntity.isEmpty()) {
            throw new TodoNotFoundException();
        }
        todoRepo.delete(todoEntity.get());
    }
}
