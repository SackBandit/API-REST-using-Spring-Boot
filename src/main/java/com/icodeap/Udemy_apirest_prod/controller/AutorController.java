package com.icodeap.Udemy_apirest_prod.controller;

import com.icodeap.Udemy_apirest_prod.entity.Autor;
import com.icodeap.Udemy_apirest_prod.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }
    //http://localhost:8080/autores
    @PostMapping
    public ResponseEntity<Autor> saveAutor(@RequestBody Autor autor) {
        return new ResponseEntity<>(autorService.saveAutor(autor), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Autor>> findAll() {
        return  ResponseEntity.ok(autorService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Autor> findById(@PathVariable Long id) {
        Optional<Autor> autorOptional = autorService.findById(id);
        return autorOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Autor> updateAutor(@RequestBody Autor autor) {
        return autorService.updateAutor(autor).map(
                ResponseEntity::ok

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
