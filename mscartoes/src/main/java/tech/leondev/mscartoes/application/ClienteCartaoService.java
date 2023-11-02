package tech.leondev.mscartoes.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.leondev.mscartoes.domain.ClienteCartao;
import tech.leondev.mscartoes.infra.repository.ClienteCartaoRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClienteCartaoService {
    private final ClienteCartaoRepository repository;

    public List<ClienteCartao> listCartoesByCpf(String cpf){
        return repository.findByCpf(cpf);
    }
}
