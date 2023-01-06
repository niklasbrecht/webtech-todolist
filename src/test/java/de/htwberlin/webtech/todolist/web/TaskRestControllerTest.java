package de.htwberlin.webtech.todolist.web;


import de.htwberlin.webtech.todolist.persistence.UserEntity;
import de.htwberlin.webtech.todolist.service.TaskService;
import de.htwberlin.webtech.todolist.web.api.Task;
import de.htwberlin.webtech.todolist.web.api.TaskCreateRequest;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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


    // Get-Tests

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
    @DisplayName("Requesting a task that cant exist. Should return code 404.")
    void getNotExistingTaskFromService() throws Exception {

        doReturn(null).when(taskService).findById(anyLong());

        mockMvc.perform(get("/api/v1/tasks/239"))
                .andExpect(status().isNotFound());
    }

    // POST-Tests

    @Test
    @DisplayName("Posts a valid Task. should return code 200.")
    void postTask() throws Exception {

        // Set up a returnable id whenn getId() is called
        Task task = mock(Task.class);
        Long id = 444L;
        doReturn(id).when(task).getId();
        doReturn(task).when(taskService).create(any(TaskCreateRequest.class));

        final String jsonReq = """
                {
                "datum" : "2022-05-05",
                "inhalt" : "Zweites",
                "titel" : "Test28",
                "benutzer_id" : 100
                }
                """;

        mockMvc.perform(post("/api/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonReq))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Posts a Task in a wrong format. Should return BadRequest")
    void postWrongTask() throws Exception {

        final String jsonReq = "{\"titel\": \"a\", \"inhalt\":\"\", \"deadline\":\"ok\"}";

        mockMvc.perform(post("/api/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonReq))
                .andExpect(status().isBadRequest());
    }

    //JWT-Tests
    @Test
    @DisplayName("Reqeuests a task without a valid JWT. Should return code 401")
    void falseJwtLogin() throws Exception{

        final String fakeJwt = "eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ";

        mockMvc.perform(get("/api/v2/tasks")
                .header("Authorization", "Bearer " + fakeJwt))
                .andExpect(status().isUnauthorized());
        }

    @Test
    @DisplayName("Requests a task with valid JWT. Should return code 200")
    void trueJwtLogin() throws Exception{

        final String Jwt = "eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoidGVzdEBncmFkbGUuY29tIiwiZXhwIjoxOTg4Mjg5ODI2LCJpYXQiOjE2NzI2NzA2MjYsInJvbGVzIjoiIn0.sRHELDf-_u5YzqXNfELnmaEPMDahHLySDsrEJ5PIPn-eaBxjIFoaXO9il27vV-ZNN5CmVaX7ECXqLoQMjw9Dg_d5grB6d-kE6BEy0OIUubntDM0praYLKQJk-3sIPFgkTsistXZY2S3DJEfVkp8lT-BSmkBchXUZPpYsmItQ05vbkv-6zQ8Z9wgZtVirV7aHhMIh6jJr9-AOu-dDCv2Ml5qUIIIHLosLwMgruoYF5NLr3jD3Sy0VDMRGmlV-l78ALOZkA6gvpFBPXIBjXnkt_XORlyoz7-lXfwB5f8vFgD_TkrE_2y9bj3WpvP4XjpV_3VDkmpeT6FxCAgjV_AtZcQ";

        mockMvc.perform(get("/api/v2/tasks")
                .header("Authorization", "Bearer " + Jwt))
                .andExpect(status().isOk());
    }
}
