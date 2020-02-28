package com.bridgelabz.demo.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserDTO {
    @NotNull(message = "Name should not be null")
    @Length(min = 3, max = 10, message = "Name letters should be between 3 and 10")
    public String userName;
    @Pattern(regexp = "^[a-zA-Z0-9]{1,}[._+-]?[a-zA-Z0-9]{1,}@[a-zA-Z0-9]{1,}([.][a-zA-Z]{2,3}){1,2}$",message = "Email Invalidp")
    public String email;
    @NotNull(message = "Name should not be null")
    @Length(min = 3, max = 10, message = "Name letters should be between 3 and 10")
    public String password;
    public int mobile;
}
