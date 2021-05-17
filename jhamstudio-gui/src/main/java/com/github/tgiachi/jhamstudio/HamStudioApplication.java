package com.github.tgiachi.jhamstudio;

import com.github.tgiachi.jhamstudio.windows.MainWindow;
import com.tgiachi.jhamstudio.api.interfaces.services.IUiService;
import com.tgiachi.jhamstudio.api.utils.ReflectionUtils;
import com.tgiachi.jhamstudio.entities.UserProfile;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.persistence.Entity;

public class HamStudioApplication extends Application {

    private ConfigurableApplicationContext applicationContext;
    private IUiService uiService;
    private final Logger logger = LoggerFactory.getLogger("HamStudio");

    @Override
    public void init() throws Exception {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        System.setProperty("prism.lcdtext", "false");
        logger.info("Initializing Context");
        this.applicationContext = new SpringApplicationBuilder()
                .sources(App.class)
                .sources(ReflectionUtils.getAnnotationArray(Entity.class))
                .build(args)
                .run(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        uiService =  applicationContext.getBean(IUiService.class);
        uiService.start();
        var vc = uiService.buildViewController(MainWindow.class, Parent.class);
        uiService.setParentStage(primaryStage);
        var scene = new Scene(vc.getView());

        primaryStage.setWidth(Screen.getPrimary().getBounds().getWidth());
        primaryStage.setHeight(Screen.getPrimary().getBounds().getHeight());
        primaryStage.setMaximized(true);

        primaryStage.setScene(scene);


        primaryStage.show();


    }

    @Override
    public void stop() throws Exception {
        applicationContext.close();
        Platform.exit();
        System.exit(0);
    }
}
