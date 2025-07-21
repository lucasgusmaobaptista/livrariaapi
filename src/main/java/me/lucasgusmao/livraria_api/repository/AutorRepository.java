package me.lucasgusmao.livraria_api.repository;

import me.lucasgusmao.livraria_api.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AutorRepository extends JpaRepository<Autor, UUID> {
}
