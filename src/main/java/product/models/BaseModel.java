package product.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public abstract class BaseModel {
    @Id
    private Long id;
    private Date createdAt;
    private Date updatedAt;
}