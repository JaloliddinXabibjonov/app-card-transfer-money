package mypackage.apptransfermoney.controller;

import mypackage.apptransfermoney.payload.LoginDto;
import mypackage.apptransfermoney.payload.RegisterDto;
import mypackage.apptransfermoney.payload.template.Result;
import mypackage.apptransfermoney.security.JwtProvider;
import mypackage.apptransfermoney.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    AuthService authService;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        UserDetails userDetails = authService.loadUserByUsername(loginDto.getUsername());
        boolean matches = passwordEncoder.matches(loginDto.getPassword(), userDetails.getPassword());
        if (matches) {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(),
                    loginDto.getPassword()
            ));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return jwtProvider.generateJwtToken(loginDto.getUsername());
        }
        return "Login yoki parol xato";
    }


}
