package com.photo.app.api.users.PhotoAppApiUsers.ui.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.photo.app.api.users.PhotoAppApiUsers.ui.Service.UserService;
import com.photo.app.api.users.PhotoAppApiUsers.ui.Shared.UserDto;
import com.photo.app.api.users.PhotoAppApiUsers.ui.model.CreateUserRequestModel;
import com.photo.app.api.users.PhotoAppApiUsers.ui.model.CreateUserResponseModel;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/status/check")
	public String getUsers() {
		System.out.println("get users was called");
		return "get users was called";
	}

	@PostMapping
	public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetails) {
		System.out.println("create user was called Details" + userDetails.toString());
		try {
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(org.modelmapper.convention.MatchingStrategies.STRICT);
			UserDto userDto = modelMapper.map(userDetails, UserDto.class);
			userService.createUser(userDto);
			CreateUserResponseModel returnValue = modelMapper.map(userDto, CreateUserResponseModel.class);
			return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}

	}

}
