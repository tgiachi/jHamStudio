package com.tgiachi.jhamstudio.api.interfaces.ui;

public interface IWidget {

    void setInternalWindow(IInternalWindowListener internalWindow);

    void setWindow(IWindowListener window);

    void initialize();

    void onClose();

    void onReady();

}
