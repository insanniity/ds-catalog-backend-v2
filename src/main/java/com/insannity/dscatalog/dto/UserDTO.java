package com.insannity.dscatalog.dto;

import com.insannity.dscatalog.entities.User;
import com.insannity.dscatalog.services.validation.UserUpdateValid;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
    @NotBlank(message = "Campo obrigatório")
    private String name;
    @NonNull
    @NotEmpty(message = "Campo obrigatório")
    @Email(message = "Digite um email válido")
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
