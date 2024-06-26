package product.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import product.models.Category;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {
    String id;
    String title;
    String price;
    String description;
    Category category;
    List<String> images;
}
