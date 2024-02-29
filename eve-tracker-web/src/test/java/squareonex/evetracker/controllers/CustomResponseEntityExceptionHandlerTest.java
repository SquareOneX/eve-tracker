package squareonex.evetracker.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CustomResponseEntityExceptionHandlerTest {
    @Mock
    private Exception mockException;

    @InjectMocks
    private CustomResponseEntityExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        mockException = new Exception("Test exception");
    }

    @Test
    void handleCustomException_ReturnsModelAndViewWithErrorDetails() {
        // Arrange
        String expectedViewName = "error/error";
        String expectedErrorName = mockException.getClass().getName();
        String expectedErrorMessage = mockException.getMessage();

        // Act
        ModelAndView modelAndView = exceptionHandler.handleCustomException(mockException);

        // Assert
        assertEquals(expectedViewName, modelAndView.getViewName());
        assertEquals(expectedErrorName, modelAndView.getModel().get("errorName"));
        assertEquals(expectedErrorMessage, modelAndView.getModel().get("errorMessage"));
    }
}