package com.gianca1994.umcredits.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "subjects")
@Data
public class Subject {

    @Id
    @Column(unique = true, updatable = false)
    private Long code;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private short credits;

    @Column(nullable = false)
    private short year;
}
