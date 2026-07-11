package auth.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import auth.Model.UserModel;
import auth.Repository.UserRepository;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/api/user/register")
    public String registerUser(@RequestBody UserModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully!";
    }

    @PostMapping("/api/user/login")
    public boolean loginUser(@RequestBody UserModel user) {
        UserModel existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null &&
                passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

}
