package developer.jorayev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userRepository.callGetUserByIdProcedure(id);
    }


    @PostMapping("/create/{username}/{pass}")
    public void createUser(@PathVariable String username,@PathVariable String pass){
        userRepository.createProcedure(username,pass);
    }

}
