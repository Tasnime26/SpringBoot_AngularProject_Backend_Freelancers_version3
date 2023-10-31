package com.tasnime.freelancer.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tasnime.freelancer.entities.Freelancer;
import com.tasnime.freelancer.service.FreelancerService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class FreelancerRESTController {
	@Autowired
	FreelancerService freelancerService ;
	
	@RequestMapping(path ="all",method=RequestMethod.GET)
	public List<Freelancer> getAllFreelancers() {
		return freelancerService.getAllFreelancers();
	}
	@RequestMapping(value="/getbyid/{id}",method = RequestMethod.GET)
	//@GetMapping ("/getbyid/{id}")
	public Freelancer getFreelancerById(@PathVariable("id") Long id) {
	return freelancerService.getFreelancer(id);
	 }
	@RequestMapping(path="/addfreel",method = RequestMethod.POST)
	//@PostMapping ("/addfreel")
	public Freelancer createFreelancer(@RequestBody Freelancer freelancer) {
	return freelancerService.saveFreelancer(freelancer);
	}
	@RequestMapping(path="/updatefreel", method = RequestMethod.PUT)
	//njm n3awth b putmapping
	public Freelancer updateFreelancer(@RequestBody Freelancer freelancer) {
	return freelancerService.updateFreelancer(freelancer);
	}
	@RequestMapping(value="/delfreel/{id}",method = RequestMethod.DELETE)
	public void deleteFreelancer(@PathVariable("id") Long id)
	{
		freelancerService.deleteFreelancerById(id);
	}
	@RequestMapping(value="/freelsdom/{idDom}",method = RequestMethod.GET)
	public List<Freelancer> getFreelancersByDomId(@PathVariable("idDom") Long idDom) {
	return freelancerService.findByDomaineIdDom(idDom);
	}
	@RequestMapping(value="/freelsByName/{nom}",method = RequestMethod.GET)
	public List<Freelancer> findByNomFreelancerContains(@PathVariable("nom") String nom) {
	return freelancerService.findByNomFreelancerContains(nom);
	}

}
