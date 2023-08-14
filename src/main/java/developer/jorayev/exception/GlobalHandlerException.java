package developer.jorayev.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
@Slf4j
public class GlobalHandlerException {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException exception){

        ApiException apiException1 = new ApiException(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Asia/Tashkent")),
                "1002");
        log.info("Exception --> {}",apiException1);
        return new ResponseEntity<>(apiException1,HttpStatus.BAD_REQUEST);
    }


}
