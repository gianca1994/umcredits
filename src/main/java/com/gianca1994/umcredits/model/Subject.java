package com.gianca1994.umcredits.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "subjects")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    @Id
    @Column(unique = true, updatable = false)
    private Long code;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int credits;

    @Column(nullable = false)
    private int year;

    @Column()
    private byte note;

}
