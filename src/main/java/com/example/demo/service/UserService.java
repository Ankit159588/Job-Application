package com.example.demo.service;

import com.example.demo.DTO.request.CreateUserRequestDTO;
import com.example.demo.DTO.request.UpdateUserRequestDTO;
import com.example.demo.DTO.response.UserResponseDTO;
import com.example.demo.Repository.UserRepository;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    // constructor injection
    private final UserRepository userRepository;
    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // res, id, name, email
    public UserResponseDTO createUser(CreateUserRequestDTO dto){
        if(userRepository.existsByEmail(dto.email())){
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setEmail(dto.email());
        user.setName(dto.name());

        User saved = userRepository.save(user);

        return toResponseDTO(saved);
    }

    public UserResponseDTO getUserById(Long id){
        return toResponseDTO(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    public List<UserResponseDTO> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(this:: toResponseDTO)
                .toList();
    }

    public UserResponseDTO updateUser(Long id, UpdateUserRequestDTO dto){
        User user = userRepository.findById(id).
                orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(dto.name());
        user.setEmail(dto.email());
        return toResponseDTO(userRepository.save(user));
    }

    public void deleteUser(Long id){
        if(!userRepository.existsById(id)){
            throw new RuntimeException("User not found");
        }

        userRepository.deleteById(id);
    }

    // MAPPER TO RESPONSE
    private UserResponseDTO toResponseDTO(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

}
