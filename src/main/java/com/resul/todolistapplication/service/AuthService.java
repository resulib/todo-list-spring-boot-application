package com.resul.todolistapplication.service;

import com.resul.todolistapplication.auth.JwtService;
import com.resul.todolistapplication.dto.AuthenticationRequestDTO;
import com.resul.todolistapplication.dto.AuthenticationResponseDTO;
import com.resul.todolistapplication.dto.RegisterRequestDTO;
import com.resul.todolistapplication.entity.UserEntity;
import com.resul.todolistapplication.entity.UserRoleEnum;
import com.resul.todolistapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponseDTO register(RegisterRequestDTO request) {
        var userOptional = userRepository.findByUsername(request.getUsername());

        if (userOptional.isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }

        var user = createUser(request);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDTO.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDTO.builder()
                .token(jwtToken)
                .build();
    }

    private UserEntity createUser(RegisterRequestDTO registrationDto) {

        UserEntity user = new UserEntity();
        user.setName(registrationDto.getName());
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setUserRole(UserRoleEnum.USER);
        return userRepository.save(user);

    }
}