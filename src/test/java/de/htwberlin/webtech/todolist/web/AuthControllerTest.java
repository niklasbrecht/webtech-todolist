package de.htwberlin.webtech.todolist.web;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONString;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    String username = "test@gradle.com";
    String password = "m7kP20nQbDRi";

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser(value = "test@gradle.com", password = "m7kP20nQbDRi")
    @Test
    void testSuccessfullLogin() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/v2/auth")
                        .with(httpBasic(username, password)))
                .andExpect(status().isOk())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();

        assertNotNull(resultString);
        assertFalse(resultString.isEmpty());
    }

    @Test
    void testFailedLogin() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/v2/auth")
                        .with(httpBasic("max@muster.is", "erfunden")))
                        .andExpect(status().isUnauthorized())
                        .andReturn();

        String resultString = result.getResponse().getContentAsString();
        assertTrue(resultString.isEmpty());
    }
}
