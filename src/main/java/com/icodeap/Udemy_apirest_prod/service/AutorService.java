package com.icodeap.Udemy_apirest_prod.service;

import com.icodeap.Udemy_apirest_prod.entity.Autor;
import com.icodeap.Udemy_apirest_prod.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService  {
    private AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }
    public Autor saveAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    public List<Autor> findAll() {
        return autorRepository.findAll();
    }

    public Optional<Autor> findById(Long id) {
        return autorRepository.findById(id);
    }

    public boolean deleteById(Long id) {
        return autorRepository.findById(id).map(
                autor -> {
                    autorRepository.delete(autor);
                    return true;
                }
        ).orElse(false);

    }

    public  Optional<Autor> updateAutor(Autor autor) {

        return autorRepository.findById(autor.getId()).map(
                a ->{
                    a.setNombre(autor.getNombre());
                    a.setApellido(autor.getApellido());
                    a.setTelefono(autor.getTelefono());
                    return autorRepository.save(a);
                }
        );



    }
}
