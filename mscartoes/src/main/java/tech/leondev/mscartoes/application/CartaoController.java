package tech.leondev.mscartoes.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.leondev.mscartoes.application.dto.CartaoSaveDTO;
import tech.leondev.mscartoes.application.dto.CartoesPorCpfResponseDTO;
import tech.leondev.mscartoes.domain.Cartao;
import tech.leondev.mscartoes.domain.ClienteCartao;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("cartoes")
@RestController
public class CartaoController {
    private final CartaoService cartaoService;
    private final ClienteCartaoService clienteCartaoService;

    @GetMapping
    private String status(){
      return "OK";
    }

    @PostMapping
    private ResponseEntity cadastra(@RequestBody CartaoSaveDTO request){
        Cartao cartao = request.toModel();
        cartaoService.save(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "renda")
    private ResponseEntity<List<Cartao>> listarPorRenda(@RequestParam("renda") Long renda){
        List<Cartao> response = cartaoService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(response);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesPorCpfResponseDTO>> getCartoesByCliente(@RequestParam("cpf") String cpf){
        List<ClienteCartao> lista = clienteCartaoService.listCartoesByCpf(cpf);
        List<CartoesPorCpfResponseDTO> resultList = lista.stream().map(CartoesPorCpfResponseDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }
}
