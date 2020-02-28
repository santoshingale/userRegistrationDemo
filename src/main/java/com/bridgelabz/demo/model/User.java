package com.bridgelabz.demo.model;

import com.bridgelabz.demo.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String userName;
    private String email;
    private String password;
    private int mobile;

    public User() {
    }

    public User(UserDTO userDTO) {
        this.userName = userDTO.userName;
        this.password = userDTO.password;
        this.mobile = userDTO.mobile;
        this.email = userDTO.email;
    }
}

