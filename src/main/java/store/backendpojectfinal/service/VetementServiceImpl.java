package store.backendpojectfinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import store.backendpojectfinal.entities.Vetement;
import store.backendpojectfinal.repositories.VetementRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service

public class VetementServiceImpl implements VetementService {

    @Autowired
    private VetementRepository vetementRepository;

    @Override
    public List<Vetement> rechercherVetements(String nom ){
        return vetementRepository.findByNomContaining(nom);
    }




    @Override
    public List<Vetement> getAllVetements() {
        return vetementRepository.findAll();
    }

    @Override
    public Vetement getVetementById(Long id) {
        return vetementRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Vetement not found"));
    }

    @Override
    public Vetement createVetement(Vetement vetement) {
        return vetementRepository.save(vetement);
    }

    @Override
    public Vetement updateVetement(Long id, Vetement newVetement) {
        Vetement existingVetement = getVetementById(id);
        existingVetement.setNom(newVetement.getNom());
        /* Possibilité d'ajout d'autres vêtements à mettre à jour  */
        return vetementRepository.save(existingVetement);
    }

    @Override
    public void deleteVetement(Long id) {
        vetementRepository.deleteById(id);
    }





}
