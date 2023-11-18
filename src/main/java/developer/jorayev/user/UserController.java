package developer.jorayev.user;


import developer.jorayev.exception.ApiRequestException;
import developer.jorayev.log.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final ErrorLogRepository errorLogRepository;
    private final InfoLogRepository infoLogRepository;

    private final UserService userService;

    public UserController(UserRepository userRepository, ErrorLogRepository errorLogRepository, InfoLogRepository infoLogRepository, UserService userService) {
        this.userRepository = userRepository;
        this.errorLogRepository = errorLogRepository;
        this.infoLogRepository = infoLogRepository;
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



    @GetMapping("/log/{id}")
    public Boolean logError(@PathVariable Integer id){
        ErrorLogDTO dto = new ErrorLogDTO();
        InfoLogDTO infoLogDTO = new InfoLogDTO();

        if (id ==1){
            dto.setMessage("Xatolik");
            dto.setSource("UserController");
            dto.setLevel(LogLevel.ERROR);
            return errorLogRepository.createErrorLog(dto);
        } else {
            infoLogDTO.setMessage("Succesfully");
            infoLogDTO.setSource("UserController");
            infoLogDTO.setLevel(LogLevel.INFO);
            return infoLogRepository.createInfoLog(infoLogDTO);
        }
    }

}
