package com.example.demo.controller;

import com.example.demo.DTO.request.CreateUserRequestDTO;
import com.example.demo.DTO.request.UpdateUserRequestDTO;
import com.example.demo.DTO.response.UserResponseDTO;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class userController {
    private final UserService userService;

    userController(UserService userService){
        this.userService = userService;
    }

    // CREATE
    @PostMapping
    public UserResponseDTO create(@RequestBody CreateUserRequestDTO dto) {
        return userService.createUser(dto);
    }

    // READ by id
    @GetMapping("/{id}")
    public UserResponseDTO getById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // read all
    @GetMapping
    public List<UserResponseDTO> getAll(){
        return userService.getAllUsers();
    }

    // update
    @PutMapping("/{id}")
    public UserResponseDTO update(@PathVariable Long id, @RequestBody UpdateUserRequestDTO dto){
        return userService.updateUser(id, dto);
    }

    // delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
