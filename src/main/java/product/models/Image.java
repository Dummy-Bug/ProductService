package product.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Data
@Entity(name="image")
@PrimaryKeyJoinColumn(name = "base_table_id")
public class Image extends Base {
    private String imageUrl;
    @ManyToOne
    private Product product;
}
