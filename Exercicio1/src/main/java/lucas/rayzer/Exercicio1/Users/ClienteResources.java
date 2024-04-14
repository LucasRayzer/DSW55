package lucas.rayzer.Exercicio1.Users;

import jakarta.validation.Valid;
import lucas.rayzer.Exercicio1.jpa.ClienteRepository;
import lucas.rayzer.Exercicio1.jpa.Produtorepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class ClienteResources {
    private ClienteRepository clienteRepository;
    private Produtorepository produtorepository;

    public ClienteResources(ClienteRepository clienteRepository, Produtorepository produtorepository) {
        this.clienteRepository = clienteRepository;
        this.produtorepository = produtorepository;
    }

    @GetMapping("/client")
    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    @PostMapping("/client")
    public ResponseEntity<Cliente> createClient(@Valid @RequestBody Cliente client){
        Cliente savedClient = clienteRepository.save(client);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{ID}")
                .buildAndExpand(savedClient.getId_cliente())
                .toUri();
                return ResponseEntity.created(location).build();
    }

    @GetMapping("/produtos/{id}/client")
    public List<Cliente> listarClientes(@PathVariable int id ){
        Optional<Produto> prod = produtorepository.findById(id);
        return prod.get().getClientes();
    }

    @PostMapping("/produtos/{id}/client")
    public ResponseEntity<Object> criarProdutoCliente(@PathVariable int id, @Valid @RequestBody Cliente cliente){
        Optional <Produto> prod = produtorepository.findById(id);
        cliente.setProduto(prod.get());
        Cliente clienteSaved = clienteRepository.save(cliente);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clienteSaved.getId_cliente())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
