package lucas.rayzer.Exercicio1.Users;

import jakarta.validation.Valid;
import lucas.rayzer.Exercicio1.jpa.FornecedorRepository;
import lucas.rayzer.Exercicio1.jpa.Produtorepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class FornecedorResources {

    private FornecedorRepository fornecedorRepository;
    private Produtorepository produtorepository;

    public FornecedorResources(FornecedorRepository fornecedorRepository, Produtorepository produtorepository) {
        this.fornecedorRepository = fornecedorRepository;
        this.produtorepository = produtorepository;
    }

    @GetMapping("/fornecedores")
    public List<Fornecedor> listarFornecedores(){return fornecedorRepository.findAll();}

    @PostMapping("/fornecedores")
    public ResponseEntity<Fornecedor> createFornecedor(@Valid @RequestBody Fornecedor forn){
        Fornecedor savedFornecedor = fornecedorRepository.save(forn);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{ID}")
                .buildAndExpand(savedFornecedor.getId_fornecedor())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @GetMapping("/fornecedores/{id}/produtos")
    public List<Produto> listarProdutosDeForn(@PathVariable int id){
        Optional <Fornecedor> fornecedores = fornecedorRepository.findById(id);
        return fornecedores.get().getProdutos();
    }
    @PostMapping("/fornecedores/{id}/produtos")
    public ResponseEntity<Object> criarProdutoDeForn (@PathVariable int id, @Valid @RequestBody Produto prod) {
        Optional <Fornecedor> fornecedor = fornecedorRepository.findById(id);
        prod.setForn(fornecedor.get());
        Produto prodSaved = produtorepository.save(prod);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{ID}")
                .buildAndExpand(prodSaved.getId_produto())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
