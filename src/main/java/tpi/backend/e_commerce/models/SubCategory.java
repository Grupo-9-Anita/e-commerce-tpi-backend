package tpi.backend.e_commerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class SubCategory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    private boolean deleted; //True si esta eliminado, false si no

    public SubCategory() {
    }

    public SubCategory(String name) {
        this.name = name;
    }

    public SubCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
}