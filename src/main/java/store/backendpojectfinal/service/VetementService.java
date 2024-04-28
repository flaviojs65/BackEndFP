package store.backendpojectfinal.service;

import store.backendpojectfinal.entities.Vetement;

import java.util.List;

public interface VetementService {
    List<Vetement> rechercherVetements(String nom);
}
