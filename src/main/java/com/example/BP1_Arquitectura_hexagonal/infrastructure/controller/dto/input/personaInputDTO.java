package com.example.BP1_Arquitectura_hexagonal.infrastructure.controller.dto.input;

import lombok.Data;

import javax.persistence.*;

@Data
public class personaInputDTO {

    private int id_persona;
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private boolean active;
    private String created_date;
    private String imagen_url;
    private String termination_date;
}
