package com.icodeap.Udemy_apirest_prod.controller;

import com.icodeap.Udemy_apirest_prod.dto.AutorDTO;
import com.icodeap.Udemy_apirest_prod.entity.Autor;
import com.icodeap.Udemy_apirest_prod.service.AutorService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorService autorService;
    private final ModelMapper modelMapper;

    public AutorController(AutorService autorService, ModelMapper modelMapper) {
        this.autorService = autorService;
        this.modelMapper = modelMapper;
    }

    //http://localhost:8080/autores
    @PostMapping
    public ResponseEntity<AutorDTO> saveAutor(@RequestBody AutorDTO autorDTO) {
        //DTO a Autor
        Autor autor = modelMapper.map(autorDTO, Autor.class);
        //autor.setNombre(autorDTO.getNombre());
        //autor.setApellido(autorDTO.getApellido());
        //autor.setTelefono(autorDTO.getTelefono());


        //Guardar en la BD
        Autor autorSaved = autorService.saveAutor(autor);

        //Autor a AutorDTO
        //AutorDTO autorDTOSaved = new AutorDTO();
        //autorDTOSaved.setId(autorSaved.getId());
        //autorDTOSaved.setNombre(autorSaved.getNombre());
        //autorDTOSaved.setApellido(autorSaved.getApellido());
        //autorDTOSaved.setTelefono(autorSaved.getTelefono());

        return new ResponseEntity<>(modelMapper.map(autorService.saveAutor(autor),AutorDTO.class), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AutorDTO>> findAll() {
        List<AutorDTO> autorDTOS = autorService.findAll().stream().map(
                autor ->{
                    //AutorDTO autorDTO = new AutorDTO();
                    //autorDTO.setId(autor.getId());
                    //autorDTO.setNombre(autor.getNombre());
                    //autorDTO.setApellido(autor.getApellido());
                    //autorDTO.setTelefono(autor.getTelefono());
                    return modelMapper.map(autor, AutorDTO.class);
                }
        ).toList();
        if(autorDTOS.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return  ResponseEntity.ok(autorDTOS);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> findById(@PathVariable Long id) {
        Optional<Autor> autorOptional = autorService.findById(id);

        return autorOptional.map(
                autorDB -> {
                    //AutorDTO autorDTO = new AutorDTO();
                    //autorDTO.setId(autorDB.getId());
                    //autorDTO.setNombre(autorDB.getNombre());
                    //autorDTO.setApellido(autorDB.getApellido());
                    //autorDTO.setTelefono(autorDB.getTelefono());
                    return ResponseEntity.ok(modelMapper.map(autorDB, AutorDTO.class));
                }
        ).orElseGet(
                () -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<AutorDTO> updateAutor(@RequestBody AutorDTO autorDTO) {
        //DTO a Autor
        Autor autor = modelMapper.map(autorDTO, Autor.class);
        //autor.setId(autorDTO.getId());
        //autor.setNombre(autorDTO.getNombre());
        //autor.setApellido(autorDTO.getApellido());
        //autor.setTelefono(autorDTO.getTelefono());

        return autorService.updateAutor(autor).map(
                autorUpdated -> {
                    //AutorDTO autorDTOUpdated = new AutorDTO();
                    //autorDTOUpdated.setId(autorUpdated.getId());
                    //autorDTOUpdated.setNombre(autorUpdated.getNombre());
                    //autorDTOUpdated.setApellido(autorUpdated.getApellido());
                    //autorDTOUpdated.setTelefono(autorUpdated.getTelefono());

                    return ResponseEntity.ok(modelMapper.map(autorUpdated, AutorDTO.class));
                }
        ).orElseGet(
                ()-> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutor(@PathVariable Long id) {
        if(autorService.deleteById(id)){
            return  ResponseEntity.noContent().build();

        }
        return ResponseEntity.notFound().build();

    }

}
