package com.insannity.dscatalog.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_user")
public class User extends Auditory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    @Email
    @Column(unique = true)
    private String email;
    @NonNull
    private String password;

    @Setter(value=AccessLevel.PROTECTED)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();



}
