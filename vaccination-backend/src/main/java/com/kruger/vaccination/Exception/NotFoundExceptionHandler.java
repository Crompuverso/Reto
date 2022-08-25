package com.kruger.vaccination.Exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class NotFoundExceptionHandler extends RuntimeException {
    private String FieldName;
    private String resourceName;
    private String value;

    public NotFoundExceptionHandler(String fieldName, String resourceName, String value) {
        super(String.format("%s no encontrado para: %s: %s", resourceName, fieldName, value));
        this.resourceName = resourceName;
        FieldName = fieldName;
        this.value = value;
    }
}