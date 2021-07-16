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

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_subjects",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "subject_code")}
    )
    private Set<SubjectModel> subjects = new HashSet<>();


    // EJ con CURL:
    // curl --header "Content-Type: application/json" --request POST --data '{"email":"gianca9405@gmail.com","username":"gianca","first_name":"giancarlo","last_name":"galvarini","password":"asdasd"}' http://localhost:8080/

    // Insert SQL:
    // INSERT INTO users(email, first_name, last_name, password) VALUES ('gianca9405@gmail.com', 'giancarlo', 'galvarini', 'asdasd');

    public UserModel() {
    }

    public UserModel(String email, String password, String firstName, String lastName, Set<SubjectModel> subjects) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public Set<SubjectModel> getSubjects() {
        return subjects;
    }

}