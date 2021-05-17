package com.tgiachi.jhamstudio.api.interfaces.services;

import com.tgiachi.jhamstudio.api.data.viewmodel.ViewControllerObject;
import com.tgiachi.jhamstudio.api.interfaces.IHamStudioService;
import com.tgiachi.jhamstudio.api.interfaces.ui.IWidget;
import javafx.stage.Stage;

import java.net.URL;

public interface IUiService extends IHamStudioService {
    void setParentStage(Stage primaryStage);

    Stage getParentStage();

    <T extends IWidget, K> ViewControllerObject<T, K> buildViewController(URL url);

    <T extends IWidget, K> ViewControllerObject<T, K> buildViewController(Class<T> controllerClass, Class<K> view) throws Exception;

    <T extends IWidget, K> void showModal(Class<T> controllerClass, Class<K> view) throws Exception;
}
