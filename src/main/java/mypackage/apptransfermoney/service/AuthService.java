package mypackage.apptransfermoney.service;

import mypackage.apptransfermoney.entity.Role;
import mypackage.apptransfermoney.entity.enums.RoleName;
import mypackage.apptransfermoney.payload.LoginDto;
import mypackage.apptransfermoney.payload.RegisterDto;
import mypackage.apptransfermoney.payload.template.Result;
import mypackage.apptransfermoney.repository.CardRepository;
import mypackage.apptransfermoney.repository.RoleRepository;
import mypackage.apptransfermoney.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    AuthenticationManager manager;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    RoleRepository roleRepository;


//    public String login(LoginDto loginDto) {
//        Authentication authenticate = manager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword() )
//        );
//        SecurityContextHolder.getContext().setAuthentication(authenticate);
//        for (User user : userList) {
//            if (loginDto.getUsername().equals(user.getUsername()) && loginDto.getPassword().equals(user.getPassword()))
//                return jwtProvider.generateJwtToken(user.getUsername());
//        }
//
//        return "null";
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> userList = new ArrayList<>(Arrays.asList(
                new User("Jaloliddin", passwordEncoder.encode("Jaloliddin") , Collections.singleton(roleRepository.findByRoleName(RoleName.OWNER.name()))),
                new User("Kamoliddin", passwordEncoder.encode("Kamoliddin"), Collections.singleton(roleRepository.findByRoleName(RoleName.USER.name()))),
                new User("Xusniddin", passwordEncoder.encode("Xusniddin"), Collections.singleton(roleRepository.findByRoleName( RoleName.EMPLOYEE_OF_BANK.name())))
        ));
        for (User user : userList) {
            if (user.getUsername().equals(username))
                return user;
        }
        throw new UsernameNotFoundException(username + " topilmadi");
    }
}

