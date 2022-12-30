package com.peaksoft.springsecuritywithjwt;

import com.peaksoft.springsecuritywithjwt.model.User;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User save(User newUser) {
        String password = newUser.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        newUser.setPassword(encodedPassword);
        newUser.setRole(Role.CUSTOMER);
        return userRepository.save(newUser);
    }

    public void delete(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException(
                    String.format("User via id = %d not found", userId));
        }

        userRepository.deleteById(userId);
    }
}
