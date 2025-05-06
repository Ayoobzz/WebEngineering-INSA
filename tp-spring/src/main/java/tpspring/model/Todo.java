package tpspring.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	protected String title;
	protected String description;
	@ElementCollection
	protected List<Category> categories;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "todolist_id")
	protected TodoList list;

	protected String owner;

	/**
	 * Temporary constructor for TP1
	 */
	public Todo(long id, String title) {
		this.id = id;
		this.title = title;
		description = "";
		categories = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", title=" + title
				+ ", description=" + description + ", categories=" + categories + "]";
	}

	public Long getId(){
		return id;

	}
	public void setId(long id){
		this.id = id;
	}
}
