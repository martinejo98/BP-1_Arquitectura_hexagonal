package com.example.BP1_Arquitectura_hexagonal.infrastructure.controller;

import com.example.BP1_Arquitectura_hexagonal.application.service.PersonaService;
import com.example.BP1_Arquitectura_hexagonal.domain.Persona;
import com.example.BP1_Arquitectura_hexagonal.infrastructure.controller.dto.input.personaInputDTO;
import com.example.BP1_Arquitectura_hexagonal.infrastructure.controller.dto.output.personaOutputDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {

        @Autowired
        private PersonaService personaService;

        @Autowired
        private ModelMapper modelMapper;

        @PostMapping("/addPersona")
        public String addPersona(@RequestBody personaInputDTO personaInputDTO) throws Exception {
            personaService.addPersona(personaInputDTO);
            return "Persona añadida";
        }

        @GetMapping("/getPersonaID/{id}")
        public personaOutputDTO getPersonaByID(@PathVariable int id) throws Exception {
            return personaService.getPersonaByID(id);
        }


        @GetMapping("/getPersonaName/{name}")
        public List<personaOutputDTO> getPersonaByName(@PathVariable String name){
            return personaService.getPersonaByName(name);
        }

        @GetMapping("/getAll")
        public List <personaOutputDTO> getAll(){
            return personaService.getAll();
        }

    @PutMapping("/update/{id}")
    public String addPersona(@RequestBody personaInputDTO persona, @PathVariable int id){
        personaService.updatePersona(id, persona);
        return "Persona actualziada";
    }

    @DeleteMapping("/delete/{id}")
    public String deletePersona(@PathVariable int id){
        personaService.deletePersona(id);
        return "Persona eliminada";
    }
}
