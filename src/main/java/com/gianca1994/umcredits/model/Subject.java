package com.gianca1994.umcredits.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "subjects")
@Getter
@Setter
@AllArgsConstructor
public class Subject {

    @Id
    @Column(unique = true, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int credits;

    @Column(nullable = false)
    private int year;

    public Subject() {

    }

}
