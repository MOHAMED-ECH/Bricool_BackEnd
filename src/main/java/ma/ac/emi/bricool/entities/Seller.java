package ma.ac.emi.bricool.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Seller extends Client{


    private List<Occupation> occupations = new ArrayList<>();


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



    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL, orphanRemoval = true)
    private List <Photo> photos = new ArrayList<>();

    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL, orphanRemoval = true)
   private List <Skill> skills= new ArrayList<>();

    public Seller(String firstName,
                  String lastName,
                  String email,
                  String phone,
                  String password,
                  String regionalOperations,
                  String cin,
                  String businessHours,
                  Ville ville,
                  Gender gender,
                  String city,
                  String slogan,
                  String description,
                  int rating,
                  int completedTAskNumber
    ) {

        super(firstName, lastName, email, phone, password);

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
}
