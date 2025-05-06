package tpspring.service;

import org.springframework.stereotype.Service;
import tpspring.model.Todo;

import java.util.*;

@Service
public class TodoServiceV1 {
    private final Map<Long, Todo> todos = new HashMap<>();
    private long cpt = 0;

    public TodoServiceV1() {
        Todo todo1 = new Todo(1, "tp web");
        Todo todo2 = new Todo(2, "tp java");
        todos.put(todo1.getId(), todo1);
        todos.put(todo2.getId(), todo2);
        cpt = 2;
    }

    public Todo addTodo(final Todo todo) {
        todo.setId(++cpt);
        todos.put(todo.getId(), todo);
        return todo;
    }

    public boolean replaceTodo(final Todo newTodo) {
        if (!todos.containsKey(newTodo.getId())) return false;
        todos.put(newTodo.getId(), newTodo);
        return true;
    }

    public boolean removeTodo(final long id) {
        return todos.remove(id) != null;
    }

    public Todo modifyTodo(final Todo partialTodo) {
        Todo existing = todos.get(partialTodo.getId());
        if (existing == null) return null;

        if (partialTodo.getTitle() != null) existing.setTitle(partialTodo.getTitle());
        if (partialTodo.getDescription() != null) existing.setDescription(partialTodo.getDescription());
        if (partialTodo.getCategories() != null) existing.setCategories(partialTodo.getCategories());

        return existing;
    }

    public Todo findTodo(final long id) {
        return todos.get(id);
    }
}
