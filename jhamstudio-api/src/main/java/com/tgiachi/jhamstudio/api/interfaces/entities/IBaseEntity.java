package com.tgiachi.jhamstudio.api.interfaces.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface IBaseEntity extends Serializable {

    Long getId();

    void setId(Long id);

    LocalDateTime getCreatedDateTime();

    void setCreatedDateTime(LocalDateTime dateTime);

    LocalDateTime getUpdatedDateTime();

    void setUpdatedDateTime(LocalDateTime dateTime);


}
