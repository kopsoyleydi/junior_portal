package com.example.junior_portal.service.portal;

import com.example.junior_portal.data.impl.inter.PermissionRepoInter;
import com.example.junior_portal.data.impl.inter.UserRepoInter;
import com.example.junior_portal.dtos.bodies.AuthRequest;
import com.example.junior_portal.dtos.bodies.RegistrationBody;
import com.example.junior_portal.model.Permission;
import com.example.junior_portal.model.User;
import com.example.junior_portal.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;


@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepoInter userRepoInter;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PermissionRepoInter permissionRepoInter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepoInter.getUserByEmail(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("User Not found");
        }
    }

    public User createNewUser(RegistrationBody registrationBody){
        User user = new User();
        user.setUsername(registrationBody.getEmail());
        List<Permission> permissions = new ArrayList<>();
        permissions.add(permissionRepoInter.getPermissionById(1L));
        user.setPermissions(permissions);
        user.setPassword(passwordEncoder.encode(registrationBody.getPassword()));
        return userRepoInter.createUser(user);
    }


    public String authUser(AuthRequest authRequest){
        UserDetails userDetails = null;
        try {
            userDetails = loadUserByUsername(authRequest.getEmail());
        }catch (Exception e){
            e.printStackTrace();
        }
        assert userDetails != null;
        return jwtTokenUtil.generateToken(userDetails);
    }
}
