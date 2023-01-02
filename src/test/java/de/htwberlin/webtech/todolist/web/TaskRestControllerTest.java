package de.htwberlin.webtech.todolist.web;


import de.htwberlin.webtech.todolist.persistence.UserEntity;
import de.htwberlin.webtech.todolist.service.TaskService;
import de.htwberlin.webtech.todolist.web.api.Task;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class TaskRestControllerTest {

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private TaskService taskService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @DisplayName("should return the found tasks")
    void getTasksFromService() throws Exception {

        //Setup
        final UserEntity userEntity = new UserEntity("vname", "nname", "email", "passwort");

        var tasks = List.of(
                new Task(1, "Abgabe", "Pullrequest stellen", Date.valueOf(LocalDate.of(2023,2,1)), userEntity),
                new Task(2, "Kino", "Avatar schauen", Date.valueOf(LocalDate.of(2023, 1,10)), userEntity)
        );
        doReturn(tasks).when(taskService).findAll();

        mockMvc.perform(get("/api/v1/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].titel").value("Abgabe"))
                .andExpect(jsonPath("$[0].inhalt").value("Pullrequest stellen"))
                .andExpect(jsonPath("$[0].datum").value(Date.valueOf(LocalDate.of(2023,2,1)).getTime()))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].titel").value("Kino"))
                .andExpect(jsonPath("$[1].inhalt").value("Avatar schauen"))
                .andExpect(jsonPath("$[1].datum").value(Date.valueOf(LocalDate.of(2023,1,10)).getTime()));
    }

    @Test
    @DisplayName("should return code 404 if given task is not found")
    void getNotExistingTaskFromService() throws Exception {

        doReturn(null).when(taskService).findById(anyLong());

        mockMvc.perform(get("/api/v1/tasks/239"))
                .andExpect(status().isNotFound());
    }
}
