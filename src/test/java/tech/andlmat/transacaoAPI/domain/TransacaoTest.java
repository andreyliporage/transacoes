package tech.andlmat.transacaoAPI.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tech.andlmat.transacaoAPI.builders.TransacaoBuilder;
import tech.andlmat.transacaoAPI.domain.exceptions.DataHoraTransacaoException;
import tech.andlmat.transacaoAPI.domain.exceptions.ValorTransacaoException;

import java.time.OffsetDateTime;

@DisplayName("Domínio: Transação")
public class TransacaoTest {

    @Test
    @DisplayName("Deve criar uma transação válida")
    public void deveCriarUmaTransacaoValida() {
        Transacao transacao = TransacaoBuilder.umaTransacao().build();
        OffsetDateTime dataAgora = OffsetDateTime.now();

        Assertions.assertAll(
                "Transação",
                () -> Assertions.assertEquals(100.0, transacao.getValor()),
                () -> Assertions.assertTrue(dataAgora.isAfter(transacao.getDataHora()))
        );
    }

    @Test
    @DisplayName("Deve lançar ValorTransacaoException quando valor for null")
    public void deveLancarValorTransacaoExceptionQuandoValorNull() {
        var exception = Assertions.assertThrows(ValorTransacaoException.class, () ->
                TransacaoBuilder.umaTransacao().comValor(null).build());

        Assertions.assertEquals("O valor deve ser maior que zero", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar ValorTransacaoException quando valor for menor que zero")
    public void deveLancarValorTransacaoExceptionQuandoValorMenorZero() {
        var exception = Assertions.assertThrows(ValorTransacaoException.class, () ->
                TransacaoBuilder.umaTransacao().comValor(-10.0).build());

        Assertions.assertEquals("O valor deve ser maior que zero", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar DataHoraTransacaoException quando dataHora for null")
    public void deveLancarDataHoraTransacaoExceptionQuandoDataHoraNull() {
        OffsetDateTime dataAgora = OffsetDateTime.now();
        var exception = Assertions.assertThrows(DataHoraTransacaoException.class, () ->
                TransacaoBuilder.umaTransacao().comDataHora(null).build());

        Assertions.assertEquals("Campo dataHora nulo ou inválido", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar DataHoraTransacaoException quando dataHora for no futuro")
    public void deveLancarDataHoraTransacaoExceptionQuandoDataHoraForNoFuturo() {
        OffsetDateTime dataAgora = OffsetDateTime.now();
        var exception = Assertions.assertThrows(DataHoraTransacaoException.class, () ->
                TransacaoBuilder.umaTransacao().comDataHora(dataAgora.plusHours(3)).build());

        Assertions.assertEquals("Campo dataHora nulo ou inválido", exception.getMessage());
    }
}
