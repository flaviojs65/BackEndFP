package store.backendpojectfinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import store.backendpojectfinal.entities.Role;
import store.backendpojectfinal.entities.Token;
import store.backendpojectfinal.entities.User;
import store.backendpojectfinal.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final JwtService jwtService;

    public UserService(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }


    public User registerUser(User user) {

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        String token = jwtService.generateToken(user);

        List<Token> userTokens = user.getTokens();
        if (userTokens == null) {
            userTokens = new ArrayList<>();
        }

        Token newToken = new Token(token, false, user); // Crear una nueva instancia de Token utilizando el constructor existente
        userTokens.add(newToken); // Agregar el nuevo token a la lista
        user.setTokens(userTokens);

        user.setRole(Role.USER);

        return userRepository.save(user);
    }

}

