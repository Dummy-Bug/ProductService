package product.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "product")
@PrimaryKeyJoinColumn(name = "base_table_id")
public class Product extends Base {
    String title;
    String price;
    String description;
    @ManyToOne()
    private Category category;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    List<Image> images;
}