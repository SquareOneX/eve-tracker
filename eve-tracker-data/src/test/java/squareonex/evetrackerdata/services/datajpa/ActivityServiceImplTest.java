package squareonex.evetrackerdata.services.datajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import squareonex.evetrackerdata.model.Activity;
import squareonex.evetrackerdata.repositories.ActivityRepository;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class ActivityServiceImplTest {
    ActivityServiceImpl activityService;
    @Mock
    ActivityRepository activityRepsoitory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        activityService = new ActivityServiceImpl(activityRepsoitory);
    }

    @Test
    void findAll() {
        Activity activity = new Activity();
        activity.setId(0);
        when(activityRepsoitory.findAll()).thenReturn(Set.of(activity));

        assertFalse(activityService.findAll().isEmpty());
        verify(activityRepsoitory, times(1)).findAll();
    }
}