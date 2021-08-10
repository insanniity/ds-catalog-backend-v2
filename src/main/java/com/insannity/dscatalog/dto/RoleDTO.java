package com.insannity.dscatalog.dto;

import com.insannity.dscatalog.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO implements Serializable {

    private Long id;
    private String authority;

    public RoleDTO(Role entity) {
        this.id = entity.getId();
        this.authority = entity.getAuthority();
    }
}
