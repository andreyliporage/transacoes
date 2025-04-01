package tech.andlmat.transacaoAPI.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tech.andlmat.transacaoAPI.builders.TransacaoBuilder;

import java.util.ArrayList;
import java.util.Collections;

@DisplayName("Domínio: Estatistica")
public class EstatisticaTest {

    @Test
    @DisplayName("Deve calcular a estatistica das transacoes corretamente")
    public void deveCalcularEstatisticasTransacoesCorretamente() {
        var transacoes = Collections.singletonList(TransacaoBuilder.umaTransacao().build());
        var estatistica = new Estatistica(transacoes);

        Assertions.assertAll(
                "Estatistica",
                () -> Assertions.assertEquals(transacoes.size(), estatistica.getCount()),
                () -> Assertions.assertEquals(transacoes.getFirst().getValor(), estatistica.getSum()),
                () -> Assertions.assertEquals(transacoes.getFirst().getValor(), estatistica.getAvg()),
                () -> Assertions.assertEquals(transacoes.getFirst().getValor(), estatistica.getMin()),
                () -> Assertions.assertEquals(transacoes.getFirst().getValor(), estatistica.getMax())
        );
    }

    @Test
    @DisplayName("Deve calcular a estatistica das transacoes corretamente com 3 itens na lista")
    public void deveCalcularEstatisticasTransacoesCorretamenteCom3ItensNaLista() {

        var transacoes = new ArrayList<Transacao>();
        transacoes.add(TransacaoBuilder.umaTransacao().build());
        transacoes.add(TransacaoBuilder.umaTransacao().comValor(233.50).build());
        transacoes.add(TransacaoBuilder.umaTransacao().comValor(27.2).build());
        var estatistica = new Estatistica(transacoes);

        Assertions.assertAll(
                "Estatistica",
                () -> Assertions.assertEquals(3, estatistica.getCount()),
                () -> Assertions.assertEquals(360.7, estatistica.getSum()),
                () -> Assertions.assertEquals(120.23, estatistica.getAvg(), 1),
                () -> Assertions.assertEquals(27.2, estatistica.getMin()),
                () -> Assertions.assertEquals(233.50, estatistica.getMax())
        );
    }
}
