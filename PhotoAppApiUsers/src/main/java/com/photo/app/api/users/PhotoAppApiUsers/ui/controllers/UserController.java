package com.photo.app.api.users.PhotoAppApiUsers.ui.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	
	@GetMapping("/status/check")
	public String getUsers() {
		return "get users was called";
	}
	
	
}
