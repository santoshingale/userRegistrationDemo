package com.bridgelabz.demo.service;

import com.bridgelabz.demo.dto.LoginDTO;
import com.bridgelabz.demo.dto.UserDTO;
import com.bridgelabz.demo.model.User;

import java.util.List;

public interface IUserService {
    User addUser(UserDTO userDTO);
    List<User> getUsers();

    String logInUser(LoginDTO loginDTO);
}
