package lucas.rayzer.Exercicio1.Users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Fornecedor {
    @Id
    @GeneratedValue
    private Integer id_fornecedor;
    @Size(max = 45)
    private String nome;

    public Fornecedor(Integer id_fornecedor, String nome) {
        this.id_fornecedor = id_fornecedor;
        this.nome = nome;
    }

    public Fornecedor() {
    }

    public Integer getId_fornecedor() {
        return id_fornecedor;
    }

    public void setId_fornecedor(Integer id_fornecedor) {
        this.id_fornecedor = id_fornecedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "id_fornecedor=" + id_fornecedor +
                ", nome='" + nome + '\'' +
                '}';
    }

    @OneToMany(mappedBy = "fornecedor")
    @JsonIgnore
    private List<Produto> produtos;

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

}
