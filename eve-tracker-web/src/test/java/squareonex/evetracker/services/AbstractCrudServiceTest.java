package squareonex.evetracker.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AbstractCrudServiceTest<T, I> {
    AbstractCrudService<T, I> unit;
    @Mock CrudRepository<T, I> repositoryMock;
    @BeforeEach
    protected void setUp(){
        MockitoAnnotations.openMocks(this);
        this.unit = new AbstractCrudService<T, I>(repositoryMock) {
            @Override
            public Set<T> findAll() {
                return super.findAll();
            }
        };
    }

    @Test
    void findAllShouldCallRepository() {
        Set<T> all = unit.findAll();
        verify(repositoryMock, times(1)).findAll();
    }
}