package com.insannity.dscatalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInsertDTO extends UserDTO{

    private String password;

}
