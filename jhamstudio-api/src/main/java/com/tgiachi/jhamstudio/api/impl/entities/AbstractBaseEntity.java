package com.tgiachi.jhamstudio.api.impl.entities;

import com.tgiachi.jhamstudio.api.interfaces.entities.IBaseEntity;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class AbstractBaseEntity implements IBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    LocalDateTime createdDateTime;

    LocalDateTime updatedDateTime;
}
