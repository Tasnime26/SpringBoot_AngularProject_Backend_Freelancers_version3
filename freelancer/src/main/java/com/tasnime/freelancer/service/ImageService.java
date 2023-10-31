package com.tasnime.freelancer.service;
import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import com.tasnime.freelancer.entities.Image;
public interface ImageService {
	Image uplaodImage(MultipartFile file) throws IOException;
	 Image getImageDetails(Long id) throws IOException;
	 ResponseEntity<byte[]> getImage(Long id) throws IOException;
	 void deleteImage(Long id) ;
	 Image uplaodImageProd(MultipartFile file,Long idFreel) throws IOException;
	 List<Image> getImagesParProd(Long freelId);

	}

