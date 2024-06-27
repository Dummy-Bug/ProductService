package product.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity(name = "product")
@PrimaryKeyJoinColumn(name = "base_table_id")
@JsonIgnoreProperties
public class Product extends Base {
    String title;
    String price;
    String description;
    @ManyToOne()
    private Category category;
//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//    List<Image> images;
}