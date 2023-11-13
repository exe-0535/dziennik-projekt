package com.example.dziennik.model;


import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "units")
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unit_id")
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "units")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "unit")
    private List<Lesson> lessons = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
