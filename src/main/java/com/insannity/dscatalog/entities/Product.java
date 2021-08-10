package com.insannity.dscatalog.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "tb_product")
public class Product extends Auditory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    @Column(columnDefinition = "TEXT")
    private String description;
    @NonNull
    private Double price;
    @NonNull
    private String imgUrl;

    @Setter(value=AccessLevel.PROTECTED)
    @ManyToMany
    @JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

}
