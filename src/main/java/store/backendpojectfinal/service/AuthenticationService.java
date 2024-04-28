package store.backendpojectfinal.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import store.backendpojectfinal.entities.AuthenticationResponse;
import store.backendpojectfinal.entities.Token;
import store.backendpojectfinal.entities.User;
import store.backendpojectfinal.repositories.TokenRepository;
import store.backendpojectfinal.repositories.UserRepository;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UserRepository userRepository, TokenRepository tokenRepository,
                                 JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public void authenticate(UsernamePasswordAuthenticationToken authenticationToken) {
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }




    public AuthenticationResponse authenticate(User request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user =  userRepository.findByUsername(request.getUsername());
        String jwt = jwtService.generateToken(user);

        revokeAllTokenByUser(user);
        saveUserToken(jwt, user);

        return new AuthenticationResponse(jwt, "User login was successful");

    }










    public String authenticateAndGenerateToken(String username, String password) {
        try {
            System.out.println("Starting authentication for user: " + username);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            System.out.println("Authentication successful for user: " + username);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = userRepository.findByUsername(username);

            String token = jwtService.generateToken(user);
            System.out.println("Token generated for user: " + username);
            return token;
        } catch (AuthenticationException e) {
            System.out.println("Authentication failed for user: " + username);
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }



    private void revokeAllTokenByUser(User user) {
        tokenRepository.findAllByUserId(user.getId()).forEach(token -> {
            token.setLoggedOut(true);
            tokenRepository.save(token);
        });
    }

    private void saveUserToken(String jwt, User user) {
        Token token = new Token(jwt, false, user);
        tokenRepository.save(token);
    }
}
