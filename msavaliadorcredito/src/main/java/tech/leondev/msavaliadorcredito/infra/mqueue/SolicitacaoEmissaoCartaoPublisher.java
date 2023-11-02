package tech.leondev.msavaliadorcredito.infra.mqueue;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import tech.leondev.msavaliadorcredito.application.dto.SolicitacaoEmissaoCartaoDTO;

@RequiredArgsConstructor
@Component
public class SolicitacaoEmissaoCartaoPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final Queue queueEmissaoCartoes;

    public void solicitarCartao(SolicitacaoEmissaoCartaoDTO dados){
        var json = converrIntoJson(dados);
        rabbitTemplate.convertAndSend(queueEmissaoCartoes.getName(), json);
    }

    @SneakyThrows
    private String converrIntoJson(SolicitacaoEmissaoCartaoDTO dados){
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(dados);
    }
}
