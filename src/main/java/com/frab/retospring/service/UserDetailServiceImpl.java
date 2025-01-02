package com.frab.retospring.service;

import com.frab.retospring.dto.AuthLoginRequest;
import com.frab.retospring.dto.AuthResponse;
import com.frab.retospring.model.User;
import com.frab.retospring.repository.UserRepository;
import com.frab.retospring.utility.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // username para mi es email
        User userEntity = userRepository.findUserByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("El usuario " + username + " no existe."));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userEntity.getRoles().forEach(role -> authorityList
                .add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

        userEntity.getRoles().stream().flatMap(role -> role.getPermissionList()
                .stream()).forEach(permission -> authorityList
                .add(new SimpleGrantedAuthority(permission.getName())));


        return new org.springframework.security.core.userdetails.User(userEntity.getEmail(),
                userEntity.getPassword(), userEntity.isActive(), userEntity.isAccountNoExpired(),
                userEntity.isCredentialNoExpired(), userEntity.isAccountNoLocked(), authorityList);
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {

        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);
        User user = userRepository.findUserByEmail(username)
                .orElseThrow(() -> new BadCredentialsException("Error al actualizar el Usuario"));
        user.setToken(accessToken);
        LocalDateTime ahora = LocalDateTime.now();
        user.setModified(ahora);
        user.setLastLogin(ahora);
        userRepository.save(user);
        AuthResponse authResponse = new AuthResponse(username, "User loged succesfully",
                accessToken, true);
        return authResponse;
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException(String.format("Invalid username or password"));
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect Password");
        }

        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }
}