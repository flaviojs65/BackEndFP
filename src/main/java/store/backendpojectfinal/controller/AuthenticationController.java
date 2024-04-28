package store.backendpojectfinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import store.backendpojectfinal.dto.LoginRequest;
import store.backendpojectfinal.entities.AuthenticationResponse;
import store.backendpojectfinal.entities.Token;
import store.backendpojectfinal.entities.User;
import store.backendpojectfinal.repositories.UserRepository;
import store.backendpojectfinal.service.AuthenticationService;
import store.backendpojectfinal.service.JwtService;
import store.backendpojectfinal.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


@RestController
public class AuthenticationController {

    private final AuthenticationService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public AuthenticationController(AuthenticationService authService , UserService userService , JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }


    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }



    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User does not exist.");
        }

        boolean isPasswordMatch = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
        if (!isPasswordMatch) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password.");
        }

        // Aquí, implementa la lógica para generar un token JWT o cualquier otro mecanismo de autenticación
        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(new Token(token , false , user));
    }

}

