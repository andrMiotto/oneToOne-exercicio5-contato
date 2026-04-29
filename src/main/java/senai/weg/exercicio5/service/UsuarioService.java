package senai.weg.exercicio5.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import senai.weg.exercicio5.dto.usuario.EmprestimoRequest;
import senai.weg.exercicio5.dto.usuario.UsuarioRequest;
import senai.weg.exercicio5.dto.usuario.UsuarioResponse;
import senai.weg.exercicio5.mapper.UsuarioMapper;
import senai.weg.exercicio5.model.Livro;
import senai.weg.exercicio5.model.Usuario;
import senai.weg.exercicio5.repository.LivroRepository;
import senai.weg.exercicio5.repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;
    private final LivroRepository livroRepository;

    public UsuarioResponse create(UsuarioRequest usuarioRequest) {

        Usuario usuario = usuarioMapper.toEntity(usuarioRequest);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return usuarioMapper.toResponse(usuarioSalvo);
    }

    public List<UsuarioResponse> listAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        if (usuarios.isEmpty()) {
            throw new RuntimeException("nao existe nenhum usuario cadastrado");
        } else {
            List<UsuarioResponse> dtos = new ArrayList<>();

            for (Usuario usuario : usuarios) {
                dtos.add(findById(usuario.getId()));
            }
            return dtos;
        }
    }

    public UsuarioResponse findById(long id) {

        Usuario usuario = usuarioRepository.findByIdWithContatoAndLivrosEmprestados(id)
                .orElseThrow(() -> new RuntimeException("Nao existe nenhum usuario com esse id"));

        return usuarioMapper.toResponse(usuario);
    }

    public UsuarioResponse update(long id, UsuarioRequest usuarioRequest) {

        Usuario usuario = usuarioRepository.findByIdWithContatoAndLivrosEmprestados(id)
                .orElseThrow(() -> new RuntimeException("Nao existe nenhum usuario com esse id"));

        usuario.setNome(usuarioRequest.nome());

        if (usuarioRequest.contato() != null) {
            if (usuario.getContato() == null) {
                usuario.setContato(usuarioMapper.toEntity(usuarioRequest).getContato());
            } else {
                usuario.getContato().setEmail(usuarioRequest.contato().email());
                usuario.getContato().setTelefone(usuarioRequest.contato().telefone());
            }
        }

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return usuarioMapper.toResponse(usuarioSalvo);
    }

    public UsuarioResponse emprestarLivro(EmprestimoRequest emprestimoRequest) {

        Usuario usuario = usuarioRepository.findByIdWithContatoAndLivrosEmprestados(emprestimoRequest.usuarioId())
                .orElseThrow(() -> new RuntimeException("Nao existe nenhum usuario com esse id"));

        Livro livro = livroRepository.findById(emprestimoRequest.livroId())
                .orElseThrow(() -> new RuntimeException("Nao existe nenhum Livro com esse id"));

        boolean livroJaEmprestado = usuario.getLivrosEmprestados().stream()
                .anyMatch(livroEmprestado -> livroEmprestado.getId() == livro.getId());

        if (livroJaEmprestado) {
            throw new RuntimeException("Esse livro ja foi emprestado para esse usuario");
        }

        usuario.emprestarLivro(livro);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return usuarioMapper.toResponse(usuarioSalvo);
    }

    public void delete(long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nao existe nenhum usuario com esse id"));

        usuarioRepository.delete(usuario);
    }
}
