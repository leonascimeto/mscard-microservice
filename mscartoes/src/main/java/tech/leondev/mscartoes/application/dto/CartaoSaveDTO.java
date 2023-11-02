package tech.leondev.mscartoes.application.dto;

import lombok.Data;
import tech.leondev.mscartoes.domain.BandeiraCartao;
import tech.leondev.mscartoes.domain.Cartao;

import java.math.BigDecimal;

@Data
public class CartaoSaveDTO {
    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limiteBasico;

    public Cartao toModel(){
        return new Cartao(nome, bandeira, renda, limiteBasico);
    }
}
