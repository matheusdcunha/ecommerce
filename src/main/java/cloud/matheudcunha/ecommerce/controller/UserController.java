package cloud.matheudcunha.ecommerce.controller;


import cloud.matheudcunha.ecommerce.controller.dto.CreateUserDto;
import cloud.matheudcunha.ecommerce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

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

}
