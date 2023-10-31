package com.tasnime.freelancer.controllers;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tasnime.freelancer.entities.Domaine;
import com.tasnime.freelancer.entities.Freelancer;
import com.tasnime.freelancer.service.FreelancerService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//import javax.validation.Valid; i changed
import jakarta.validation.Valid;
@Controller
public class FreelancerController {
	@Autowired
	FreelancerService freelancerService;
	@RequestMapping("/showCreate")
	public String showCreate(ModelMap modelMap)
	{
		List<Domaine> doms = freelancerService.getAllDomaine();
		Freelancer freel = new Freelancer();
		Domaine dom = new Domaine ();
		dom = doms.get(0);
		freel.setDomaine(dom);
		modelMap.addAttribute("freelancer", new Freelancer());
		modelMap.addAttribute("mode", "new");
		modelMap.addAttribute("domaine", doms);
	return "formFreelancer";
	}
	
	@RequestMapping("/saveFreelancer")
	public String saveFreelancer(@Valid Freelancer freelancer,  BindingResult bindingResult)
	{
		 if (bindingResult.hasErrors()) {
		        return "formFreelancer";
		    }
		    freelancerService.saveFreelancer(freelancer);
		    return "redirect:/listeFreelancers";
	}
	@RequestMapping("/listeFreelancers")
	public String listeFreelancerss(ModelMap modelMap ,@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "3") int size)

	{
		Page<Freelancer> freel = freelancerService.getAllFreelancerParPage(page, size);
		modelMap.addAttribute("freelancers", freel);
		 modelMap.addAttribute("pages", new int[freel.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		return "listeFreelancers";
	}
	@RequestMapping("/supprimerFreelancer")
	public String supprimerFreelancer(@RequestParam("id") Long id,
	 ModelMap modelMap ,@RequestParam (name="page",defaultValue = "0") int page,
	 @RequestParam (name="size", defaultValue = "3") int size)
	{ 
		freelancerService.deleteFreelancerById(id);
		Page<Freelancer> freel = freelancerService.getAllFreelancerParPage(page, 
				size);
				modelMap.addAttribute("freelancers", freel);
				modelMap.addAttribute("pages", new int[freel.getTotalPages()]);
				modelMap.addAttribute("currentPage", page);
				modelMap.addAttribute("size", size);

	return "listeFreelancers";
	}
	@RequestMapping("/modifierFreelancer")
	public String editerFreelancer(@RequestParam("id") Long id,ModelMap modelMap)
	{
		Freelancer f= freelancerService.getFreelancer(id);
		List<Domaine> doms = freelancerService.getAllDomaine();
	modelMap.addAttribute("freelancer", f);
	modelMap.addAttribute("mode", "edit");
	modelMap.addAttribute("domaine", doms);
	return "formFreelancer";
	}
	@RequestMapping("/updateFreelancer")
	public String updateFreelancer(@ModelAttribute("freelancer") Freelancer freelancer,
	@RequestParam("date") String date,ModelMap modelMap) throws ParseException 
	{
		//conversion de la date 
		 SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		 Date dateinscription = dateformat.parse(String.valueOf(date));
		 freelancer.setDateinscription(dateinscription);
		 
		 freelancerService.updateFreelancer(freelancer);
		 List<Freelancer> freel = freelancerService.getAllFreelancers();
		 modelMap.addAttribute("freelancers", freel);
		return "listeFreelancers";
		}
}
