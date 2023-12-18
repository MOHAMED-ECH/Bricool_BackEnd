package ma.ac.emi.bricool.web;


import ma.ac.emi.bricool.entities.Seller;
import ma.ac.emi.bricool.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/seller")
public class SellerController {


    @Autowired
    private SellerRepository sellerRepository;



    @GetMapping("/sellers/{id}")

    public Seller getSeller(@PathVariable Long id){
        return sellerRepository.findById(id).get();
    }

  /*  @PostMapping("/sellers/{id}")

    public Seller saveSeller(@RequestBody Seller seller){
        return sellerRepository.save(seller);
    }*/


    @PostMapping("/sellers")
    public Seller createSeller(@RequestBody Seller seller) {
        return sellerRepository.save(seller);
    }



   /* @PostMapping("/sellers/{sellerId}/skill")
    public Skill addSkillToSeller(@PathVariable Long sellerId, @RequestBody Skill skill) {
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(() -> new ResourceNotFoundException("Seller not found"));
            skill.setSeller(seller);
            skillRepository.save(skill);

            return skill;
    }*/


    @PostMapping("/sellers/{id}/image")
    public void createSynthImage(@PathVariable("id") long id, @RequestParam("file") MultipartFile image) throws Exception {
        Seller seller = sellerRepository.findById(id).orElseThrow();
        // The "public" directory is automatically used by Spring to serve static assets
        Path publicDirectory = Paths.get(".", "public").toAbsolutePath();

        if (!Files.exists(publicDirectory)) {
            Files.createDirectories(publicDirectory);
        }

        byte[] imageContent = image.getBytes();
        Path filepath = Paths.get(publicDirectory.toString(), image.getOriginalFilename());
        try (OutputStream os = Files.newOutputStream(filepath)) {
            os.write(imageContent);
        }
        seller.setPhotoDeProfil(image.getOriginalFilename());
        sellerRepository.save(seller);
    }



   /* @GetMapping("/sellers/{id}/image")
    public byte[] getSynthImage(@PathVariable("id") long id) throws Exception {
        Seller seller = sellerRepository.findById(id).orElseThrow();
        // The "public" directory is automatically used by Spring to serve static assets
        Path publicDirectory = Paths.get(".", "public").toAbsolutePath();



        Path filepath = Paths.get(publicDirectory.toString(), seller.getPhotoDeProfil());
        return Files.readAllBytes(filepath);
    }*/




    @GetMapping("/sellers/{id}/image")
    public ResponseEntity<byte[]> getSynthImage(@PathVariable("id") long id) {
        try {
            Seller seller = sellerRepository.findById(id).orElseThrow(() -> new FileNotFoundException("Seller not found"));
            Path publicDirectory = Paths.get(".", "public").toAbsolutePath();
            Path filepath = publicDirectory.resolve(seller.getPhotoDeProfil());

            if (!Files.exists(filepath)) {
                throw new FileNotFoundException("Image file not found");
            }

            byte[] imageData = Files.readAllBytes(filepath);
            String contentType = "image/jpeg"; // Adjust this based on the actual image type

            return ResponseEntity
                    .ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(imageData);

        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            // Log exception details here
            return ResponseEntity.internalServerError().build();
        }
    }




}
