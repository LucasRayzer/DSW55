package lucas.rayzer.Exercicio1.Users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Produto {
    @Id
    @GeneratedValue
    private Integer id_produto;
    @Size(max = 45)
    private String descricao;
    private float valor;

    public Produto(int id_produto, String descricao, float valor) {
        this.id_produto = id_produto;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Produto() {

    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id_produto=" + id_produto +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                '}';
    }

        @ManyToOne(fetch = FetchType.LAZY)
        @JsonIgnore
        private Fornecedor fornecedor;

    public Fornecedor getForn() {
        return fornecedor;
    }

    public void setForn(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    @OneToMany(mappedBy = "produto")
    @JsonIgnore
    private List<Cliente> clientes;

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}
