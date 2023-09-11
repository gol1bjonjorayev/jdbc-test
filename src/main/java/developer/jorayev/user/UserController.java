package developer.jorayev.user;


import developer.jorayev.exception.ApiRequestException;
import developer.jorayev.log.ErrorLogDTO;
import developer.jorayev.log.ErrorLogRepository;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.sql.Timestamp;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final ErrorLogRepository errorLogRepository;

    private final UserService userService;

    public UserController(UserRepository userRepository, ErrorLogRepository errorLogRepository, UserService userService) {
        this.userRepository = userRepository;
        this.errorLogRepository = errorLogRepository;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        throw new ApiRequestException("Api should not be not null");
//        return userService.callGetUserByIdProcedure(id);
    }

    @PostMapping("/create/{username}/{pass}")
    public void createUser(@PathVariable String username,@PathVariable String pass){
        userService.createProcedure(username,pass);
    }

    @PostMapping("/reg-user")
    public void regUser(@RequestBody UserRegDTO regDTO){
        userService.userReg(regDTO);
    }



    @GetMapping("/log-error/{id}")
    public Boolean logError(@PathVariable Integer id){
        ErrorLogDTO dto = new ErrorLogDTO();
        if (id !=1){

            throw new NotFoundException("Xatolik");
        }
        dto.setMessage("Xatolik");
        dto.setSource("UserController");
        dto.setLevel("ERROR");
        return errorLogRepository.createErrorLog(dto);
    }

}
