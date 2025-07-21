package me.lucasgusmao.livraria_api.repository;

import me.lucasgusmao.livraria_api.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LivroRepository extends JpaRepository<Livro, UUID> {
}
