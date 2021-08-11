package com.insannity.dscatalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInsertDTO extends UserDTO{

    @NotBlank(message = "Campo obrigatório")
    private String password;

}
