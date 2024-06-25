package product.models;

import jakarta.persistence.Entity;

@Entity
public class Product extends BaseModel {
    private String title;
    private String description;
    private double price;
    private String image;
    private int qty;
    private int numberOfOrders;

    private Category category;
}