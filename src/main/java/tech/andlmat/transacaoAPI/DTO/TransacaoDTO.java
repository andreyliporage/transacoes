package tech.andlmat.transacaoAPI.DTO;

import tech.andlmat.transacaoAPI.domain.Transacao;

import java.time.OffsetDateTime;

public record TransacaoDTO(Double valor, OffsetDateTime dataHora) {

    public static TransacaoDTO fromTransacao(Transacao transacao) {
        return new TransacaoDTO(transacao.getValor(), transacao.getDataHora());
    }

    public Transacao toTransacao() {
        return new Transacao(valor, dataHora);
    }
}
