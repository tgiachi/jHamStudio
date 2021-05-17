package com.github.tgiachi.jhamstudio.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.tgiachi.jhamstudio.api.data.config.DatasourceConfigRoot;
import com.tgiachi.jhamstudio.api.data.config.DatasourceType;
import com.tgiachi.jhamstudio.api.interfaces.services.IConfigurationService;
import com.tgiachi.jhamstudio.api.interfaces.services.IFileSystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    Logger logger = LoggerFactory.getLogger(getClass());
    private DatasourceConfigRoot datasourceConfigRoot;
    private final IFileSystemService fileSystemService;

    public DataSourceConfiguration(IConfigurationService configurationProvider, IFileSystemService fileSystemService) {
        datasourceConfigRoot = configurationProvider.getConfig(DatasourceConfigRoot.class);
        this.fileSystemService = fileSystemService;
    }


    @Bean
    DataSource getDataSource() {
        var dataSourceBuilder = DataSourceBuilder.create();
        if (datasourceConfigRoot.getDatasourceConfig().getDatasourceType() == DatasourceType.FILE) {
            fileSystemService.createDirectory("db");

            var dbFile = fileSystemService.buildPath("db", "hamstudio.db");
            logger.info("File database (H2): {}", dbFile);
            dataSourceBuilder.url("jdbc:h2:file:" + dbFile);
            dataSourceBuilder.driverClassName("org.h2.Driver");
        }

        return dataSourceBuilder.build();
    }
}
