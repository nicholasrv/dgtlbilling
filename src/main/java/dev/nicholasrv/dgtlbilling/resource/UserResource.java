package dev.nicholasrv.dgtlbilling.resource;

import dev.nicholasrv.dgtlbilling.domain.HttpResponse;
import dev.nicholasrv.dgtlbilling.domain.User;
import dev.nicholasrv.dgtlbilling.dto.UserDTO;
import dev.nicholasrv.dgtlbilling.form.LoginForm;
import dev.nicholasrv.dgtlbilling.service.UserService;
import dev.nicholasrv.dgtlbilling.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

import java.net.URI;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserResource {
    private final UserServiceImpl userService;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public ResponseEntity<HttpResponse> login(@RequestBody @Valid LoginForm loginForm){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword()));
        UserDTO userDTO = userService.getUserByEmail(loginForm.getEmail());
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("user", userDTO))
                        .message("Login Success")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @PostMapping("/register")
    public ResponseEntity<HttpResponse> saveUser(@RequestBody @Valid User user) {
        UserDTO userDto = userService.createUser(user);
        return ResponseEntity.created(getUri()).body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("user", userDto))
                        .message("User created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build());
    }

    private URI getUri() {
        return URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/get/<userId>").toUriString());
    }
}
