package senai.weg.exercicio5.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import senai.weg.exercicio5.dto.contato.ContatoResponse;
import senai.weg.exercicio5.dto.contato.ContatoRequest;
import senai.weg.exercicio5.mapper.ContatoMapper;
import senai.weg.exercicio5.model.Contato;
import senai.weg.exercicio5.repository.ContatoRepository;

@Service
@RequiredArgsConstructor
public class ContatoService {

    private final ContatoMapper contatoMapper;
    private final ContatoRepository contatoRepository;

    public ContatoResponse create(ContatoRequest contatoRequest) {

        Contato contato = contatoMapper.toEntity(contatoRequest);

        Contato contatoSalvo = contatoRepository.save(contato);

        ContatoResponse contatoResponse = contatoMapper.toResponse(contatoSalvo);

        return contatoResponse;

    }

    public List<ContatoResponse> listAll() {
        List<Contato> contatos = contatoRepository.findAll();

        if (contatos.isEmpty()) {
            throw new RuntimeException("nao existe nenhum contato cadastrado");
        } else {
            List<ContatoResponse> dtos = new ArrayList<>();

            for (Contato contato : contatos) {
                dtos.add(contatoMapper.toResponse(contato));
            }
            return dtos;
        }

    }

    public ContatoResponse findById(long id) {

        Contato contato = contatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nao existe nenhum contato com esse id"));

        return contatoMapper.toResponse(contato);
    }

    public ContatoResponse update(long id, ContatoRequest contatoRequest) {

        Contato contato = contatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nao existe nenhum contato com esse id"));

        contato.setEmail(contatoRequest.email());
        contato.setTelefone(contatoRequest.telefone());

        Contato contatoSalvo = contatoRepository.save(contato);

        return contatoMapper.toResponse(contatoSalvo);

    }

    public void delete(long id) {

        Contato contato = contatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nao existe nenhum contato com esse id"));

        contatoRepository.delete(contato);

    }
}
