package product.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

import java.util.List;


@Data
@Entity(name = "category")
@PrimaryKeyJoinColumn(name = "base_table_id")
public class Category extends Base {
    private String title;
    private String image;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
