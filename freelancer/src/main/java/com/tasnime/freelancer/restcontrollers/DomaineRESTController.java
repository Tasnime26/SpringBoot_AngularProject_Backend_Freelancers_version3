package com.tasnime.freelancer.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tasnime.freelancer.entities.Domaine;
import com.tasnime.freelancer.repos.DomaineRepository;

@RestController
@RequestMapping("/api/dom")
@CrossOrigin("*")//accesible a partire de nimporte quelle url 

public class DomaineRESTController {
	@Autowired
	DomaineRepository domaineRepository;
	@RequestMapping(method=RequestMethod.GET)
	public List<Domaine> getAllDomaines()
	{
	return domaineRepository.findAll();
	}
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public Domaine getDomaineById(@PathVariable("id") Long id) {
	return domaineRepository.findById(id).get();
	}
}
