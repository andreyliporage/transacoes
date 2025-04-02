package tech.andlmat.transacaoAPI.builders;

import tech.andlmat.transacaoAPI.domain.Transacao;

import java.time.OffsetDateTime;

public class TransacaoBuilder {

    private Double valor;
    private OffsetDateTime dataHora;

    private TransacaoBuilder() {}

    public static TransacaoBuilder umaTransacao() {
        TransacaoBuilder builder = new TransacaoBuilder();
        inicializacaoPadrao(builder);
        return builder;
    }

    private static void inicializacaoPadrao(TransacaoBuilder transacaoBuilder) {
        transacaoBuilder.valor = 100.0;
        transacaoBuilder.dataHora = OffsetDateTime.now();
    }

    public TransacaoBuilder comValor(Double valor) {
        this.valor = valor;
        return this;
    }

    public TransacaoBuilder comDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
        return this;
    }

    public Transacao build() {
        return new Transacao(valor, dataHora);
    }
}
