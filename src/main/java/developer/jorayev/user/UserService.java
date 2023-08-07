package developer.jorayev.user;

import developer.jorayev.school.SchoolDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Types;
import java.util.Map;

@Service
@Slf4j
public class UserService {

    private final UserRepository repository;
    private final JdbcTemplate jdbcTemplate;

    public UserService(UserRepository repository, JdbcTemplate jdbcTemplate) {
        this.repository = repository;
        this.jdbcTemplate = jdbcTemplate;
    }


    public User callGetUserByIdProcedure(Integer id) {
        String sql = "CALL public.get_test(?, ?)";
        User user = new User();

        jdbcTemplate.execute(con -> {
            CallableStatement call = con.prepareCall(sql);

            call.setInt(1, id);
            call.registerOutParameter(2, Types.VARCHAR);
            System.out.println(call);
            return call;
        }, (CallableStatementCallback<Void>) cs -> {
            cs.execute();
            String string = cs.getString(2);
            Map<String, Object> json = repository.parseMapFromJson(string);
            user.setUsername((String) json.get("username"));
            user.setId((Integer) json.get("id"));
            user.setPassword((String) json.get("password"));
            return null;
        });
        log.info(user.toString());
        return user;
    }


    public User createProcedure(String username,String pass) {
        String sql = "CALL public.save_test(?, ?, ?)";
        User user = new User();

        jdbcTemplate.execute(con -> {
            CallableStatement call = con.prepareCall(sql);

            call.setString(1, username);
            call.setString(2, pass);
            call.registerOutParameter(3, Types.VARCHAR);
            System.out.println(call);
            return call;
        }, (CallableStatementCallback<Void>) cs -> {
            cs.execute();
            String string = cs.getString(3);
            Map<String, Object> json = repository.parseMapFromJson(string);
            user.setUsername((String) json.get("username"));
            user.setId((Integer) json.get("id"));
            user.setPassword((String) json.get("password"));
            return null;
        });
        log.info(user.toString());
        return user;
    }
    public void userReg(UserRegDTO regDTO){

        UserDTO userDTO = new UserDTO();
        userDTO.setPassword(regDTO.getPassword());
        userDTO.setUsername(regDTO.getUsername());

        SchoolDTO schoolDTO = new SchoolDTO();
        schoolDTO.setNames(regDTO.getNames());
        schoolDTO.setAddress(regDTO.getAddress());

        String sql = "CALL public.reg_user(?, ?, ?)";

        String userMapper = repository.jsonFromObject(userDTO);
        String schoolMapper = repository.jsonFromObject(schoolDTO);
        log.info(userMapper);
        log.info(schoolMapper);
        jdbcTemplate.execute(con -> {
            CallableStatement call = con.prepareCall(sql);

            call.setString(1, userMapper);
            call.setString(2, schoolMapper);
            call.registerOutParameter(3, Types.VARCHAR);
            log.info(call.toString());
            return call;
        }, (CallableStatementCallback<Void>) cs -> {
            cs.execute();
            String string = cs.getString(3);
            log.info("Successfully ==> {}", string);
            return null;
        });
    }
}
