package com.ecommerce.store.controllers;

import com.ecommerce.store.dtos.ChangePasswordRequest;
import com.ecommerce.store.dtos.RegisterUserRequest;
import com.ecommerce.store.dtos.UpdateUserRequest;
import com.ecommerce.store.dtos.UserDto;
import com.ecommerce.store.mappers.UserMapper;
import com.ecommerce.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping
    public Iterable<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        var user =userRepository.findById(id).orElse(null);

        if (user == null){
            return ResponseEntity.notFound().build();
        }



        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PostMapping
    public ResponseEntity<UserDto> registerUser(@RequestBody RegisterUserRequest request, UriComponentsBuilder uriBuilder) {

        var user = userMapper.toEntity(request);
        var userDto = userMapper.toDto(user);

        userRepository.save(user);

        userDto.setId(user.getId());

        var uri = uriBuilder.path("/products/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(name="id") Long id,@RequestBody UpdateUserRequest request){
        var user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userMapper.update(request,user);
        userRepository.save(user);

        return ResponseEntity.ok(userMapper.toDto(user));
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        var user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        userRepository.delete(user);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/{id}/change-password")
    public ResponseEntity<Void>changePassword(@RequestBody ChangePasswordRequest request, @PathVariable Long id){
        var user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        if(!user.getPassword().equals(request.getOldPassword())){
            return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        user.setPassword(request.getNewPassword());
        userRepository.save(user);

        return ResponseEntity.noContent().build();

    }

}
