package developer.jorayev.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
public class ApiException {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime dateTime;
    private final String code;


    public ApiException(String message, HttpStatus httpStatus, ZonedDateTime dateTime, String code) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.dateTime = dateTime;
        this.code = code;
    }


}
