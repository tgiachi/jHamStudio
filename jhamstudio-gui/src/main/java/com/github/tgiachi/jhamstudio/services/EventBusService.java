package com.github.tgiachi.jhamstudio.services;

import com.tgiachi.jhamstudio.api.impl.services.AbstractHamStudioService;
import com.tgiachi.jhamstudio.api.interfaces.services.IEventBusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class EventBusService extends AbstractHamStudioService implements IEventBusService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final ApplicationEventPublisher eventPublisher;

    public EventBusService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public <T extends Serializable> void sendMessage(T message) {
        logger.debug("Publishing event type: {}", message.getClass().getSimpleName());
        eventPublisher.publishEvent(message);
    }
}
