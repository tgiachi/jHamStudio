package com.github.tgiachi.jhamstudio.services;

import com.tgiachi.jhamstudio.api.annotations.Window;
import com.tgiachi.jhamstudio.api.data.viewmodel.ViewControllerObject;
import com.tgiachi.jhamstudio.api.impl.services.AbstractHamStudioService;
import com.tgiachi.jhamstudio.api.interfaces.services.IUiService;
import com.tgiachi.jhamstudio.api.interfaces.ui.IWidget;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
public class UiService extends AbstractHamStudioService implements IUiService {

    private final ApplicationContext applicationContext;



    @Setter
    @Getter
    private Stage parentStage;

    public UiService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;

    }

    @Override
    public <T extends IWidget, K> ViewControllerObject<T, K> buildViewController(URL url) {
        try {
            var result = new ViewControllerObject<T, K>();
            var loader = buildFxmlLoader(url);
            result.setView(loader.load());
            result.setController(loader.getController());
            return result;
        } catch (Exception ex) {
            logger.error("Error during load window: {}:{}", url, ex.getMessage());
        }
        return null;
    }

    @Override
    public <T extends IWidget, K> ViewControllerObject<T, K> buildViewController(Class<T> controllerClass, Class<K> view) throws Exception {

        var annotation = controllerClass.getAnnotation(Window.class);

        if (annotation == null)
            throw new Exception(String.format("Class %s don't have annotation @Widget", controllerClass.getName()));

        var packageName = annotation.packageName().equals("") ? controllerClass.getPackageName() : annotation.packageName();

        URL urlToLoad = controllerClass.getResource(String.format("/%s/%s", packageName, annotation.viewName()));

        return buildViewController(urlToLoad);

    }

    @Override
    public <T extends IWidget, K> void showModal(Class<T> controllerClass, Class<K> view) throws Exception {
        var dialog = new Stage();
        var viewController = buildViewController(controllerClass, view);

        var annotation = controllerClass.getAnnotation(Window.class);

        var scene = new Scene((Parent) viewController.getView());
//        styleService.getDefaultStyleExternalUrls().forEach(s -> scene.getStylesheets().add(s));
//        scene.getStylesheets().add(styleService.getDefaultStyleFilename());
        dialog.setScene(scene);
        dialog.initOwner(parentStage);
        dialog.initModality(Modality.APPLICATION_MODAL);
        if (annotation != null) {
            if (annotation.title() != "") {
                dialog.setTitle(annotation.title());
            }
        }
        viewController.getController().setWindow(() -> {
            dialog.close();
        });
        dialog.showAndWait();
    }

    private FXMLLoader buildFxmlLoader(URL url) {
        var loader = new FXMLLoader(url);
        loader.setControllerFactory(applicationContext::getBean);
        return loader;
    }
}
