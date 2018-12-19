package com.chanikya.TodoApp.Todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

	static List<Todo> todos = new ArrayList<Todo>();
	private static long idCounter = 0;

	static {

		todos.add(new Todo(++idCounter, "Learn to Swim", "ravikanth", new Date(), false));
		todos.add(new Todo(++idCounter, "Become an expert at Spring", "ravikanth", new Date(), false));
		todos.add(new Todo(++idCounter, "Become an expert at Angular", "ravikanth", new Date(), false));
		todos.add(new Todo(++idCounter, "Become an expert SASS", "ravikanth", new Date(), false));

	}

	public Todo deleteById(long id) {
		Todo todo = findById(id);
		if (todo == null) {
			return null;
		}
		if (todos.remove(todo)) {
			return todo;
		}
		return null;
	}

	public List<Todo> findAll() {
		return todos;
	}

	public Todo findById(long id) {
		for (Todo todo : todos) {
			if (todo.getId() == id) {
				return todo;
			}
		}
		return null;

	}

	public Todo save(Todo todo) {
		System.out.println("to do to be deleted is " + todo.getId());
		if (todo.getId() == -1 || todo.getId() == 0) {
			todo.setId(++idCounter);
			todos.add(todo);
		} else {

			todos.remove(todo);
			todos.add(todo);
		}
		return todo;
	}
}
