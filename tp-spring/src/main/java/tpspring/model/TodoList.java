package tpspring.model;

import java.util.ArrayList;
import java.util.List;

import lombok.*;
import jakarta.persistence.*;
@Entity
public class TodoList {
	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@OneToMany(mappedBy = "list", cascade = CascadeType.ALL)
	private List<Todo> todos;
	private String owner;

	public TodoList() {
		super();
		todos = new ArrayList<>();
	}

	public TodoList(final String name) {
		super();
		this.name = name;
		todos = new ArrayList<>();
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public List<Todo> getTodos() {
		return todos;
	}

	public Long getId(){
		return id;
	}




}
