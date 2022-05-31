package com.example.BP1_Arquitectura_hexagonal.repository;

import com.example.BP1_Arquitectura_hexagonal.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository <Persona, Integer> {
}
