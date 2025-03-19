package com.employee.exception;




import com.employee.dto.ErrorMessageDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorMessageDTO> handleGlobalException(Exception exception, WebRequest request){
        ErrorMessageDTO errorMessageDTO = ErrorMessageDTO.builder()
                .apiPath(request.getDescription(false))
                .message(exception.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessageDTO);
    }

    @ExceptionHandler(ResourceNotFoundBussinessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessageDTO> handleResourceNotFoundBussinessException(ResourceNotFoundBussinessException exception,WebRequest request)
    {
        ErrorMessageDTO errorMessageDTO = ErrorMessageDTO.builder()
                .apiPath(request.getDescription(false))
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now()).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessageDTO);
    }


    @ExceptionHandler(ResourceAlreadyExistsBussinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessageDTO> handleResourceAlreadyExistsBussinessException(ResourceAlreadyExistsBussinessException exception,WebRequest request)
    {
        ErrorMessageDTO errorMessageDTO = ErrorMessageDTO.builder()
                .apiPath(request.getDescription(false))
                .status(HttpStatus.BAD_REQUEST)
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now()).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessageDTO);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        Map<String ,String> map = new HashMap<>();
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();

        allErrors.forEach(error -> {
            String feildName = ((FieldError)error).getField();
            String description = error.getDefaultMessage();
            map.put(feildName,description);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }

}
