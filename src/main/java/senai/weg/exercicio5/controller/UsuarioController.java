package senai.weg.exercicio5.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import senai.weg.exercicio5.dto.usuario.EmprestimoRequest;
import senai.weg.exercicio5.dto.usuario.UsuarioRequest;
import senai.weg.exercicio5.dto.usuario.UsuarioResponse;
import senai.weg.exercicio5.service.UsuarioService;

@RestController
@RequestMapping("usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponse> create(@RequestBody UsuarioRequest usuarioRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.create(usuarioRequest));
    }

    @GetMapping("/list")
    public ResponseEntity<List<UsuarioResponse>> listAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(usuarioService.listAll());
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<UsuarioResponse> findById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(usuarioService.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UsuarioResponse> update(@PathVariable long id, @RequestBody UsuarioRequest usuarioRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(usuarioService.update(id, usuarioRequest));
    }

    @PutMapping("/emprestar")
    public ResponseEntity<UsuarioResponse> emprestarLivro(@RequestBody EmprestimoRequest emprestimoRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(usuarioService.emprestarLivro(emprestimoRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        usuarioService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
