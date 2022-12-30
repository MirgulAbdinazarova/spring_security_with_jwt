package com.peaksoft.springsecuritywithjwt;

import com.peaksoft.springsecuritywithjwt.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

      private final UserService userService;

    @GetMapping
    public List<User> findAll() {
        return  userService.findAllUsers();
    }

    @PostMapping
    public User save (@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("{userId}")
    void  deleteById (@PathVariable Long userId) {
        userService.delete(userId);

    }
}
