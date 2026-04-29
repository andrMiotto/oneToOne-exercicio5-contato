package senai.weg.exercicio5.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import senai.weg.exercicio5.dto.livro.LivroRequest;
import senai.weg.exercicio5.dto.livro.LivroResponse;
import senai.weg.exercicio5.mapper.LivroMapper;
import senai.weg.exercicio5.model.Livro;
import senai.weg.exercicio5.repository.LivroRepository;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroMapper livroMapper;
    private final LivroRepository livroRepository;

    public LivroResponse create(LivroRequest livroRequest) {

        Livro livro = livroMapper.toEntity(livroRequest);

        Livro livroSalvo = livroRepository.save(livro);

        LivroResponse livroResponse = livroMapper.toResponse(livroSalvo);

        return livroResponse;

    }

    public List<LivroResponse> listAll() {
        List<Livro> livros = livroRepository.findAll();

        if (livros.isEmpty()) {
            throw new RuntimeException("nao existe nenhuma Livro cadastrado");
        } else {
            List<LivroResponse> dtos = new ArrayList<>();

            for (Livro livro : livros) {
                dtos.add(livroMapper.toResponse(livro));
            }
            return dtos;
        }

    }

    public LivroResponse findById(long id) {

        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nao existe nenhum Livro com esse id"));

        return livroMapper.toResponse(livro);
    }

    public LivroResponse update(long id, LivroRequest livroRequest) {

        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nao existe nenhum Livro com esse id"));

        livro.setTitulo(livroRequest.titulo());
        livro.setAutor(livroRequest.autor());

        Livro livroSalvo = livroRepository.save(livro);

        return livroMapper.toResponse(livroSalvo);

    }

    public void delete(long id) {

        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nao existe nenhum Livro com esse id"));

        livroRepository.delete(livro);

    }

}
