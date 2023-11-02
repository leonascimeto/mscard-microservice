package tech.leondev.mscartoes.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import tech.leondev.mscartoes.application.dto.SolicitacaoEmissaoCartaoDTO;
import tech.leondev.mscartoes.domain.Cartao;
import tech.leondev.mscartoes.domain.ClienteCartao;
import tech.leondev.mscartoes.infra.repository.CartaoRepository;
import tech.leondev.mscartoes.infra.repository.ClienteCartaoRepository;

@RequiredArgsConstructor
@Component
public class EmissaoCartaoSubscriber {
    private final CartaoRepository cartaoRepository;
    private final ClienteCartaoRepository clienteCartaoRepository;

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receberSolicitacaoEmissao(@Payload String payload) throws JsonProcessingException {
        try {
            var mapper = new ObjectMapper();
            SolicitacaoEmissaoCartaoDTO dados = mapper.readValue(payload, SolicitacaoEmissaoCartaoDTO.class);
            Cartao cartao = cartaoRepository.findById(dados.getIdCartao())
                    .orElseThrow();
            ClienteCartao clienteCartao = new ClienteCartao();
            clienteCartao.setCartao(cartao);
            clienteCartao.setCpf(dados.getCpf());
            clienteCartao.setLimite(dados.getLimiteLiberado());

            clienteCartaoRepository.save(clienteCartao);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
