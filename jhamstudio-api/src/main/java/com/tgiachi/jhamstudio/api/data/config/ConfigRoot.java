package com.tgiachi.jhamstudio.api.data.config;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

@Data
public class ConfigRoot implements Serializable {
    private HashMap<String, Object> configs = new HashMap<>();
}
