package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class PortConfiguration implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    @Autowired
    private Environment environment;

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        if (environment.getProperty("PORT") != null)
            factory.setPort(Integer.parseInt(environment.getProperty("PORT")));
        else factory.setPort(8080);
    }
}

