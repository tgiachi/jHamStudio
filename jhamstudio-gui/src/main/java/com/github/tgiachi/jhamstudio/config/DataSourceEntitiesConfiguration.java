package com.github.tgiachi.jhamstudio.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan("com.tgiachi")
public class DataSourceEntitiesConfiguration {
}
