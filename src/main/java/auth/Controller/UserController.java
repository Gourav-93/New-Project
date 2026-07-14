package auth.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import auth.DTO.LoginRequest;
import auth.Entity.UserEntity;
import auth.Repository.UserRepository;
import auth.Security.JwtService;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/api/user/register")
    public String register(@RequestBody UserEntity user) {
        String newPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(newPassword);

        userRepository.save(user);
        return jwtService.generateToken(user.getEmail());
    }

    @PostMapping("/api/user/login")
    public String login(@RequestBody LoginRequest candidate) {
        List<UserEntity> users = userRepository.findAll();

        for (UserEntity user : users) {

            if (user.getEmail().equals(candidate.getEmail()) && passwordEncoder.matches(
                    candidate.getPassword(),
                    user.getPassword())) {
                return jwtService.generateToken(user.getEmail());
            }
        }
        return "Sorry";

    }

}
