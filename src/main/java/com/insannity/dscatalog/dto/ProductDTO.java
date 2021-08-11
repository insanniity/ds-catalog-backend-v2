package com.insannity.dscatalog.dto;

import com.insannity.dscatalog.entities.Category;
import com.insannity.dscatalog.entities.Product;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements Serializable {


    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;

    @Setter(value=AccessLevel.PROTECTED)
    private List<CategoryDTO> categories = new ArrayList<>();

    public ProductDTO(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.imgUrl = entity.getImgUrl();
    }

    public ProductDTO(Product entity, Set<Category> cat){
        this(entity);
        cat.forEach(x -> categories.add(new CategoryDTO(x)));
    }

}
