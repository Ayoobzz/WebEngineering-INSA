package tpspring.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
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

	public Todo(long id, String title, String description, List<Category> categories, TodoList list, String owner) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.categories = categories;
		this.list = list;
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", title=" + title
				+ ", description=" + description + ", categories=" + categories + "]";
	}

	public Long getId(){
		return id;

	}


	public List<Category> getCategories() {
		return categories;
	}

	public String getDescription() {
		return description;
	}

	public String getTitle() {
		return title;
	}

	public String getOwner() {
		return owner;
	}

	public void setId(long id){
		this.id = id;
	}

	public void setDescription( String description){
		this.description=description;

	}

	public void setTitle(String title){
		this.title=title;
	}

	public void setCategories(List<Category> x){
		this.categories=x;

	}
}
