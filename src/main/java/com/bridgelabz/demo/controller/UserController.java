package com.bridgelabz.demo.controller;

import com.bridgelabz.demo.dto.LoginDTO;
import com.bridgelabz.demo.dto.ResponceDTO;
import com.bridgelabz.demo.dto.UserDTO;
import com.bridgelabz.demo.model.User;
import com.bridgelabz.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/demo")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping
    public ResponseEntity<ResponceDTO> addUserRecord(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<ResponceDTO>(new ResponceDTO(bindingResult.getFieldErrors().get(0).getDefaultMessage(),
                    null), HttpStatus.BAD_REQUEST);
        }
        User user = userService.addUser(userDTO);
        ResponceDTO responceDTO = new ResponceDTO("user successfully added", user);
        return new ResponseEntity<ResponceDTO>(responceDTO, HttpStatus.OK);
    }

    @GetMapping
    public List<User> getUserRecord() {
        List<User> users = userService.getUsers();
        return users;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUserRecord(@RequestBody LoginDTO loginDTO) {
        String b = userService.logInUser(loginDTO);
        return new ResponseEntity<String>(b, HttpStatus.OK);
    }

}
