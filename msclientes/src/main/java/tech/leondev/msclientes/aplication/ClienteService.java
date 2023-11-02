package tech.leondev.msclientes.aplication;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.leondev.msclientes.domain.Cliente;
import tech.leondev.msclientes.infra.repository.ClienteRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClienteService {
    private final ClienteRepository repository;

    @Transactional
    public Cliente save(Cliente cliente){
        return repository.save(cliente);
    }

    @Transactional(readOnly = true)
    public Optional<Cliente> getByCpf(String cpf){
        return repository.findByCpf(cpf);
    }
}
