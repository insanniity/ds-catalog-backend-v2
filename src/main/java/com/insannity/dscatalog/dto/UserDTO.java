package com.insannity.dscatalog.dto;

import com.insannity.dscatalog.entities.User;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    @NonNull
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String email;
//    private String password;

    @Setter(value=AccessLevel.PROTECTED)
    private Set<RoleDTO> roles = new HashSet<>();

    public UserDTO(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
    }


}
