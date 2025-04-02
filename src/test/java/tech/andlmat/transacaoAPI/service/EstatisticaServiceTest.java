package tech.andlmat.transacaoAPI.service;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tech.andlmat.transacaoAPI.builders.TransacaoBuilder;
import tech.andlmat.transacaoAPI.domain.Estatistica;
import tech.andlmat.transacaoAPI.infra.TransacaoRepository;
import tech.andlmat.transacaoAPI.service.impl.EstatisticaServiceImpl;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@DisplayName("Serviço: EstatisticaService")
public class EstatisticaServiceTest {

    @Mock
    TransacaoRepository repository;

    @InjectMocks
    EstatisticaServiceImpl service;

    @BeforeEach
    public void setup() {
        var transacao01 = TransacaoBuilder.umaTransacao().comValor(120.0).comDataHora(OffsetDateTime.now()).build();
        var transacao02 = TransacaoBuilder.umaTransacao().comValor(77.0).comDataHora(OffsetDateTime.now()).build();

        TransacaoService.transacoes.addAll(Arrays.asList(transacao01, transacao02));
    }

    @AfterEach
    public void tearDown() {
        TransacaoService.transacoes.clear();
    }

    public EstatisticaServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve retornar quantidade correta de itens na estatistica")
    public void deveRetornarQuantidadeCorretaDeItensNaEstatistica() {
        Estatistica estatistica = service.get("60");

        Assertions.assertEquals(2, estatistica.getCount());
    }

    @Test
    @DisplayName("Deve retornar soma correta na estatistica")
    public void deveRetornarSomaCorretaNaEstatistica() {
        Estatistica estatistica = service.get("60");

        Assertions.assertEquals(197.0, estatistica.getSum());
    }

    @Test
    @DisplayName("Deve retornar media correta na estatistica")
    public void deveRetornarMediaCorretaNaEstatistica() {
        Estatistica estatistica = service.get("60");

        Assertions.assertEquals(98.5, estatistica.getAvg());
    }

    @Test
    @DisplayName("Deve retornar valor mínimo correto na estatistica")
    public void deveRetornarValorMinimoCorretoNaEstatistica() {
        Estatistica estatistica = service.get("60");

        Assertions.assertEquals(77.0, estatistica.getMin());
    }

    @Test
    @DisplayName("Deve retornar valor máximo correto na estatistica")
    public void deveRetornarValorMaximoCorretoNaEstatistica() {
        Estatistica estatistica = service.get("60");

        Assertions.assertEquals(120.0, estatistica.getMax());
    }

    @Test
    @DisplayName("Deve usar as transações de até 60 segundos atrás")
    public void deveUsarAsTransacoesDeAte60SegundosAtras() {
        var transacaoComMaisDe60Segundos01 = TransacaoBuilder.umaTransacao().comValor(99.7).comDataHora(OffsetDateTime.now().minusSeconds(60)).build();
        var transacao03 = TransacaoBuilder.umaTransacao().comValor(10.0).build();
        var transacaoComMaisDe60Segundos02 = TransacaoBuilder.umaTransacao().comDataHora(OffsetDateTime.now().minusSeconds(120)).build();
        var transacao04 = TransacaoBuilder.umaTransacao().comValor(300.0).build();

        TransacaoService.transacoes.addAll(Arrays.asList(transacaoComMaisDe60Segundos01, transacao03, transacao04, transacaoComMaisDe60Segundos02));

        Estatistica estatistica = service.get("60");

        Assertions.assertEquals(4, estatistica.getCount());
        Assertions.assertEquals(126.75, estatistica.getAvg());
        Assertions.assertEquals(10.0, estatistica.getMin());
        Assertions.assertEquals(300.0, estatistica.getMax());
    }

    @Test
    @DisplayName("Deve usar as transações de até 120 segundos atrás")
    public void deveUsarAsTransacoesDeAte120SegundosAtras() {
        var transacaoComMaisDe60Segundos01 = TransacaoBuilder.umaTransacao().comValor(99.7).comDataHora(OffsetDateTime.now().minusSeconds(120)).build();
        var transacao03 = TransacaoBuilder.umaTransacao().comValor(10.0).build();
        var transacaoComMaisDe60Segundos02 = TransacaoBuilder.umaTransacao().comDataHora(OffsetDateTime.now().minusSeconds(120)).build();
        var transacao04 = TransacaoBuilder.umaTransacao().comValor(300.0).build();

        TransacaoService.transacoes.addAll(Arrays.asList(transacaoComMaisDe60Segundos01, transacao03, transacao04, transacaoComMaisDe60Segundos02));

        Estatistica estatistica = service.get("120");

        Assertions.assertEquals(4, estatistica.getCount());
        Assertions.assertEquals(126.75, estatistica.getAvg());
        Assertions.assertEquals(10.0, estatistica.getMin());
        Assertions.assertEquals(300.0, estatistica.getMax());
    }

    @Test
    @DisplayName("Deve retornar estatística com valores zerados")
    public void deveRetornarEstatisticaComValoresZerados() {
        TransacaoService.transacoes.clear();
        var transacaoComMaisDe60Segundos01 = TransacaoBuilder.umaTransacao().comValor(99.7).comDataHora(OffsetDateTime.now().minusSeconds(120)).build();
        var transacaoComMaisDe60Segundos02 = TransacaoBuilder.umaTransacao().comDataHora(OffsetDateTime.now().minusSeconds(120)).build();

        TransacaoService.transacoes.addAll(Arrays.asList(transacaoComMaisDe60Segundos01, transacaoComMaisDe60Segundos02));

        Estatistica estatistica = service.get("60");

        Assertions.assertEquals(0, estatistica.getCount());
        Assertions.assertEquals(0.0, estatistica.getAvg());
        Assertions.assertEquals(0.0, estatistica.getMin());
        Assertions.assertEquals(0.0, estatistica.getMax());
    }
}
