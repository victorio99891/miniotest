package pl.wiktor.minioapi.controller.handler;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.wiktor.minioapi.service.errors.MinioClientException;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class MinioApiControllerAdvice {


    @ExceptionHandler(value = MinioClientException.class)
    public ResponseEntity<ErrorMessage> handleMinioClientException(MinioClientException exception) {
        log.error("Handled exception: ", exception);
        return ResponseEntity
                .badRequest()
                .body(new ErrorMessage(HttpStatus.BAD_REQUEST, LocalDateTime.now(), exception.getMessage()));
    }

    @Data
    static class ErrorMessage {
        private final HttpStatus status;
        private final LocalDateTime timestamp;
        private final String message;

        public ErrorMessage(HttpStatus status, LocalDateTime timestamp, String message) {
            this.status = status;
            this.timestamp = timestamp;
            this.message = message;
        }
    }

}
