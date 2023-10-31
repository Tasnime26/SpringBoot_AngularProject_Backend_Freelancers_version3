package com.tasnime.freelancer.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import com.tasnime.freelancer.entities.Freelancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import com.tasnime.freelancer.entities.Image;
import com.tasnime.freelancer.repos.FreelancerRepository;
import com.tasnime.freelancer.repos.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService{
	@Autowired
	 ImageRepository imageRepository;

	@Autowired
	FreelancerService freelancerService;
	@Autowired
	FreelancerRepository freelancerRepository ;
	 @Override
	 public Image uplaodImage(MultipartFile file) throws IOException {
	 /*Ce code commenté est équivalent au code utilisant le design pattern Builder
	 * Image image = new Image(null, file.getOriginalFilename(), 
	 file.getContentType(), file.getBytes(), null);
	 return imageRepository.save(image);*/
	 return imageRepository.save(Image.builder()
	 .name(file.getOriginalFilename())
	 .type(file.getContentType())
	 .image(file.getBytes()).build() );
	 }
	 @Override
	 public Image getImageDetails(Long id) throws IOException{
	 final Optional<Image> dbImage = imageRepository. findById (id);
	 return Image.builder()
	 .idImage(dbImage.get().getIdImage())
	 .name(dbImage.get().getName())
	 .type(dbImage.get().getType())
	 .image(dbImage.get().getImage()).build() ;
	 }
	 @Override
	 public ResponseEntity<byte[]> getImage(Long id) throws IOException{
	 final Optional<Image> dbImage = imageRepository. findById (id);
	 return ResponseEntity
	 .ok()
	 .contentType(MediaType.valueOf(dbImage.get().getType()))
	 .body(dbImage.get().getImage());
	 }
	 @Override
	 public void deleteImage(Long id) {
	 imageRepository.deleteById(id);
	 }
	 @Override
	 public Image uplaodImageProd(MultipartFile file,Long idFreel) 
	 throws IOException {
		 Freelancer f = new Freelancer();
	 f.setIdFreelancer(idFreel);
	 return imageRepository.save(Image.builder()
	  .name(file.getOriginalFilename())
	  .type(file.getContentType())
	  .image(file.getBytes())
	  .freelancer(f).build() );
	 }
	 @Override
	 public List<Image> getImagesParProd(Long freelId) {
		 Freelancer f = freelancerRepository.findById(freelId).get();
	 return f.getImages();
	 }
}
