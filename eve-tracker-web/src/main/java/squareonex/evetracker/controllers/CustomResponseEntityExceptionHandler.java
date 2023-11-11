package squareonex.evetracker.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView handleCustomException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error/error");
        modelAndView.addObject("errorName", ex.getClass().getName());
        modelAndView.addObject("errorMessage", ex.getMessage());

        return modelAndView;
    }
}
