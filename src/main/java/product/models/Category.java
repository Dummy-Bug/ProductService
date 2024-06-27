package product.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "category")
public class Category {
    @Id
    @JsonProperty("id")
    @Column(name = "category_id")
    private long categoryId;
    private String title;
    private String image;
}
