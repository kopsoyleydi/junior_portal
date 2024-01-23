package com.example.junior_portal.service.portal;

import com.example.junior_portal.dtos.bodies.request.AuthRequest;
import com.example.junior_portal.dtos.bodies.response.JwtResponse;
import com.example.junior_portal.dtos.bodies.request.RegistrationBody;
import com.example.junior_portal.dtos.response.CommonResponse;
import com.example.junior_portal.exception.AppError;
import com.example.junior_portal.model.User;
import com.example.junior_portal.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAuthService {

    private final UserService userService;

    private final JwtTokenUtil jwtTokenUtils;

    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> createAuthToken(AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(
                    HttpStatus.UNAUTHORIZED.value()
                    , "Неправильный логин или пароль")
                    , HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getEmail());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public ResponseEntity<?> createNewUser(RegistrationBody registrationBody) {
        if (userService.loadUserByUsername(registrationBody.getEmail()).getUsername() != null) {
            return new ResponseEntity<>(new AppError(
                    HttpStatus.UNAUTHORIZED.value()
                    , "Неправильный логин или пароль")
                    , HttpStatus.UNAUTHORIZED);
        }
        User user = userService.createNewUser(registrationBody);
        return ResponseEntity.ok(user);
    }
}
