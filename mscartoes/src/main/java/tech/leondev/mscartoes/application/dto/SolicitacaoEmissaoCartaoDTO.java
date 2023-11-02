package tech.leondev.mscartoes.application.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SolicitacaoEmissaoCartaoDTO {
    private Long idCartao;
    private String cpf;
    private String endereco;
    private BigDecimal limiteLiberado;
}
