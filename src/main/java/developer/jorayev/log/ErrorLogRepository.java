package developer.jorayev.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

@Repository
public class ErrorLogRepository {
    private final ObjectMapper objectMapper;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ErrorLogRepository(@Qualifier("objectMapper") ObjectMapper objectMapper, JdbcTemplate jdbcTemplate) {
        this.objectMapper = objectMapper;
        this.jdbcTemplate = jdbcTemplate;
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

    public String jsonFromObject(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public Boolean createErrorLog(ErrorLogDTO errorLogDTO) {
        String sql = "{ ? = CALL service_procedure.create_error_log(?) }";
        String s = jsonFromObject(errorLogDTO);

        return jdbcTemplate.execute(con -> {
                    CallableStatement call = con.prepareCall(sql);
                    call.registerOutParameter(1, Types.BOOLEAN);
                    call.setString(2, s);
                    System.out.println(call);
                    return call;
                }, (CallableStatementCallback<Boolean>) cs -> {
                    cs.execute();
                    return cs.getBoolean(1);
                }
        );

    }


}
