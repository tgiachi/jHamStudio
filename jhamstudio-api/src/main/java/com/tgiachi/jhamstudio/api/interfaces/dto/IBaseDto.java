package com.tgiachi.jhamstudio.api.interfaces.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface IBaseDto extends Serializable {
    Long getId();

    void setId(Long id);

    LocalDateTime getCreatedDateTime();

    void setCreatedDateTime(LocalDateTime dateTime);

    LocalDateTime getUpdatedDateTime();

    void setUpdatedDateTime(LocalDateTime dateTime);
}
