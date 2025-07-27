package me.lucasgusmao.livraria_api.repository;

import me.lucasgusmao.livraria_api.model.Autor;
import me.lucasgusmao.livraria_api.model.GeneroLivro;
import me.lucasgusmao.livraria_api.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface LivroRepository extends JpaRepository<Livro, UUID> {

    List<Livro> findByAutor(Autor autor);

    @Query("SELECT a FROM Livro l JOIN l.autor a ")
    List<Autor> listarAutoresDosLivros();

    @Query("SELECT DISTINCT l.titulo FROM Livro l ")
    List<String> listarTitulosDosLivros();

    @Query("""
        SELECT l.genero
        FROM Livro l
        JOIN l.autor a
        WHERE a.nacionalidade = 'Brasileiro'
        ORDER BY l.genero
""")
    List<Livro> listarGenerosAutoresBrasileiros();

//    @Query("SELECT l FROM Livro l WHERE l.genero = :genero ORDER BY :paramOrdenacao")
//    List<Livro> findByGenero(@Param("genero") GeneroLivro generoLivro, @Param("paramOrdenacao") String nomePropriedade);

    @Query("SELECT l FROM Livro l WHERE l.genero = ?1 ORDER BY ?2 ")
    List<Livro> findByGenero(GeneroLivro generoLivro, String nomePropriedade);

    @Modifying
    @Transactional
    @Query("DELETE FROM Livro WHERE genero = ?1 ")
    void deleteByGenero(GeneroLivro generoLivro);

    boolean existsByAutor(Autor autor);

}
