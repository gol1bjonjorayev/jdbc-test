package developer.jorayev;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Types;
import java.util.Map;

@Repository
@Slf4j
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate, @Qualifier("objectMapper") ObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
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
            Map<String, Object> json = parseMapFromJson(string);
            user.setUsername((String) json.get("username"));
            user.setId((Integer) json.get("id"));
            user.setPassword((String) json.get("password"));
            return null;
        });
        log.info(user.toString());
        return user;
    }

    public Map<String, Object> parseMapFromJson(String result) {
        try {
            JsonNode jsonNode = objectMapper.readTree(result);
            return objectMapper.convertValue(jsonNode, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
