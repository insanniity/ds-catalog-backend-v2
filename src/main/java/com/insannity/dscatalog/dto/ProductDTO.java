package com.insannity.dscatalog.dto;

import com.insannity.dscatalog.entities.Category;
import com.insannity.dscatalog.entities.Product;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    @NonNull
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private Double price;
    @NonNull
    private String imgUrl;

    private List<CategoryDTO> categories = new ArrayList<>();

    public ProductDTO(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.imgUrl = entity.getImgUrl();
    }

    public ProductDTO(Product entity, Set<Category> categories){
        this(entity);
        categories.forEach(x -> this.categories.add(new CategoryDTO(x)));
    }

}
