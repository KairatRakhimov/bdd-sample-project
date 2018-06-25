package com.kairatr.bdd.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "person")
public class Person implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    @Size(min = 3, max = 20) private String firstName;
    @Size(min = 3, max = 20) private String lastName;

    public Long getId() {
        return id;
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
}
