package com.github.tgiachi.jhamstudio;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class HamStudioApplication extends Application {

    private ConfigurableApplicationContext applicationContext;
    private final Logger logger = LoggerFactory.getLogger("HamStudio");

    @Override
    public void init() throws Exception {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        System.setProperty("prism.lcdtext", "false");
        logger.info("Initializing Context");
        this.applicationContext = new SpringApplicationBuilder()
                .sources(App.class)
                .build(args)
                .run(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    @Override
    public void stop() throws Exception {
        Platform.exit();
        System.exit(0);
    }
}
