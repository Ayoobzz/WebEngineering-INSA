package tpspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpspring.model.SpecificTodo;
import tpspring.model.Todo;
import tpspring.service.TodoServiceV2;

@RestController
public class TodoControllerV2 {

    @Autowired
    private TodoServiceV2 service;

    @GetMapping("/v2/public/todo/todo/{id}")
    public SpecificTodo getTodo(@PathVariable long id) {
        //Optional<Todo> todo = service.findTodo(id);
        //
        // return todo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        return new SpecificTodo(5,"todo 5");
    }

    @PostMapping("/v2/public/todo/todo")
    public ResponseEntity<Todo> newTodo(@RequestBody Todo todo) {
        Todo saved = service.addTodo(todo);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/v2/public/todo/todo/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable long id) {
        boolean removed = service.removeTodo(id);
        return removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/v2/public/todo/todo")
    public ResponseEntity<Void> updateTodo(@RequestBody Todo todo) {
        boolean updated = service.replaceTodo(todo);
        return updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
