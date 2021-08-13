package com.insannity.dscatalog.dto;

import com.insannity.dscatalog.services.validation.UserInsertValid;
import com.insannity.dscatalog.services.validation.UserUpdateValid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@UserUpdateValid
public class UserUpdateDTO extends UserDTO{


}
