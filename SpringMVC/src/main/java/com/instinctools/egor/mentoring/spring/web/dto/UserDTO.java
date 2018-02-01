package com.instinctools.egor.mentoring.spring.web.dto;

import com.instinctools.egor.mentoring.spring.core.entity.User;

import java.util.Date;

public class UserDTO {
    private String name;
    private Date birthDate;

    public UserDTO() {
    }

    public UserDTO(final String name, final Date birthDate) {
        this.name = name;
        this.birthDate = new Date(birthDate.getTime());
    }

    public Date getBirthDate() {
        return new Date(birthDate.getTime());
    }

    public String getName() {
        return name;
    }

    public User toModel() {
        User user = new User(name, birthDate);
        return user;
    }
}
