package com.tasnime.freelancer.entities;

import java.util.List;

import jakarta.persistence.Entity; // Updated import
import jakarta.persistence.GeneratedValue; // Updated import
import jakarta.persistence.GenerationType; // Updated import
import jakarta.persistence.Id; // Updated import
import jakarta.persistence.OneToMany; // Updated import

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Domaine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDom;
    private String nomDom;
    private String descriptionDom;

    @OneToMany(mappedBy = "domaine")
    @JsonIgnore
    private List<Freelancer> freelancers;
}

