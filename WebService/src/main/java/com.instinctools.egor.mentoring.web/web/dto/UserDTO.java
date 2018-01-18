package com.instinctools.egor.mentoring.web.web.dto;

import java.util.Date;

public class UserDTO {
    private String name;
    private Date birthDate;

    public UserDTO(){}

    public UserDTO(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getName() {
        return name;
    }
}
