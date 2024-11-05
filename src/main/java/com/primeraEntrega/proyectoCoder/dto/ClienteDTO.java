package com.primeraEntrega.proyectoCoder.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteDTO {
    
    @Schema(description = "Unique identifier of the user", example = "1")
    private long id;
    @Schema(description = "Name of the user", example = "John Doe")
    private String name;
    @Schema(description = "Email address of the user", example = "john.doe@example.com")
    private String email;
    @Schema(description = "Phone number of the user", example = "+52 3322114455")
    private String phone;
    
    public ClienteDTO() {
    }
    
    public ClienteDTO(long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

}



