package com.gasdar.app.funbox.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value="classpath:message.properties", encoding="UTF-8")
public class OwnPropertyConfig {



}
