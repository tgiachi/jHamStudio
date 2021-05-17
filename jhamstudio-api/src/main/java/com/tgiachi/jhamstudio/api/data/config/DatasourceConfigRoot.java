package com.tgiachi.jhamstudio.api.data.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tgiachi.jhamstudio.api.annotations.ConfigSection;
import lombok.Data;

import java.io.Serializable;

@Data
@ConfigSection
public class DatasourceConfigRoot implements Serializable {

    @JsonProperty("datasource")
    private DatasourceConfig datasourceConfig = new DatasourceConfig();


}
