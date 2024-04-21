package store.backendpojectfinal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serializable;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "vetements")
public class
Vetement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private Double prix;
    @Column(length = 1000)
    private String description;

    @ElementCollection
    @CollectionTable(name = "vetement_images", joinColumns = @JoinColumn(name = "vetement_id"))
    @Column(name = "image")
    private List<String> images;

    private Integer quantite;
    private String taille;
    private String couleur;
    private String couleurName;
    private String categorie;
    private String product;
    private Boolean newItem;
}
