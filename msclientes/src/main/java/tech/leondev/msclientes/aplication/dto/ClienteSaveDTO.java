package tech.leondev.msclientes.aplication.dto;

import lombok.Data;
import tech.leondev.msclientes.domain.Cliente;

@Data
public class ClienteSaveDTO {
    private String cpf;
    private String nome;
    private Integer idade;

    public Cliente toModel(){
        return new Cliente(cpf, nome, idade);
    }
}
