package developer.jorayev.log;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InfoLogDTO {

    private Timestamp times;
    private String level;
    private String source;
    private String message;

}
