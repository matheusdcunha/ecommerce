package cloud.matheudcunha.ecommerce.controller;


import cloud.matheudcunha.ecommerce.controller.dto.CreateUserDto;
import cloud.matheudcunha.ecommerce.entity.UserEntity;
import cloud.matheudcunha.ecommerce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDto dto){

        var user = this.userService.createUser(dto);

        return ResponseEntity.created(URI.create("/users/" + user.getId())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> findById(@PathVariable UUID userId){

        var user = this.userService.findById(userId);

        return user.isPresent() ?
                ResponseEntity.ok(user.get()) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId){


        return this.userService.deleteUser(userId) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

}
