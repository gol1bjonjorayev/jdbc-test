package developer.jorayev.user;


import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.callGetUserByIdProcedure(id);
    }

    @PostMapping("/create/{username}/{pass}")
    public void createUser(@PathVariable String username,@PathVariable String pass){
        userService.createProcedure(username,pass);
    }

    @PostMapping("/reg-user")
    public void regUser(@RequestBody UserRegDTO regDTO){
        userService.userReg(regDTO);
    }

}
