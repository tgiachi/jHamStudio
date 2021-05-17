package com.tgiachi.jhamstudio.api.interfaces.services;

import com.tgiachi.jhamstudio.api.interfaces.IHamStudioService;

public interface IConfigurationService extends IHamStudioService {

    <T> T getConfig(Class<T> classz);
}
