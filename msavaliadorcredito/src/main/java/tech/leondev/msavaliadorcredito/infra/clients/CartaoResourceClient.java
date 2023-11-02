package tech.leondev.msavaliadorcredito.infra.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tech.leondev.msavaliadorcredito.application.dto.CartaoClienteDTO;
import tech.leondev.msavaliadorcredito.application.dto.CartaoDTO;

import java.util.List;

@FeignClient(value = "mscartoes", path = "/cartoes")
public interface CartaoResourceClient {
    @GetMapping(params = "cpf")
    ResponseEntity<List<CartaoClienteDTO>> getCartoesByCliente(@RequestParam("cpf") String cpf);

    @GetMapping(params = "renda")
    ResponseEntity<List<CartaoDTO>> getCartoesByRenda(@RequestParam("renda") Long renda);

}
