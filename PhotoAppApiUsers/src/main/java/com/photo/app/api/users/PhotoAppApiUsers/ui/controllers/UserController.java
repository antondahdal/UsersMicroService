package com.photo.app.api.users.PhotoAppApiUsers.ui.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.photo.app.api.users.PhotoAppApiUsers.ui.model.CreateUserRequestModel;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	
	@GetMapping("/status/check")
	public String getUsers() {
		System.out.println("get users was called");
		return "get users was called";
	}
	
	
	@PostMapping
	public String createUser(@Valid @RequestBody CreateUserRequestModel userDetails) {
		System.out.println("create user was called Details"+ userDetails.toString());
		return "create user was called";
	}
	
}
