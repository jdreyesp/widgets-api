package org.example.widgetsapi.configuration;

import org.example.widgetsapi.repository.InMemoryWidgetRepository;
import org.example.widgetsapi.repository.WidgetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "application-test.properties")
public class RepositoryConfigurationIT {

    @Resource
    private WebApplicationContext webApplicationContext;

    @Test
    public void shouldRepositoryConfiguration() {
        Map<String, WidgetRepository> widgetRepositories = webApplicationContext.getBeansOfType(WidgetRepository.class);
        assertEquals(1, widgetRepositories.size());
        widgetRepositories.forEach((String key, WidgetRepository value) -> assertTrue(value instanceof InMemoryWidgetRepository));
    }
}
