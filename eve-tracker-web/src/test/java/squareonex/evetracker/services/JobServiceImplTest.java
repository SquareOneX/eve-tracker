package squareonex.evetracker.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import squareonex.evetrackerdata.repositories.JobRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class JobServiceImplTest {
    @InjectMocks JobServiceImpl jobService;
    @Mock
    JobRepository jobRepositoryMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        assertNotNull(jobService.findAll());

        verify(jobRepositoryMock, times(1)).findAll();
    }
}