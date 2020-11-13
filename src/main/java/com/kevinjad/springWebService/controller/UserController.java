package com.kevinjad.springWebService.controller;

import com.kevinjad.springWebService.model.request.UserDetailsRequestModel;
import com.kevinjad.springWebService.model.response.UserRest;
import com.kevinjad.springWebService.service.UserService;
import com.kevinjad.springWebService.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getUser(){
        return "get user was called";
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetailsRequestModel){
        UserRest userRest = new UserRest();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetailsRequestModel,userDto);
        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser,userRest);
        return userRest;
    }

    @PutMapping
    public String updateUser(){
        return "update user was called";
    }
    @DeleteMapping
    public String deleteUser(){
        return "delete user was called";
    }
}
