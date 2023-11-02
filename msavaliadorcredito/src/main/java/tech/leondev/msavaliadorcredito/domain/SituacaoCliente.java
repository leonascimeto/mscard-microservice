package tech.leondev.msavaliadorcredito.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.leondev.msavaliadorcredito.application.dto.CartaoClienteDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SituacaoCliente {
    private DadosCliente cliente;
    private List<CartaoClienteDTO> cartoes;
}
