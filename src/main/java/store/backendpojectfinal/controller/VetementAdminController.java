package store.backendpojectfinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.backendpojectfinal.entities.Vetement;
import store.backendpojectfinal.service.VetementServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/admin/vetements")
public class VetementAdminController {



    @Autowired
    private VetementServiceImpl vetementService;

    @GetMapping("/all")
    public ResponseEntity<List<Vetement>> getAllVetements() {
        List<Vetement> vetements = vetementService.getAllVetements();
        return ResponseEntity.ok().body(vetements);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vetement> getVetementById(@PathVariable(value = "id") Long id) {
        Vetement vetement = vetementService.getVetementById(id);
        return ResponseEntity.ok().body(vetement);
    }

    @PostMapping("/create")
    public ResponseEntity<Vetement> createVetement(@RequestBody Vetement vetement) {
        Vetement createdVetement = vetementService.createVetement(vetement);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVetement);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Vetement> updateVetement(@PathVariable(value = "id") Long id,
                                                   @RequestBody Vetement newVetement) {
        Vetement updatedVetement = vetementService.updateVetement(id, newVetement);
        return ResponseEntity.ok().body(updatedVetement);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVetement(@PathVariable(value = "id") Long id) {
        vetementService.deleteVetement(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/recherche")
    public ResponseEntity<List<Vetement>> rechercherVetements(@RequestParam("nom") String nom) {
        List<Vetement> vetements = vetementService.rechercherVetements(nom);
        return ResponseEntity.ok().body(vetements);
    }


}
