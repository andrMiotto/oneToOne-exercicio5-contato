package senai.weg.exercicio5.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import senai.weg.exercicio5.dto.livro.LivroRequest;
import senai.weg.exercicio5.dto.livro.LivroResponse;
import senai.weg.exercicio5.service.LivroService;

@RestController
@RequestMapping("livro")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;

    @PostMapping("/register")
    public ResponseEntity<LivroResponse> create(@RequestBody LivroRequest livroRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(livroService.create(livroRequest));
    }

    @GetMapping("/list")
    public ResponseEntity<List<LivroResponse>> listAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(livroService.listAll());
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<LivroResponse> findById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(livroService.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LivroResponse> update(@PathVariable long id, @RequestBody LivroRequest livroRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(livroService.update(id, livroRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        livroService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
