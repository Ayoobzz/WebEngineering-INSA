package tpspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tpspring.model.Todo;

import java.util.Optional;

@Service
public class TodoServiceV2 {

    @Autowired
    private TodoCrudRepository repository;

    public Todo addTodo(final Todo todo) {
        return repository.save(todo); // ID généré automatiquement
    }

    public boolean replaceTodo(final Todo newTodo) {
        if (!repository.existsById(newTodo.getId())) return false;
        repository.save(newTodo);
        return true;
    }

    public boolean removeTodo(final long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }

    public Optional<Todo> findTodo(final long id) {
        return repository.findById(id);
    }

    public Optional<Todo> modifyTodo(final Todo partialTodo) {
        Optional<Todo> existing = repository.findById(partialTodo.getId());
        if (existing.isEmpty()) return Optional.empty();

        Todo todo = existing.get();
        if (partialTodo.getTitle() != null) todo.setTitle(partialTodo.getTitle());
        if (partialTodo.getDescription() != null) todo.setDescription(partialTodo.getDescription());
        if (partialTodo.getCategories() != null) todo.setCategories(partialTodo.getCategories());

        return Optional.of(repository.save(todo));
    }
}
