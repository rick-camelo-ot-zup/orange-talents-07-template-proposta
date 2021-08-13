package br.rickcm.proposta.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApiErrorHandler{

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDto handle(MethodArgumentNotValidException exception) {
        Map<String, List<String>> errorList = new HashMap<String, List<String>>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            List<String> messageList;
            if(errorList.get(e.getField()) != null){
                messageList = errorList.get(e.getField());
            }else{
                messageList = new ArrayList<>();
            }
            messageList.add(mensagem);
            errorList.put(e.getField(), messageList);
        });
        return new ErrorDto(errorList);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorDto handle(HttpMessageNotReadableException exception) {
        Map<String, List<String>> errorMap = new HashMap<String, List<String>>();
        List<String> errorList = new ArrayList<>();
        errorList.add(exception.getMessage());
        errorMap.put("error", errorList);
        return new ErrorDto(errorMap);
    }
}
