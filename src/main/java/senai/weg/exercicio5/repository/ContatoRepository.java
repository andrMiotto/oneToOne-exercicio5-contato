package senai.weg.exercicio5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import senai.weg.exercicio5.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

}
