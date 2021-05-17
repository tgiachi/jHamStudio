package com.tgiachi.jhamstudio.api.impl.services;

import com.tgiachi.jhamstudio.api.interfaces.IHamStudioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public abstract class AbstractHamStudioService implements IHamStudioService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @PostConstruct
    public void start() {

    }

    @Override
    @PreDestroy
    public void stop() {

    }
}
