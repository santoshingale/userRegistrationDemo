package com.bridgelabz.demo.service;

import com.bridgelabz.demo.dto.LoginDTO;
import com.bridgelabz.demo.dto.UserDTO;
import com.bridgelabz.demo.exception.UserException;
import com.bridgelabz.demo.model.User;
import com.bridgelabz.demo.repository.UserRepo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User addUser(UserDTO userDTO) {
        Optional<User> byEmail = userRepo.findByEmail(userDTO.email);
        if (byEmail.isPresent()) {
            throw new UserException("User already register");
        }
        userDTO.password = passwordEncoder.encode(userDTO.password);
        User user = new User(userDTO);
        User savedData = userRepo.save(user);
        return savedData;
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public String logInUser(LoginDTO loginDTO) {
        Optional<User> byEmail = userRepo.findByEmail(loginDTO.email);
        if (byEmail.isPresent() && passwordEncoder.matches(loginDTO.password, byEmail.get().getPassword())) {
            return /*JWT.create()
                    .withClaim("email", loginDTO.email)
                    .sign(Algorithm.HMAC256(System.getenv("secret")));*/
                    Jwts.builder().claim("email",loginDTO.email).setIssuedAt(new Date(System.currentTimeMillis()))
                            .setExpiration(new Date(System.currentTimeMillis() + 1000*20)).signWith(SignatureAlgorithm.HS256,System.getenv("secret")).compact();
        }
        throw new UserException("Incorrect email or password");
    }
}
