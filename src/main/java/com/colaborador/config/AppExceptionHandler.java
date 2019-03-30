package com.colaborador.config;

import com.colaborador.exception.ColaboradorException;
import com.colaborador.model.dto.ErroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AppExceptionHandler {

    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { ColaboradorException.class, MethodArgumentNotValidException.class, Exception.class})
    public ErroDTO handleBadRequest(HttpServletRequest request, Exception exception) {

        ErroDTO erroDTO;
        if (exception instanceof ColaboradorException) {
            ColaboradorException colaboradorException = (ColaboradorException) exception;
            erroDTO = new ErroDTO(
                    colaboradorException.getMessage(),
                    request.getMethod(),
                    request.getRequestURL().toString()

            );
        } else if (exception instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException)exception;
            erroDTO = new ErroDTO(
                    methodArgumentNotValidException.getMessage(),
                    methodArgumentNotValidException.getParameter().getParameterName(),
                    request.getRequestURL().toString(),
                    request.getMethod(),
                    methodArgumentNotValidException.getMessage()
            );
        } else {
            erroDTO = new ErroDTO(
                    exception.getMessage(),
                    request.getMethod(),
                    request.getRequestURL().toString()
                );
        }
        return erroDTO;
    }
}
