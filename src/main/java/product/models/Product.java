package product.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Product extends BaseModel {
    private String title;
    private String description;
    private double price;
    private String image;
    private int qty;
    private int numberOfOrders;

    @ManyToOne()
    private Category category;
}