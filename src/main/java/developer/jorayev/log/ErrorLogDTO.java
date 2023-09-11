package developer.jorayev.log;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorLogDTO {

    @Enumerated(EnumType.STRING)
    private LogLevel level;
    private String source;
    private String message;

}
