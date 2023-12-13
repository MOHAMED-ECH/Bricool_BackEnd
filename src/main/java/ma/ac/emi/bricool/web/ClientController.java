package ma.ac.emi.bricool.web;


import ma.ac.emi.bricool.entities.Client;
import ma.ac.emi.bricool.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientController {


    @Autowired
    private ClientRepository clientRepository;


    @GetMapping("/clients/{id}")

    public Client getClient(@PathVariable Long id){
        return clientRepository.findById(id).get();
    }


    @PostMapping("/clients/{id}")

public Client saveClient(@RequestBody Client client){
        return clientRepository.save(client);
    }
}
