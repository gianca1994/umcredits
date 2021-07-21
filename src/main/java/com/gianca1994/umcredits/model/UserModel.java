package com.gianca1994.umcredits.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column()
    private float average;

    @Column()
    private short credits;

    @Column()
    private byte subjectsApproved;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_subjects",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "subject_code")}
    )
    private Set<SubjectModel> subjects = new HashSet<>();


    public UserModel() {
    }

    public UserModel(String email,
                     String password,
                     String firstName,
                     String lastName,
                     float average,
                     short credits,
                     byte subjectsApproved,
                     Set<SubjectModel> subjects) {

        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.average = average;
        this.credits = credits;
        this.subjectsApproved = subjectsApproved;
        this.subjects = subjects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public short getCredits() {
        return credits;
    }

    public void setCredits(short credits) {
        this.credits = credits;
    }

    public byte getSubjectsApproved() {
        return subjectsApproved;
    }

    public void setSubjectsApproved(byte subjectsApproved) {
        this.subjectsApproved = subjectsApproved;
    }

    public Set<SubjectModel> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<SubjectModel> subjects) {
        this.subjects = subjects;
    }
}