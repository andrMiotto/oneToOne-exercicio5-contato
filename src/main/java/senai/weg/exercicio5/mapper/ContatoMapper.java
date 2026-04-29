package senai.weg.exercicio5.mapper;

import org.springframework.stereotype.Component;

import senai.weg.exercicio5.dto.contato.ContatoRequest;
import senai.weg.exercicio5.dto.contato.ContatoResponse;
import senai.weg.exercicio5.model.Contato;

@Component
public class ContatoMapper {

    public Contato toEntity(ContatoRequest request) {
        if (request == null) {
            return null;
        }

        return new Contato(
                request.email(),
                request.telefone());
    }

    public ContatoResponse toResponse(Contato contato) {
        if (contato == null) {
            return null;
        }

        return new ContatoResponse(
                contato.getId(),
                contato.getEmail(),
                contato.getTelefone());
    }
}
