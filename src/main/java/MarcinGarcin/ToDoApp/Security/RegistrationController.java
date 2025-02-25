package MarcinGarcin.ToDoApp.Security;

import MarcinGarcin.ToDoApp.User.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class RegistrationController {


    @Autowired
    private MarcinGarcin.ToDoApp.User.UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @PostMapping(value = "/req/signup", consumes = "application/json")
    public User createUser(@RequestBody User user){
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            return null;

        }
        else{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }

    }
}
