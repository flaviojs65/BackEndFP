package store.backendpojectfinal.service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import store.backendpojectfinal.entities.Vetement;
import store.backendpojectfinal.repositories.VetementRepository;

import java.io.IOException;
import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private VetementRepository vetementRepository;

    @Override
    public void initVetements() {
        if (vetementRepository.count() == 0) {
            try {
                // Cargar datos desde el archivo JSON
                ObjectMapper objectMapper = new ObjectMapper();
                ClassPathResource resource = new ClassPathResource("data/Vetments.json");
                List<Vetement> vetements = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<Vetement>>() {});

                // Guardar los datos en la base de datos
                vetementRepository.saveAll(vetements);

                System.out.println("Vetements initialized: " + vetements.size() + " entries added.");
            } catch (IOException e) {
                System.out.println("Failed to initialize Vetements: " + e.getMessage());
            }
        } else {
            System.out.println("Vetements already initialized.");
        }
    }
}
