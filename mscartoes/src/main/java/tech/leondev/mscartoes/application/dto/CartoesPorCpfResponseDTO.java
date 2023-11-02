package tech.leondev.mscartoes.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.leondev.mscartoes.domain.ClienteCartao;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartoesPorCpfResponseDTO {
    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;

    public static CartoesPorCpfResponseDTO fromModel(ClienteCartao model){
        return new CartoesPorCpfResponseDTO(
                model.getCartao().getNome(),
                model.getCartao().getBandeira().toString(),
                model.getCartao().getLimiteBasico()
        );
    }
}
