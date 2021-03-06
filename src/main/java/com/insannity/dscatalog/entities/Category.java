package com.insannity.dscatalog.entities;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_category")
@EqualsAndHashCode(exclude = "products")
public class Category extends Auditory implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Setter(value = AccessLevel.PROTECTED)
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();


}
