package tpspring.service;

import org.springframework.data.jpa.repository.JpaRepository;
import tpspring.model.TodoList;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {
}
