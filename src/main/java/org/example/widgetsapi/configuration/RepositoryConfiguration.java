package org.example.widgetsapi.configuration;

import org.example.widgetsapi.repository.WidgetRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.InvocationTargetException;

@Configuration
public class RepositoryConfiguration {

    @Value("${widget.repository.class}")
    private Class<WidgetRepository> widgetRepositoryClazz;

    @Bean
    public WidgetRepository getRepository() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return widgetRepositoryClazz.getDeclaredConstructor().newInstance();
    }
}
