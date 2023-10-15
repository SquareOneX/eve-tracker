package squareonex.evetracker.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

import static org.mockito.Mockito.*;

class AbstractCrudServiceTest<T, I> {
    AbstractCrudService<T, I> unit;
    @Mock CrudRepository<T, I> repositoryMock;
    @BeforeEach
    void beforeEach(){
        MockitoAnnotations.openMocks(this);
        this.unit = new AbstractCrudService<T, I>(repositoryMock) {
        };
    }

    @Test
    void findAllShouldCallRepository() {
        Set<T> all = unit.findAll();
        verify(repositoryMock, times(1)).findAll();
    }

    @Test
    void findByIdShouldCallRepository() {
        I id = mock();
        unit.findById(id);
        verify(repositoryMock, times(1)).findById(id);
    }
}