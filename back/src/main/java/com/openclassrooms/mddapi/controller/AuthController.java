package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.responses.MyResponseExceptionObject;
import com.openclassrooms.mddapi.responses.MyResponseObject;
import com.openclassrooms.mddapi.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/auth")
public class AuthController {

    private IUserService userService;

    public AuthController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
        boolean isError = bindingResult.hasErrors();
        String token = null;
        if (isError || (token = userService.authenticateUser(userDto)) == null) {
            return new ResponseEntity<>(new MyResponseExceptionObject("error"), HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(new MyResponseObject(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> setUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
        boolean isError = bindingResult.hasErrors();
        String token = null;
        if (isError || (token = userService.createNewUser(userDto)) == null) {
            return new ResponseEntity<>(new HashMap<>(), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(new MyResponseObject(token));
    }
}
