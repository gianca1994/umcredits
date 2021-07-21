package com.gianca1994.umcredits.model;


import javax.persistence.*;

@Entity
@Table(name = "subjects")
public class SubjectModel {

    @Id
    @Column(unique = true, updatable = false)
    private Long code;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private short credits;

    @Column(nullable = false)
    private short year;

    public SubjectModel() {
    }

    public SubjectModel(Long code, String name, short credits, short year) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.year = year;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getCredits() {
        return credits;
    }

    public void setCredits(byte credits) {
        this.credits = credits;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

}
