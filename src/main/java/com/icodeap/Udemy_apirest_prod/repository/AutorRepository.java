package com.icodeap.Udemy_apirest_prod.repository;

import com.icodeap.Udemy_apirest_prod.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

//Gestiona la entidad Autor
public interface AutorRepository extends JpaRepository<Autor, Long> {


}
