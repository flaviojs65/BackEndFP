package store.backendpojectfinal.service;

import org.springframework.stereotype.Service;
import store.backendpojectfinal.entities.Vetement;

import java.util.List;


@Service
public interface VetementService {
    List<Vetement> rechercherVetements(String nom);

    List<Vetement> getAllVetements();

    Vetement getVetementById(Long id);

    Vetement createVetement(Vetement vetement);

    Vetement updateVetement(Long id, Vetement newVetement);

    void deleteVetement(Long id);
}
