package com.gianca1994.umcredits.model;


import javax.persistence.*;

@Entity
@Table(name = "subjects")
public class SubjectModel {

    @Id
    private Long code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private byte credits;

    @Column(nullable = false)
    private short year;


    public SubjectModel() {

    }

    public SubjectModel(Long code, String name, byte credits, short year) {
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

    public byte getCredits() {
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
