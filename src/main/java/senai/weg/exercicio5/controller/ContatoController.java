package senai.weg.exercicio5.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import senai.weg.exercicio5.dto.contato.ContatoRequest;
import senai.weg.exercicio5.dto.contato.ContatoResponse;
import senai.weg.exercicio5.service.ContatoService;

@RestController
@RequestMapping("contato")
@RequiredArgsConstructor
public class ContatoController {

    private final ContatoService contatoService;

    @PostMapping("/register")
    public ResponseEntity<ContatoResponse> create(@RequestBody ContatoRequest contatoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(contatoService.create(contatoRequest));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ContatoResponse>> listAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(contatoService.listAll());
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<ContatoResponse> findById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(contatoService.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ContatoResponse> update(@PathVariable long id, @RequestBody ContatoRequest contatoRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(contatoService.update(id, contatoRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        contatoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
