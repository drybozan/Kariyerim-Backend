package com.example.exeptionHandlers;

/*import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AppExceptionHandler {

    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {RuntimeException.class})
    public String handleException(HttpServletRequest request, Exception ex) {
        System.err.println(request.getRequestURL() + " istegi gerceklestirilirken bir hata olustu. Hata mesaji: " + ex.getMessage());
        return "error";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public String handleNotFound(HttpServletRequest request, Exception e){
        System.err.println(request.getRequestURL() + " istegine karsilik karsilayici bulunamadi. " + "Hata mesaji: " + e.getMessage());
        return "error";
    }
}
*/
