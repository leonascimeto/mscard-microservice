package tech.leondev.msavaliadorcredito.application;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.leondev.msavaliadorcredito.application.dto.AvaliacaoResponseDTO;
import tech.leondev.msavaliadorcredito.application.dto.DadosAvaliacaoDTO;
import tech.leondev.msavaliadorcredito.application.dto.ProtocoloSolicitacaoCartaoDTO;
import tech.leondev.msavaliadorcredito.application.dto.SolicitacaoEmissaoCartaoDTO;
import tech.leondev.msavaliadorcredito.domain.SituacaoCliente;

@RequiredArgsConstructor
@RequestMapping("avaliacoes-credito")
@RestController
public class AvaliadorCreditoController {
    private final AvaliadorCreditoService avaliadorCreditoService;
    @GetMapping
    public String status(){
        return "OK";
    }

    @GetMapping(value= "situacao-cliente", params = "cpf")
    public ResponseEntity<SituacaoCliente> consultaSituacaoCliente(@RequestParam("cpf") String cpf){
        SituacaoCliente situacaoCliente = avaliadorCreditoService.obterSituacaoClienteCpf(cpf);
        return ResponseEntity.ok(situacaoCliente);
    }

    @PostMapping
    private ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacaoDTO dados){
        AvaliacaoResponseDTO response = avaliadorCreditoService.realizarAvalicao(dados.getCpf(), dados.getRenda());
        return ResponseEntity.ok(response);
    }

    @PostMapping("solicitacoes-cartao")
    private ResponseEntity solicitarCartao(@RequestBody SolicitacaoEmissaoCartaoDTO dados){
        ProtocoloSolicitacaoCartaoDTO protocolo = avaliadorCreditoService
                .solicitarEmissaoCartao(dados);
        return ResponseEntity.ok(protocolo);
    }
}
