package senai.weg.exercicio5.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "contato_id")
    private Contato contato;

    @ManyToMany
    @JoinTable(name = "usuario_livro", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "livro_id"))
    private List<Livro> livrosEmprestados = new ArrayList<>();

    public Usuario(String nome) {
        this.nome = nome;
    }

    public Usuario(String nome, Contato contato) {
        this.nome = nome;
        this.contato = contato;
    }

    public void emprestarLivro(Livro livro) {
        this.livrosEmprestados.add(livro);
    }

}
