package store.backendpojectfinal.controller;




import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import store.backendpojectfinal.entities.AuthenticationResponse;
import store.backendpojectfinal.entities.User;
import store.backendpojectfinal.service.AuthenticationService;

@RestController
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody User request
            ) {
        return ResponseEntity.ok(authService.register(request));
    }

 @GetMapping("/login")
    public ResponseEntity<String> demo() {
        return ResponseEntity.ok("Hello from secured url");
    }

}
