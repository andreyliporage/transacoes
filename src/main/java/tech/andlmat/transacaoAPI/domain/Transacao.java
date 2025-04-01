package tech.andlmat.transacaoAPI.domain;

import tech.andlmat.transacaoAPI.domain.exceptions.DataHoraTransacaoException;
import tech.andlmat.transacaoAPI.domain.exceptions.ValorTransacaoException;

import java.time.OffsetDateTime;

public class Transacao {

    private Double valor;
    private OffsetDateTime dataHora;

    public Transacao(Double valor, OffsetDateTime dataHora) {
        valorTransacaoValido(valor);
        dataHoraTransacaoValida(dataHora);

        this.valor = valor;
        this.dataHora = dataHora;
    }

    public Double getValor() {
        return valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    private void valorTransacaoValido(Double valor) {
        if (valor == null || valor < 0)
            throw new ValorTransacaoException();
    }

    private void dataHoraTransacaoValida(OffsetDateTime dataHora) {
        OffsetDateTime dataAgora = OffsetDateTime.now();
        if (dataHora == null || dataHora.isAfter(dataAgora))
            throw new DataHoraTransacaoException();
    }
}
