package com.tgiachi.jhamstudio.entities;

import com.tgiachi.jhamstudio.api.impl.entities.AbstractBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "user_profiles")
@Entity
public class UserProfile extends AbstractBaseEntity {

    @Column(length = 100)
    private String firstName;
    @Column(length = 100)
    private String lastName;

    @Column(length = 10)
    private String callSign;

    @Column(length = 40)
    private String passwordMd5;
}
