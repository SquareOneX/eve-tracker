package squareonex.evetracker.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import squareonex.evetracker.services.JobService;
import squareonex.evetrackerdata.model.Job;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class JobControllerIT {
    private final int pageSize = 10;
    @Autowired
    JobController jobController;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    JobService jobService;

    @Test
    void webPageShouldLoad() throws Exception {
        int pageNumber = 1;
        Page<Job> expected = jobService.findPaginated(PageRequest.of(pageNumber - 1, pageSize)); //backend paging is 0-indexed
        mockMvc.perform(get("/jobs?page=" + pageNumber + "&size=" + pageSize))
                .andExpect(status().isOk())
                .andExpect(model().attribute("data", expected));
    }

    @Test
    void newJob() throws Exception {
        mockMvc.perform(get("/jobs/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("jobCommand"))
                .andExpect(view().name("jobs/new"));
    }
}