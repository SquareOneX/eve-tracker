package squareonex.evetracker.controllers;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import squareonex.evetracker.commands.ItemCommand;
import squareonex.evetracker.commands.JobCommand;
import squareonex.evetracker.commands.UserCommand;
import squareonex.evetracker.converters.ItemToItemCommand;
import squareonex.evetracker.services.JobService;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.repositories.BlueprintRepository;
import squareonex.evetrackerdata.repositories.ItemRepository;

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
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private BlueprintRepository blueprintRepository;

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

        Item slasher = itemRepository.findByNameIgnoreCase("Slasher").orElseThrow();
        ItemCommand slasherCommand = new ItemToItemCommand().convert(slasher);

        Blueprint blueprint = blueprintRepository.findById(689L).orElseThrow();
        command.setItemCommand(slasherCommand);

        command.setQuantity(1L);
        command.setIsInternal(false);

        UserCommand user = new UserCommand();
        user.setName("Player");

        command.setUserCommand(user);

        mockMvc.perform(post("/jobs").flashAttr("job", command))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/jobs"));
    }
}