package senai.weg.exercicio5.dto.livro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LivroResponse {

    private long id;
    private String titulo;
    private String autor;
}
