package com.insannity.dscatalog.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardError implements Serializable {

    private Instant timestamp = Instant.now();
    private Integer status;
    private String error;
    private String message;
    private String path;

}
