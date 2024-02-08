package squareonex.evetracker.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;

import java.util.List;

import static org.mockito.Mockito.*;

class PagingUtilitiesTest {
    @Mock
    private Model modelMock;
    @Mock
    private PageableService<Object> serviceMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addPageableDataToModelShouldAddExpectedAttributes() {
        int page = 1;
        int size = 5;

        when(serviceMock.findPaginated(any())).thenAnswer(invocation -> new PageImpl<>(List.of(1, 2, 3)));

        PagingUtilities.addPageableDataToModel(modelMock, serviceMock, page, size);

        verify(serviceMock, times(1)).findPaginated(PageRequest.of(0, 5));
        verify(modelMock).addAttribute(matches("data"), any(Page.class));
        verify(modelMock).addAttribute(matches("pageNumbers"), anyList());
    }
}