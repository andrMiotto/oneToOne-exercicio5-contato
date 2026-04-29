package senai.weg.exercicio5.dto.usuario;

import senai.weg.exercicio5.dto.contato.ContatoRequest;

public record UsuarioRequest(
        String nome,
        ContatoRequest contato) {
}
