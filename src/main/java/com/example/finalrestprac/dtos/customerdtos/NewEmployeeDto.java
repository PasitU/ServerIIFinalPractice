package com.example.finalrestprac.dtos.customerdtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class NewEmployeeDto {
    @NotEmpty
    private Integer id;
    @JsonIgnore
    private String firstName;
    @JsonIgnore
    private String lastName;

    public String getName() {
        return firstName + ' ' + lastName;
    }
}
