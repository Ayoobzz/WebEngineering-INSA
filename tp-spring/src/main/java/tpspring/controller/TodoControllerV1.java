package tpspring.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tpspring.model.Todo;
import tpspring.service.TodoServiceV1;


@RestController
public class TodoControllerV1 {
    @Autowired
    private TodoServiceV1 todoService;
    private final Map<Long, Todo> todos = new HashMap<>();
    private long cpt = 0;
    public TodoControllerV1() {
        Todo todo1 = new Todo(1,"tp web");
        Todo todo2 = new Todo(2,"tp java");
        todos.put(todo1.getId(), todo1);
        todos.put(todo2.getId(), todo2);
    }

    @GetMapping(path="/v1/public/todo/todo/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Todo getTodo(@PathVariable long id) {
        Todo todo = todos.get(id);
        if (todo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found for ID: " + id);
        }
        return todo;

    }

    @PostMapping(path="/v1/public/todo/todo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void newTodo(@RequestBody Todo todo) {
        cpt = todos.keySet().stream().max(Long::compare).orElse(0L);
        todo.setId(++cpt);
        todos.put(todo.getId(), todo);
        System.out.println("New todo: " + todo);
    }

    @DeleteMapping(path="/v1/public/todo/todo/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable long id) {
        Todo todo = todos.remove(id);
        if (todo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping(path="/v1/public/todo/todo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateTodo(@RequestBody Todo todo) {
        if (todo == null || todo.getId()==null) {
            return ResponseEntity.badRequest().build();
        }
        if (!todos.containsKey(todo.getId())) {
            return ResponseEntity.notFound().build();
        }
        todos.put(todo.getId(),todo);
        return ResponseEntity.ok().build();

    }

    @PatchMapping(path="/v1/public/todo/todo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> patchTodo(@RequestBody Todo todo) {
        if (todo == null || todo.getId()==null) {
            return ResponseEntity.badRequest().build();
        }
        if (!todos.containsKey(todo.getId())) {
            return ResponseEntity.notFound().build();
        }
        Todo existingTodo = todos.get(todo.getId());
        if (todo.getTitle() != null) {
            existingTodo.setTitle(todo.getTitle());
        }
        if (todo.getDescription() != null) {
            existingTodo.setDescription(todo.getDescription());
        }
        if (todo.getCategories() != null) {
            existingTodo.setCategories(todo.getCategories());
        }

        return ResponseEntity.ok().build();
    }




}
