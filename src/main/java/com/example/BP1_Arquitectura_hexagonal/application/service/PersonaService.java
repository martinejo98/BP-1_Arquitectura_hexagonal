package com.example.BP1_Arquitectura_hexagonal.application.service;

import com.example.BP1_Arquitectura_hexagonal.domain.Persona;
import com.example.BP1_Arquitectura_hexagonal.infrastructure.controller.dto.input.personaInputDTO;
import com.example.BP1_Arquitectura_hexagonal.infrastructure.controller.dto.output.personaOutputDTO;
import com.example.BP1_Arquitectura_hexagonal.repository.PersonaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    ModelMapper modelMapper;

    public personaOutputDTO addPersona(personaInputDTO persona) throws Exception {
        if(persona.getUsuario() != null && persona.getUsuario().length() <= 10 && persona.getUsuario().length() >= 6){
            if(persona.getName() != null){
                if (persona.getSurname() != null) {
                    if (persona.getPassword() != null) {
                        if (persona.getCompany_email() != null) {
                            if(persona.getPersonal_email() != null){
                                if (persona.getCity() != null) {
                                    personaRepository.save(modelMapper.map(persona, Persona.class));
                                    return modelMapper.map(persona, personaOutputDTO.class);
                                } else {
                                    throw new Exception("El campo email personal no puede estar vacío");
                                }
                            }else{
                                throw new Exception("El campo email personal no puede estar vacío");
                            }
                        } else {
                            throw new Exception("El campo email corporativo no puede estar vacío");
                        }
                    }else{
                        throw new Exception("El campo contraseña no puede estar vacío");
                    }
                }else{
                    throw new Exception("El campo apellido no puede estar vacío");
                }
            }else{
                throw new Exception("El campo nombre no puede estar vacío");
            }
        }else{
            throw new Exception("El campo usuario debe tener entre 6 y 10 caracteres");
        }
    }

    public personaOutputDTO getPersonaByID(Integer id) throws Exception {
        Persona persona = personaRepository.findById(id).orElseThrow(()-> new Exception("No se ha encontrado a nadie con el id: "+id));
        personaOutputDTO peDTO = modelMapper.map(persona, personaOutputDTO.class);
        return peDTO;
    }

    public List<personaOutputDTO> getPersonaByName(String name){
        List <personaOutputDTO> listaPersonas = new ArrayList<>();
        personaRepository.findAll().forEach(
                person -> {
                    if(person.getName().equals(name)){
                        personaOutputDTO peDTO = modelMapper.map(person, personaOutputDTO.class);
                        listaPersonas.add(peDTO);
                    }
                }
        );

        return listaPersonas;
    }

    public List<personaOutputDTO> getAll(){
        List <personaOutputDTO> listaPersonas = new ArrayList<>();
        personaRepository.findAll().forEach(
                person -> {
                    personaOutputDTO peDTO = modelMapper.map(person, personaOutputDTO.class);
                    listaPersonas.add(peDTO);
                }
        );
        return listaPersonas;
    }

    /**
     * Este metodo lo que hace esc convertirme todas las persona que encuentra el findAll() en personasDTO
     * y me lo añade a la lista de personasDTO que he creado y la retorna
     */

    public personaOutputDTO updatePersona(int id, personaInputDTO persona) {
        Optional<Persona> personEntity = personaRepository.findById(id);
        if (personEntity.isPresent()) {
            persona.setId_persona(id);
            persona.setUsuario(Optional.ofNullable(persona.getUsuario()).orElse(personEntity.get().getUsuario()));
            persona.setPassword(Optional.ofNullable(persona.getPassword()).orElse(personEntity.get().getPassword()));
            persona.setName(Optional.ofNullable(persona.getName()).orElse(personEntity.get().getName()));
            persona.setSurname(Optional.ofNullable(persona.getSurname()).orElse(personEntity.get().getSurname()));
            persona.setCompany_email(Optional.ofNullable(persona.getCompany_email()).orElse(personEntity.get().getCompany_email()));
            persona.setPersonal_email(Optional.ofNullable(persona.getPersonal_email()).orElse(personEntity.get().getPersonal_email()));
            persona.setCity(Optional.ofNullable(persona.getCity()).orElse(personEntity.get().getCity()));
            persona.setImagen_url(Optional.ofNullable(persona.getImagen_url()).orElse(personEntity.get().getImagen_url()));
            persona.setActive(personEntity.get().getActive());
            persona.setCreated_date(personEntity.get().getCreated_date());
            personaRepository.saveAndFlush(modelMapper.map(persona, Persona.class));
            personaOutputDTO personDTO = modelMapper.map(persona, personaOutputDTO.class);
            return personDTO;
        } else {
            return null;
        }
    }

    public String deletePersona(int id){
        personaRepository.deleteById(id);
        return "Persona eliminada";
    }
}