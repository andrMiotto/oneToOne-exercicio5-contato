package senai.weg.exercicio5.dto.usuario;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import senai.weg.exercicio5.dto.contato.ContatoResponse;
import senai.weg.exercicio5.dto.livro.LivroResponse;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {

    private long id;
    private String nome;
    private ContatoResponse contato;
    private List<LivroResponse> livrosEmprestados = new ArrayList<>();
}
