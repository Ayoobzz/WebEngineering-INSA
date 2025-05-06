package tpspring.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpspring.model.TodoList;
import tpspring.service.TodoListService;
import tpspring.controller.dto.NamedDTO;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v2/public/todolist")
public class TodoListController {

    @Autowired
    private TodoListService service;

    @GetMapping
    public ResponseEntity<List<TodoList>> getAllTodoLists() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoList> getTodoList(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TodoList> createTodoList(@RequestBody TodoList todoList) {
        TodoList saved = service.save(todoList);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/api/v2/public/todolist")
    public ResponseEntity<TodoList> createEmptyTodoList(@RequestBody NamedDTO namedDTO) {
        TodoList todoList = new TodoList();
        todoList.setName(namedDTO.name());
        todoList.setTodos(new ArrayList<>());

        TodoList saved = service.save(todoList);
        return ResponseEntity.ok(saved);
    }


    @PutMapping
    public ResponseEntity<Void> updateTodoList(@RequestBody TodoList todoList) {
        boolean updated = service.update(todoList);
        return updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoList(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }


        @PostMapping("/{todolistId}/addTodo/{todoId}")
        public ResponseEntity<TodoList> addTodoToTodoList(
                @PathVariable Long todolistId,
                @PathVariable Long todoId
        ) {
            TodoList updatedList = service.addTodoToTodoList(todoId, todolistId);
            return ResponseEntity.ok(updatedList);
        }
}
