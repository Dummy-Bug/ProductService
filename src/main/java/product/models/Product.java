package product.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties
@Entity(name = "product")
public class Product {
    @Id
    @JsonProperty("id")
    @Column(name = "product_id")
    long productId;
    String title;
    String price;
    List<String> images;
    @Column(length = 1000)
    String description;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    Category category;
}