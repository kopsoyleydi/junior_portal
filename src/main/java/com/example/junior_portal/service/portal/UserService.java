package com.example.junior_portal.service.portal;

import com.example.junior_portal.data.impl.inter.PermissionRepoInter;
import com.example.junior_portal.data.impl.inter.ProfileRepoInter;
import com.example.junior_portal.data.impl.inter.UserRepoInter;
import com.example.junior_portal.data.mapper.UserMapper;
import com.example.junior_portal.dtos.bodies.request.PassChange;
import com.example.junior_portal.dtos.bodies.request.RegistrationBody;
import com.example.junior_portal.dtos.dto.UserDto;
import com.example.junior_portal.model.Permission;
import com.example.junior_portal.model.User;
import com.example.junior_portal.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;


@Slf4j
public class UserService implements UserDetailsService {

    @Autowired private UserRepoInter userRepoInter;

    @Autowired private JwtTokenUtil jwtTokenUtil;

    @Autowired private ProfileRepoInter profileRepoInter;

    @Autowired private PermissionRepoInter permissionRepoInter;

    @Autowired private UserMapper userMapper;

    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepoInter.getUserByEmail(email);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("User Not found");
        }
    }

    public User getUserByEmail(String email){
        return userRepoInter.getUserByEmail(email);
    }

    public boolean check(String email){
        User user = userRepoInter.getUserByEmail(email);
        return user == null;
    }



    public User createNewUser(RegistrationBody registrationBody){
        User user = new User();
        user.setEmail(registrationBody.getEmail());
        user.setLogin(registrationBody.getUsername());
        user.setPictureLink(registrationBody.getPictureLink());
        List<Permission> permissions = new ArrayList<>();
        permissions.add(permissionRepoInter.getPermissionById(registrationBody.getRoleId()));
        user.setPermissions(permissions);
        user.setPassword(passwordEncoder.encode(registrationBody.getPassword()));
        return userRepoInter.createUser(user);
    }



    public ResponseEntity<?> changeUserPassword(PassChange passChange){
        try {
            User user = userRepoInter.getUserByEmail(passChange.getUserEmail());
            if(user!=null){
                if(passChange.getPassword().equals(passChange.getRepeatPassword())){
                    userRepoInter.updatePassword(passChange.getUserEmail(),
                            passwordEncoder.encode(passChange.getPassword()));
                    return ResponseEntity
                            .status(HttpStatus.OK).build();
                }
                else {
                    return ResponseEntity
                            .status(HttpStatus.BAD_REQUEST).build();
                }
            }
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND).build();

        }
        catch (Exception e){
            log.info("Service: UserService, method: changeUserPassword");
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> getCurrentUser(String token){
        token = token.replace("Bearer ", "");
        String email = jwtTokenUtil.extractUsername(token);
        try {

            User user = userRepoInter.getUserByEmail(email);
            UserDto userDto = userMapper.toDto(user);
            return ResponseEntity.ok(userDto);
        }
        catch (Exception e){
            log.info("Service: UserService, method: getCurrentUser");
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }

    public ResponseEntity<?> findAllUsers(){
        try{
            return ResponseEntity.ok(userRepoInter.findAllUsers());
        }
        catch (Exception e){
            log.info("Service: UserService, method: findAllUsers");
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }
}
