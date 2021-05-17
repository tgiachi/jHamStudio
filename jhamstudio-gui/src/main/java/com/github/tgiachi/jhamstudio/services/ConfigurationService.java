package com.github.tgiachi.jhamstudio.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.tgiachi.jhamstudio.api.annotations.ConfigSection;
import com.tgiachi.jhamstudio.api.data.config.ConfigRoot;
import com.tgiachi.jhamstudio.api.impl.services.AbstractHamStudioService;
import com.tgiachi.jhamstudio.api.interfaces.services.IConfigurationService;
import com.tgiachi.jhamstudio.api.interfaces.services.IFileSystemService;
import com.tgiachi.jhamstudio.api.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;

@Service
public class ConfigurationService extends AbstractHamStudioService implements IConfigurationService {

    private final IFileSystemService fileSystemService;
    private final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

    private ConfigRoot configRoot;

    public ConfigurationService(IFileSystemService fileSystemService) {
        this.fileSystemService = fileSystemService;

        if (!new File(fileSystemService.buildPath("config", "hamstudio.yml")).exists()) {
            createDefaultConfig();
        }

        loadConfig();

    }

    private void loadConfig() {
        try {
            var config = fileSystemService.readFile(Paths.get("config", "hamstudio.yml").toString());

            configRoot = objectMapper.readValue(config, ConfigRoot.class);

        } catch (Exception ex) {
            logger.error("Error during load config", ex);
        }
    }

    private void createDefaultConfig() {
        var configRoot = new ConfigRoot();
        try {
            var annotations = ReflectionUtils.getAnnotation(ConfigSection.class);

            for (var annotation : annotations) {
                var object = annotation.getDeclaredConstructor(null).newInstance();
                configRoot.getConfigs().put(object.getClass().getSimpleName(), object);
            }

            fileSystemService.writeFile(Paths.get("config", "hamstudio.yml").toString(), objectMapper.writeValueAsString(configRoot));

        } catch (Exception ex) {

        }

    }

    @Override
    public <T> T getConfig(Class<T> classz) {
        try {
            var map = (HashMap<String, Object>) (T) configRoot.getConfigs().get(classz.getSimpleName());
            var yaml = objectMapper.writeValueAsString(map);

            return objectMapper.readValue(yaml, classz);
        } catch (Exception ex) {
            logger.error("Error during config: ", ex);
        }

        return null;
    }
}
