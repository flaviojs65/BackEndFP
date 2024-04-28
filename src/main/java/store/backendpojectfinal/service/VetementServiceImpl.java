package store.backendpojectfinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.backendpojectfinal.entities.Vetement;
import store.backendpojectfinal.repositories.VetementRepository;

import java.util.List;

@Service
public class VetementServiceImpl implements VetementService {

    @Autowired
    private VetementRepository vetementRepository;

    @Override
    public List<Vetement> rechercherVetements(String nom ){
        return vetementRepository.findByNomContaining(nom);
    }

}
