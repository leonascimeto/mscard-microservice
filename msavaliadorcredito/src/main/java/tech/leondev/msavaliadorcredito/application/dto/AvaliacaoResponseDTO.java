package tech.leondev.msavaliadorcredito.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AvaliacaoResponseDTO {
   private List<CartaoClienteDTO> cartoes;
}
