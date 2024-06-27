package product.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonIgnoreProperties
@Entity(name = "image")
@PrimaryKeyJoinColumn(name = "base_table_id")
public class Image extends Base {
    private String imageUrl;
    @ManyToOne
    private Product product;

    @JsonCreator
    public Image(@JsonProperty("image") String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
