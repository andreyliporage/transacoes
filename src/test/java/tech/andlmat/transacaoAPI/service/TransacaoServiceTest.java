package tech.andlmat.transacaoAPI.service;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tech.andlmat.transacaoAPI.builders.TransacaoBuilder;
import tech.andlmat.transacaoAPI.domain.Transacao;
import tech.andlmat.transacaoAPI.infra.TransacaoRepository;
import tech.andlmat.transacaoAPI.service.impl.TransacaoServiceImpl;

@DisplayName("Serviço: TransacaoService")
public class TransacaoServiceTest {

    @Mock
    TransacaoRepository repository;

    @InjectMocks
    TransacaoServiceImpl service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        TransacaoRepository.transacoes.add(TransacaoBuilder.umaTransacao().build());
    }

    @Test
    @DisplayName("Deve persistir uma transação com sucesso")
    public void devePersistirUmaTransacaoComSucesso() {
        Transacao transacao = TransacaoBuilder.umaTransacao().build();

        Mockito.when(repository.post(transacao))
                        .thenReturn(transacao);

        service.post(transacao);

        Assertions.assertEquals(2, TransacaoRepository.transacoes.size());
    }

    @Test
    @DisplayName("Deve limpar lista de transação")
    public void deveLimparListaDeTransacao() {
        Assertions.assertEquals(1, TransacaoRepository.transacoes.size());

        service.delete();

        Assertions.assertEquals(0, TransacaoRepository.transacoes.size());
    }
}
