package com.tasnime.freelancer.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.tasnime.freelancer.repos.ImageRepository;
import com.tasnime.freelancer.entities.Domaine;
import com.tasnime.freelancer.entities.Freelancer;
import com.tasnime.freelancer.repos.DomaineRepository;
import com.tasnime.freelancer.repos.FreelancerRepository;
@Service

public class FreelancerServiceImpl implements FreelancerService{
	@Autowired
	FreelancerRepository freelancerRepository;
	@Autowired
	DomaineRepository domaineRepository;
	@Autowired
	ImageRepository imageRepository;
	@Override
	public Freelancer saveFreelancer(Freelancer f) {
	return freelancerRepository.save(f);
	}
	/*@Override
	public Freelancer updateFreelancer(Freelancer f) {
	return freelancerRepository.save(f);
	}*/
	
	@Override
	public Freelancer updateFreelancer(Freelancer f) {
	/*Long oldProdImageId = 
	this.getFreelancer(f.getIdFreelancer()).getImage().getIdImage();
	Long newProdImageId = f.getImage().getIdImage();
	
	if (oldProdImageId != newProdImageId) //si l'image a été modifiée
	imageRepository.deleteById(oldProdImageId);*/
		Freelancer freelUpdated = freelancerRepository.save(f);
	return freelUpdated;
	}
	@Override
	public void deleteFreelancer(Freelancer f) {
		
		freelancerRepository.delete(f);
	}
	@Override
	public void deleteFreelancerById(Long id) {
		//suuprimer l'image avant de supprimer le produit
		Freelancer f = getFreelancer(id);
		try {
			Files.delete(Paths.get(System.getProperty("user.home")+"/images/"+f.getImagePath()));
			} catch (IOException e) {
			e.printStackTrace();
			}
		freelancerRepository.deleteById(id);
	}
	@Override
	public Freelancer getFreelancer(Long id) {
	return freelancerRepository.findById(id).get();
	}
	@Override
	public List<Freelancer> getAllFreelancers() {
	return freelancerRepository.findAll();
	}
	@Override
	public Page<Freelancer> getAllFreelancerParPage(int page, int size) {
	return freelancerRepository.findAll(PageRequest.of(page, size));
	}
	@Override
	public List<Freelancer> findByNomFreelancer(String nom) {
		
		return freelancerRepository.findByNomFreelancer(nom);
	}
	@Override
	public List<Freelancer> findByNomFreelancerContains(String nom) {
		
		return freelancerRepository.findByNomFreelancerContains(nom);
	}
	@Override
	public List<Freelancer> findByNomSalaire(String nom, Double salaire) {
		
		return freelancerRepository.findByNomSalaire(nom, salaire);
	}
	@Override
	public List<Freelancer> findByDomaine(Domaine domaine) {
		
		return freelancerRepository.findByDomaine(domaine);
	}
	@Override
	public List<Freelancer> findByDomaineIdDom(Long id) {
	
		return freelancerRepository.findByDomaineIdDom(id);
	}
	@Override
	public List<Freelancer> findByOrderByNomFreelancerAsc() {
		
		return freelancerRepository.findByOrderByNomFreelancerAsc();
	}
	@Override
	public List<Freelancer> trierFreelancersNomsSalaire() {
		
		return freelancerRepository.trierFreelancersNomsSalaire();
	}
	@Override
	public List<Domaine> getAllDomaine() {
		return domaineRepository.findAll();
	}
}
