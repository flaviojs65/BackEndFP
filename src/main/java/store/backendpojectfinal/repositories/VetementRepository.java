package store.backendpojectfinal.repositories;


import store.backendpojectfinal.entities.Vetement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource  //Toutes les méthodes JpaRepository sont accessibles via une API Rest (Web service générique)
public interface   VetementRepository extends JpaRepository<Vetement, Long> {
    List<Vetement> findByNomContaining(String nom);
}
