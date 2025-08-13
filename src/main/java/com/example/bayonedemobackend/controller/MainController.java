package com.example.bayonedemobackend.controller;


import com.example.bayonedemobackend.model.Tasks;
import com.example.bayonedemobackend.repository.TaskRepository;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MainController {


    @Autowired
    private TaskRepository task;


    @PostMapping("/BayOne/felipe/newTask")
    public ResponseEntity<Tasks> addTask(@RequestBody Tasks newTask) {
        Tasks saved = task.save(newTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


    @GetMapping("/BayOne/felipe/getTasks")
    public List<Tasks> getUsers() {
        return task.getAllTasks();
    }

    @GetMapping("/BayOne/felipe/getTasks/{email}")
    public List<Tasks> getUserByEmail(@PathVariable("email") String email) {
        return task.getAllTasksFilteredByEmail(email);
    }

    //Get token
    private String getJWTToken(String username) {
        String secretKey = "sgkjfhdskfheurygjbzcmnqweiurhskjdghkajjalskjhfowiehgin";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        JwtBuilder builder = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (60*1000*60)))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes());

        return "Bearer " + builder.compact();
    }
}
