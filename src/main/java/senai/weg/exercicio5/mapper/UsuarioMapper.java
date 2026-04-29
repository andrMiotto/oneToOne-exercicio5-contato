package senai.weg.exercicio5.mapper;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import senai.weg.exercicio5.dto.usuario.UsuarioRequest;
import senai.weg.exercicio5.dto.usuario.UsuarioResponse;
import senai.weg.exercicio5.model.Usuario;

@Component
@RequiredArgsConstructor
public class UsuarioMapper {

    private final ContatoMapper contatoMapper;
    private final LivroMapper livroMapper;

    public Usuario toEntity(UsuarioRequest request) {
        if (request == null) {
            return null;
        }

        return new Usuario(
                request.nome(),
                contatoMapper.toEntity(request.contato()));
    }

    public UsuarioResponse toResponse(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                contatoMapper.toResponse(usuario.getContato()),
                livroMapper.toResponseList(usuario.getLivrosEmprestados()));
    }
}
