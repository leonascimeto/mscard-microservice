package tech.leondev.msclientes.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.leondev.msclientes.domain.Cliente;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);
}
