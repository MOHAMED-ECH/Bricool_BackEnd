package ma.ac.emi.bricool.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString


public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    private String lastName;

    private String email;


    private String phone;

    private String password;


    private Occupation occupations ;


   // private List <String> regionalOperations;


    private String photoDeProfil;


    private String cin;

    private String businessHours;

    private Ville ville;

    private Gender gender;
    private String city;


    private String slogan;

    private String description;

    private int rating;

    private int completedTAskNumber;

    private Date dateNaissance;


    /*@OneToMany(mappedBy = "seller",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Project> projects;*/



    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL, orphanRemoval = true)
    private List <Photo> photos = new ArrayList<>();


    public Seller(String firstName,
                  String lastName,
                  String email,
                  String phone,
                  String password,
                  //String regionalOperations,
                  String cin,
                  String businessHours,
                  Ville ville,
                  Gender gender,
                  String city,
                  String slogan,
                  String description,
                  int rating,
                  int completedTAskNumber,
                  Date dateNaissance
    ) {

        this.firstName= firstName;
        this.lastName=lastName;
        this.email=email;
        this.phone= phone;
        this.password=password;

       // this.regionalOperations = regionalOperations;
        this.cin = cin;
        this.businessHours = businessHours;
        this.ville = ville;
        this.gender = gender;
        this.city = city;
        this.slogan = slogan;
        this.description = description;
        this.rating = rating;
        this.completedTAskNumber = completedTAskNumber;


    }


    public Seller (String firstName, String lastName, String phone,String email, String password, Date dateNaissance){

        this.firstName= firstName;
        this.lastName=lastName;
        this.email=email;
        this.phone= phone;
        this.password=password;
        this.dateNaissance = dateNaissance;
    }


    public void addPhoto(Photo photo) {
        photos.add(photo);
        photo.setSeller(this);
    }


    public void removePhoto(Photo photo) {
        photos.remove(photo);
        photo.setSeller(null);
    }


  /*  public void addProject(Project project) {
        projects.add(project);
        project.setSeller(this);
    }*/

   /* public void removeProject(Project project) {
        projects.remove(project);
        project.setSeller(null);
    }*/


}
