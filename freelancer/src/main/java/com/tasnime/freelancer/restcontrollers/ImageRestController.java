package com.tasnime.freelancer.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.tasnime.freelancer.entities.Freelancer;
import com.tasnime.freelancer.entities.Image;
import com.tasnime.freelancer.service.FreelancerService;
import com.tasnime.freelancer.service.ImageService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/image")
@CrossOrigin(origins = "*")

public class ImageRestController {
	@Autowired
	 ImageService imageService ;
	@Autowired
	FreelancerService freelancerService;
	
	@RequestMapping(value = "/uploadFS/{id}" , method = RequestMethod.POST)
	 public void uploadImageFS(@RequestParam("image") MultipartFile 
	file,@PathVariable("id") Long id) throws IOException {
		Freelancer f = freelancerService.getFreelancer(id);
	 f.setImagePath(id+".jpg");
	 
	Files.write(Paths.get(System.getProperty("user.home")+"/images/"+f.getImagePath())
	, file.getBytes());
	freelancerService.saveFreelancer(f);
	 
	 }
	@RequestMapping(value = "/loadfromFS/{id}" , 
			 method = RequestMethod.GET,
			 produces = MediaType.IMAGE_JPEG_VALUE)
			 public byte[] getImageFS(@PathVariable("id") Long id) throws IOException {
			 
		Freelancer f = freelancerService.getFreelancer(id);
			 return
			Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/images/"+f.getImagePath()));
			 }

	
	
	
	
	
	
	
	
	
	
	
	
	 
	 @RequestMapping(value = "/upload" , method = RequestMethod.POST)
	 public Image uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
	 return imageService.uplaodImage(file);
	 }
	 @RequestMapping(value = "/get/info/{id}" , method = RequestMethod.GET)
	 public Image getImageDetails(@PathVariable("id") Long id) throws IOException {
	 return imageService.getImageDetails(id) ;
	 }
	 
	 
	 @PostMapping(value = "/uplaodImageFreel/{idFreel}" )
	 public Image uploadMultiImages(@RequestParam("image")MultipartFile file,
	 @PathVariable("idFreel") Long idFreel) 
	throws IOException {
	 return imageService.uplaodImageProd(file,idFreel);
	 }
	@RequestMapping(value = "/getImagesFreel/{idFreel}" , 
	 method = RequestMethod.GET)
	 public List<Image> getImagesProd(@PathVariable("idFreel") Long idFreel) 
	throws IOException {
	 return imageService.getImagesParProd(idFreel);
	 }

	 
	 
	 @RequestMapping(value = "/load/{id}" , method = RequestMethod.GET)
	 public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) throws IOException 
	{
	 return imageService.getImage(id);
	 }
	 
	 
	 @RequestMapping(value = "/delete/{id}" , method = RequestMethod.DELETE)
	 public void deleteImage(@PathVariable("id") Long id){
	 imageService.deleteImage(id);
	 }
	 @RequestMapping(value="/update",method = RequestMethod.PUT)
	 public Image UpdateImage(@RequestParam("image")MultipartFile file) throws IOException {
	 return imageService.uplaodImage(file);
	 }

}
