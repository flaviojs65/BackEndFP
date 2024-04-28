package store.backendpojectfinal.Web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.backendpojectfinal.entities.Vetement;
import store.backendpojectfinal.repositories.VetementRepository;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/vetements")
public class VetementController {

    @Autowired
    private VetementRepository vetementRepository;

    @GetMapping
    public List<Vetement> getAllVetements() {
        return vetementRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Vetement> getVetementById(@PathVariable Long id) {
        Optional<Vetement> vetement = vetementRepository.findById(id);
        return vetement.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/rechercher")
    public ResponseEntity<List<Vetement>> rechercherVetements(@RequestParam String nom) {
        List<Vetement> resultats = vetementRepository.findByNomContaining(nom);
        if (resultats.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(resultats);
        }
    }

}
