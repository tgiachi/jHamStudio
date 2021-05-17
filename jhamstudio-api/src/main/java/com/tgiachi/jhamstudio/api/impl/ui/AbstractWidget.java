package com.tgiachi.jhamstudio.api.impl.ui;

import com.tgiachi.jhamstudio.api.interfaces.ui.IInternalWindowListener;
import com.tgiachi.jhamstudio.api.interfaces.ui.IWidget;
import com.tgiachi.jhamstudio.api.interfaces.ui.IWindowListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractWidget implements IWidget {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private IInternalWindowListener internalWindowListener;
    private IWindowListener windowListener;

    @Override
    public void setInternalWindow(IInternalWindowListener internalWindow) {
        internalWindowListener = internalWindow;
    }

    @Override
    public void setWindow(IWindowListener window) {
        this.windowListener = window;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void onClose() {

    }

    @Override
    public void onReady() {

    }
}
