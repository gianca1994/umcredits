package com.gianca1994.umcredits.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

@Getter
@Setter
@ToString
public class UserDTO {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
}
