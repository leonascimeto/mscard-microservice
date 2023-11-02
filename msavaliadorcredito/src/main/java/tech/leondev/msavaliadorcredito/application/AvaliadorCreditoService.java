package tech.leondev.msavaliadorcredito.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.leondev.msavaliadorcredito.application.dto.*;
import tech.leondev.msavaliadorcredito.domain.DadosCliente;
import tech.leondev.msavaliadorcredito.domain.SituacaoCliente;
import tech.leondev.msavaliadorcredito.infra.clients.CartaoResourceClient;
import tech.leondev.msavaliadorcredito.infra.clients.ClienteResourceClient;
import tech.leondev.msavaliadorcredito.infra.mqueue.SolicitacaoEmissaoCartaoPublisher;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AvaliadorCreditoService {
    private final ClienteResourceClient clienteResourceClient;
    private final CartaoResourceClient cartaoResourceClient;
    private final SolicitacaoEmissaoCartaoPublisher emissaoCartaoPublisher;

    public SituacaoCliente obterSituacaoClienteCpf(String cpf) {
        ResponseEntity<DadosCliente> dadosCliente = clienteResourceClient.dadosCliente(cpf);
        ResponseEntity<List<CartaoClienteDTO>> cartoes = cartaoResourceClient.getCartoesByCliente(cpf);
        return SituacaoCliente
                .builder()
                .cliente(dadosCliente.getBody())
                .cartoes(cartoes.getBody())
                .build();
    }

    public AvaliacaoResponseDTO realizarAvalicao(String cpf, Long renda) {
        DadosCliente dadosCliente = clienteResourceClient.dadosCliente(cpf).getBody();
        List<CartaoDTO> cartoesByRenda = cartaoResourceClient.getCartoesByRenda(renda).getBody();

        List<CartaoClienteDTO> catoesAprovados = cartoesByRenda.stream().map(cartao -> {
            BigDecimal limiteBasico = cartao.getLimiteBasico();
            BigDecimal rendaBD = BigDecimal.valueOf(renda);
            BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getIdade());
            BigDecimal fator = idadeBD.divide(BigDecimal.valueOf(10));
            BigDecimal limiteAprovado = fator.multiply(limiteBasico);

            CartaoClienteDTO aprovado = new CartaoClienteDTO();
            aprovado.setNome(cartao.getNome());
            aprovado.setBandeira(cartao.getBandeira());
            aprovado.setLimiteLiberado(limiteAprovado);

            return aprovado;
        }).collect(Collectors.toList());

        return new AvaliacaoResponseDTO(catoesAprovados);
    }

    public ProtocoloSolicitacaoCartaoDTO solicitarEmissaoCartao(SolicitacaoEmissaoCartaoDTO dados){
        emissaoCartaoPublisher.solicitarCartao(dados);
        String protocolo = UUID.randomUUID().toString();
        return new ProtocoloSolicitacaoCartaoDTO(protocolo);
    }
}