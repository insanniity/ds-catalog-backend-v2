package com.insannity.dscatalog.controllers.exceptions;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationError extends StandardError{

    @Setter(value= AccessLevel.PROTECTED)
    private List<FieldMessage> errors = new ArrayList<>();

    public void addErrors(String fieldName, String message){
        errors.add(new FieldMessage(fieldName, message));
    }

}
