package tpspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tpspring.model.Todo;
import tpspring.model.TodoList;
import tpspring.service.TodoListRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TodoListService{

    @Autowired
    private TodoListRepository repository;
    @Autowired
    private TodoCrudRepository todoRep;

    public List<TodoList> findAll() {
        return repository.findAll();
    }

    public Optional<TodoList> findById(Long id) {
        return repository.findById(id);
    }

    public TodoList save(TodoList todoList) {
        return repository.save(todoList);
    }

    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean update(TodoList updated) {
        if (!repository.existsById(updated.getId())) return false;
        repository.save(updated);
        return true;
    }

    public TodoList addTodoToTodoList(Long todoId, Long todolistId) {
        Todo todo = todoRep.findById(todoId).orElseThrow(() -> new RuntimeException("Todo not found"));
        TodoList list = repository.findById(todolistId).orElseThrow(  () -> new RuntimeException("TodoList not found"));
        list.getTodos().add(todo);
        return repository.save(list);
    }
}
