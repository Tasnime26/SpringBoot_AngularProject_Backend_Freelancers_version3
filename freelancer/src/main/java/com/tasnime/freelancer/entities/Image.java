package com.tasnime.freelancer.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
@Entity
@Builder //design pattern qui permet e creer des objets complexe au lieu de creer new pour creer un objet on utilise builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long idImage ;
 private String name ;
 private String type ;
 @Column( name = "IMAGE" , length = 4048576 ) //pour dire a mysql que je veut pas un tiny lob
 @Lob //blob cest un type qui contient es onnes binair
 private byte[] image; 
 //@OneToOne
 @ManyToOne
 @JoinColumn (name="FREELANCER_ID")
 @JsonIgnore
 private Freelancer freelancer;
 
}

