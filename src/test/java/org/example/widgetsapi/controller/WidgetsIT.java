package org.example.widgetsapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.widgetsapi.utils.WidgetTestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class WidgetsIT {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldProvideWidgetController() {
        assertEquals(1, webApplicationContext.getBeansOfType(WidgetController.class).size());
    }

    @Test
    public void shouldCreateANewWidgetWithNoZIndex() throws Exception {
        mockMvc.perform(
                post("/widget")
                        .characterEncoding(StandardCharsets.UTF_8.displayName())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(WidgetTestBuilder.createTestWidget(null, null))))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.coordinates").exists())
                .andExpect(jsonPath("$.zindex").exists())
                .andExpect(jsonPath("$.width").exists())
                .andExpect(jsonPath("$.height").exists())
                .andExpect(jsonPath("$.lastModificationDate").exists());
    }

    @Test
    public void shouldCreateANewWidgetWithZIndex() throws Exception {
        //Given
        int zIndex = 999;

        //When
        mockMvc.perform(
                post("/widget")
                        .characterEncoding(StandardCharsets.UTF_8.displayName())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(WidgetTestBuilder.createTestWidget(zIndex, null))))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.coordinates").exists())
                .andExpect(jsonPath("$.zindex").value(zIndex))
                .andExpect(jsonPath("$.width").exists())
                .andExpect(jsonPath("$.height").exists())
                .andExpect(jsonPath("$.lastModificationDate").exists());
    }
}
