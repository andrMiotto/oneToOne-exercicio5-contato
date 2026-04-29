package senai.weg.exercicio5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import senai.weg.exercicio5.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
