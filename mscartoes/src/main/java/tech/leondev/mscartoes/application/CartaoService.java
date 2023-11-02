package tech.leondev.mscartoes.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.leondev.mscartoes.domain.Cartao;
import tech.leondev.mscartoes.infra.repository.CartaoRepository;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CartaoService {
    private final CartaoRepository repository;

    @Transactional
    public Cartao save(Cartao cartao){
        return repository.save(cartao);
    }

    public List<Cartao> getCartoesRendaMenorIgual(Long renda){
        BigDecimal rendaConverted = BigDecimal.valueOf(renda);
        return repository.findByRendaLessThanEqual(rendaConverted);
    }
}
