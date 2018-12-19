package com.chanikya.TodoApp.welcome;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("${cors.url}")
public class WelcomeController {

	@GetMapping("authenticate/{username}")
	public Welcome authenticate(@PathVariable String username) {
		return new Welcome("Welcome to Todo App " + username);
	}

}
