package com.tgiachi.jhamstudio.api.interfaces.services;

import com.tgiachi.jhamstudio.api.interfaces.IHamStudioService;

import java.io.Serializable;

/**
 * Service for send message through Event Bus
 */
public interface IEventBusService extends IHamStudioService {

    /**
     * Send message
     * @param message message must be a serializable
     * @param <T> generic class
     */
    <T extends Serializable> void sendMessage(T message);

}
