package auth.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import auth.DTO.LoginModel;
import auth.Model.UserModel;
import auth.Repository.UserRepository;
import auth.Security.JwtService;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired   
    JwtService jwtService;

    @PostMapping("/api/user/register")
    public String register(@RequestBody UserModel user) {
        String newPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(newPassword);

        userRepository.save(user);
        return jwtService.generateToken(user.getEmail());
    }

    @PostMapping("/api/user/login")
    public String login(@RequestBody LoginModel candidate) {
        List<UserModel> users = userRepository.findAll();

        for (UserModel user : users) {

            if (user.getEmail().equals(candidate.getEmail()) && passwordEncoder.matches(
                    candidate.getPassword(),
                    user.getPassword())) {
                return jwtService.generateToken(user.getEmail());
            }
        }
        return "Sorry";

    }

}
