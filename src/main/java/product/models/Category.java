package product.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper=false)
@JsonIgnoreProperties
@Entity(name = "category")
@PrimaryKeyJoinColumn(name = "base_table_id")
public class Category extends Base {
    private String title;
    private String image;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
