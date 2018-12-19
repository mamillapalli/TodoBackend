package com.chanikya.TodoApp.Todo;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author ravikant Required mappings for Todo services
 */
@RestController
@CrossOrigin("${cors.url}")
public class TodoController {

	TodoService todoService;

	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}

	// to create a new Todo
	@PostMapping(path = "/Todo/{username}/create")
	public ResponseEntity<Void> createTodo(@PathVariable String username, @RequestBody Todo todo) {
		Todo createdTodo = todoService.save(todo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/[id]").buildAndExpand(createdTodo.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	// to delete a Todo
	@DeleteMapping(path = "/Todo/{username}/{id}")
	public ResponseEntity<Void> deleteToDo(@PathVariable String username, @PathVariable long id) {
		Todo todo = todoService.deleteById(id);

		if (todo != null) {
			return ResponseEntity.noContent().build();
		} else {
			// System.out.println("to do not found for id" + id);
			return ResponseEntity.notFound().build();
		}
	}

	// to retrieve a particular Todo
	@GetMapping(path = "/Todo/{username}/{id}")
	public Todo getTodo(@PathVariable String username, @PathVariable long id) {
		return todoService.findById(id);
	}

	// to retrieve a list of Todo's
	@GetMapping(path = "/Todo/{username}")
	public List<Todo> getTodos(@PathVariable String username) {
		return todoService.findAll();
	}

	// to update a particular Todo
	@PutMapping(path = "/Todo/{username}/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id,
			@RequestBody Todo todo) {
		Todo updatedTodo = todoService.save(todo);
		return new ResponseEntity<Todo>(updatedTodo, HttpStatus.OK);
	}
}
