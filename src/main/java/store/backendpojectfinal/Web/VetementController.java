package store.backendpojectfinal.Web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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


}
