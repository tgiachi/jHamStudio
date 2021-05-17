package com.tgiachi.jhamstudio.api.data.config;


import lombok.Data;

import java.io.Serializable;

@Data
public class DatasourceConfig implements Serializable {
    private DatasourceType datasourceType = DatasourceType.FILE;
    private String jdbcUrl = "";
}
