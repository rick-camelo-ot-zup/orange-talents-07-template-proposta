package br.rickcm.proposta.error;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestControllerAdvice
public class ApiErrorHandler{

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handle(MethodArgumentNotValidException exception) {
        Collection<String> mensagens = new ArrayList<>();
        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        fieldErrors.forEach(fieldError -> {
            String message = String.format("Campo %s %s", fieldError.getField(), fieldError.getDefaultMessage());
            mensagens.add(message);
        });

        ErrorDto errorDto = new ErrorDto(mensagens);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ExceptionHandler(ApiErrorException.class)
    public ResponseEntity<ErrorDto> handleApiErroException(ApiErrorException apiErroException) {
        Collection<String> mensagens = new ArrayList<>();
        mensagens.add(apiErroException.getReason());

        ErrorDto errorDto = new ErrorDto(mensagens);
        return ResponseEntity.status(apiErroException.getHttpStatus()).body(errorDto);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorDto> handleFeignStatusException(FeignException e, HttpServletResponse response) {
        Collection<String> mensagens = new ArrayList<>();
        mensagens.add(e.getMessage());

        ErrorDto errorDto = new ErrorDto(mensagens);
        return ResponseEntity.status(e.status()).body(errorDto);
    }
}
