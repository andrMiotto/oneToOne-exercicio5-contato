package senai.weg.exercicio5.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import senai.weg.exercicio5.dto.livro.LivroRequest;
import senai.weg.exercicio5.dto.livro.LivroResponse;
import senai.weg.exercicio5.model.Livro;

@Component
public class LivroMapper {

    public Livro toEntity(LivroRequest request) {
        if (request == null) {
            return null;
        }

        return new Livro(
                request.titulo(),
                request.autor());
    }

    public LivroResponse toResponse(Livro livro) {
        if (livro == null) {
            return null;
        }

        return new LivroResponse(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor());
    }

    public List<LivroResponse> toResponseList(List<Livro> livros) {
        if (livros == null) {
            return List.of();
        }

        return livros.stream()
                .map(this::toResponse)
                .toList();
    }
}
