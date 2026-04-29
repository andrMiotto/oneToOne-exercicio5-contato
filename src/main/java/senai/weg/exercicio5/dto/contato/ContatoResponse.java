package senai.weg.exercicio5.dto.contato;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContatoResponse {

    private long id;
    private String email;
    private String telefone;
}
