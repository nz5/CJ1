package hawservice.controller;

import hawservice.model.Payload.ApiResponse;
import hawservice.model.Payload.JwtAuthenticationResponse;
import hawservice.model.Payload.LoginRequest;
import hawservice.model.Payload.SignUpRequest;
import hawservice.repository.UserRepository;
import hawservice.security.JwtTokenProvider;
import hawservice.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController
{

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserService userService;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest)
    {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest)
    {
        if (userRepository.existsByEmail(signUpRequest.getEmail()))
        {
            return new ResponseEntity(
                new ApiResponse(false, "Username is already taken!"),
                HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail()))
        {
            return new ResponseEntity(
                new ApiResponse(false, "Email Address already in use!"),
                HttpStatus.BAD_REQUEST);
        }

        // Creating user's account

        userService.createUser(signUpRequest);

        //URI location = ServletUriComponentsBuilder
        //  .fromCurrentContextPath().path("/api/users/{username}")
        //.buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.ok().body(new ApiResponse(true, "User registered successfully"));
    }
}
