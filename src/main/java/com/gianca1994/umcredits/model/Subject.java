package com.gianca1994.umcredits.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "subjects")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Subject {

    // @JsonIgnore
    // @Column(unique = true, updatable = false)
    // private Long id;

    @Id
    @Column(unique = true, updatable = false)
    private Long code;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int credits;

    @Column(nullable = false)
    private int year;

    public Subject(Long code, String name, int credits, int year) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Subject subject = (Subject) o;
        return code != null && Objects.equals(code, subject.code);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
