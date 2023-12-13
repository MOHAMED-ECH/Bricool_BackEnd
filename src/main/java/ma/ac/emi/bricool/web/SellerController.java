package ma.ac.emi.bricool.web;


import ma.ac.emi.bricool.entities.Seller;
import ma.ac.emi.bricool.entities.Skill;
import ma.ac.emi.bricool.repositories.SellerRepository;
import ma.ac.emi.bricool.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/seller")
public class SellerController {


    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private SkillRepository skillRepository;


    @GetMapping("/sellers/{id}")

    public Seller getSeller(@PathVariable Long id){
        return sellerRepository.findById(id).get();
    }

    @PostMapping("/sellers/{id}")

    public Seller saveSeller(@RequestBody Seller seller){
        return sellerRepository.save(seller);
    }



    @PostMapping("/sellers/{sellerId}/skill")
    public Skill addSkillToSeller(@PathVariable Long sellerId, @RequestBody Skill skill) {
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(() -> new ResourceNotFoundException("Seller not found"));
            skill.setSeller(seller);
            skillRepository.save(skill);

            return skill;
    }


    @PostMapping("/sellers/{id}/image")
    public void createSynthImage(@PathVariable("id") long id, @RequestParam("file") MultipartFile image) throws Exception {
        Seller seller = sellerRepository.findById(id).orElseThrow();
        // The "public" directory is automatically used by Spring to serve static assets
        Path publicDirectory = Paths.get(".", "public").toAbsolutePath();
        byte[] imageContent = image.getBytes();
        Path filepath = Paths.get(publicDirectory.toString(), image.getOriginalFilename());
        try (OutputStream os = Files.newOutputStream(filepath)) {
            os.write(imageContent);
        }
        seller.setPhotoDeProfil(image.getOriginalFilename());
        sellerRepository.save(seller);
    }

}
