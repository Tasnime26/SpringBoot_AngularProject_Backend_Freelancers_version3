package com.tasnime.freelancer.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity; // Updated import
import jakarta.persistence.GeneratedValue; // Updated import
import jakarta.persistence.GenerationType; // Updated import
import jakarta.persistence.Id; // Updated import
import jakarta.persistence.ManyToOne; // Updated import
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal; // Updated import
import jakarta.persistence.TemporalType; // Updated import
import jakarta.validation.constraints.Max; // Updated import
import jakarta.validation.constraints.Min; // Updated import
import jakarta.validation.constraints.NotNull; // Updated import
import jakarta.validation.constraints.PastOrPresent; // Updated import
import jakarta.validation.constraints.Size; // Updated import

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Freelancer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFreelancer;

    @NotNull
    @Size(min = 4, max = 15)
    private String nomFreelancer;

    @Min(value = 10)
    @Max(value = 10000)
    private Double salaireFreelancer;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private Date dateinscription;

    @ManyToOne
    private Domaine domaine;
    /*@OneToOne
    private Image image;*/
    @OneToMany (mappedBy = "freelancer")
    private List<Image> images;
    private String imagePath;

    public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Freelancer() {
        super();
    }

    public Domaine getDomaine() {
        return domaine;
    }

    public void setDomaine(Domaine domaine) {
        this.domaine = domaine;
    }

    public Freelancer(String nomFreelancer, Double salaireFreelancer, Date dateinscription) {
        this.nomFreelancer = nomFreelancer;
        this.salaireFreelancer = salaireFreelancer;
        this.dateinscription = dateinscription;
    }

    public Long getIdFreelancer() {
        return idFreelancer;
    }

    public void setIdFreelancer(Long idFreelancer) {
        this.idFreelancer = idFreelancer;
    }

    public String getNomFreelancer() {
        return nomFreelancer;
    }

    public void setNomFreelancer(String nomFreelancer) {
        this.nomFreelancer = nomFreelancer;
    }

    public Double getSalaireFreelancer() {
        return salaireFreelancer;
    }

    public void setSalaireFreelancer(Double salaireFreelancer) {
        this.salaireFreelancer = salaireFreelancer;
    }

    public Date getDateinscription() {
        return dateinscription;
    }

    public void setDateinscription(Date dateinscription) {
        this.dateinscription = dateinscription;
    }

    @Override
    public String toString() {
        return "Freelancer [idFreelancer=" + idFreelancer + ", nomFreelancer=" + nomFreelancer + ", salaireFreelancer="
                + salaireFreelancer + ", dateinscription=" + dateinscription + "]";
    }

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	/*public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}*/
  
}
