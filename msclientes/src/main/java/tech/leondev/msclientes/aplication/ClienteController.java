package tech.leondev.msclientes.aplication;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.leondev.msclientes.aplication.dto.ClienteSaveDTO;
import tech.leondev.msclientes.domain.Cliente;

import java.net.URI;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("clientes")
public class ClienteController {
    private final ClienteService service;

    @GetMapping
    private String status(){
        return "OK";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ClienteSaveDTO request){
        Cliente cliente = request.toModel();
        service.save(cliente);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(cliente.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity dadosCliente(@RequestParam("cpf") String cpf){
        var cliente = service.getByCpf(cpf);
        if(cliente.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cliente);
    }

}
