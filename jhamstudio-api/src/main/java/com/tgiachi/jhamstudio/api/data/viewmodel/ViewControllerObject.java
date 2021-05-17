package com.tgiachi.jhamstudio.api.data.viewmodel;

import com.tgiachi.jhamstudio.api.interfaces.ui.IWidget;
import lombok.Data;

@Data
public class ViewControllerObject<TController extends IWidget, KView> {
    TController controller;
    KView view;
}
