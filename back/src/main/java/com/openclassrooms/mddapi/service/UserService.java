package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, JWTService jwtService, UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder) { //ModelMapper modelMapper,
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    public String authenticateUser(UserDto userDto) {
        String passwordNotEncoded = userDto.getPassword();
        Optional<User> user = userRepository.findByEmail(userDto.getEmail());
        if (user.isEmpty() || !this.bCryptPasswordEncoder.matches(passwordNotEncoded, user.get().getPassword())) {
            return null;
        }
        return jwtService.generateToken(user.get().getEmail());
    }

    @Override
    public String createNewUser(UserDto userDto) {
        Optional<User> userSaved = saveUser(userDto);
        return userSaved.map(user -> jwtService.generateToken(user.getEmail())).orElse(null);
    }

    @Override
    public Optional<User> saveUser(UserDto userDto) {
        String passwordEncoded = this.bCryptPasswordEncoder.encode(userDto.getPassword());
        User user =  this.userMapper.toEntity(userDto);
        user.setPassword(passwordEncoded);
        try {
            return Optional.of(userRepository.save(user));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }
}
