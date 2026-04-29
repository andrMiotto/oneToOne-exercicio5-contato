package senai.weg.exercicio5.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import senai.weg.exercicio5.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @EntityGraph(attributePaths = {"contato", "livrosEmprestados"})
    @Query("""
            select distinct u
            from Usuario u
            left join fetch u.contato
            left join fetch u.livrosEmprestados
            where u.id = :id
            """)
    Optional<Usuario> findByIdWithContatoAndLivrosEmprestados(@Param("id") Long id);
}
