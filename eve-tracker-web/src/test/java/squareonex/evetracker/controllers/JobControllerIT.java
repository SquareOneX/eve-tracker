package squareonex.evetracker.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import squareonex.evetracker.commands.JobCommand;
import squareonex.evetracker.services.JobService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class JobControllerIT {
    @Autowired
    JobController jobController;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    JobService jobService;

    @Test
    void webPageShouldLoad() throws Exception {
        mockMvc.perform(get("/jobs"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("jobs", jobService.findAll()));
    }

    @Test
    void newJob() throws Exception {
        mockMvc.perform(get("/jobs/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("job"))
                .andExpect(view().name("jobs/form"));
    }

    @Test
    void createJob() throws Exception {
        JobCommand command = new JobCommand();
        mockMvc.perform(post("/jobs", model().attribute("job", command)))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/jobs"));
    }
}