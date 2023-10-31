package com.tasnime.freelancer.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.tasnime.freelancer.entities.Domaine;
@RepositoryRestResource(path = "dom")
@CrossOrigin("http://localhost:4200/") //pour autoriser angular
public interface DomaineRepository extends JpaRepository<Domaine, Long> {

}
