package tech.andlmat.transacaoAPI.infra;

import tech.andlmat.transacaoAPI.domain.Transacao;

import java.util.List;

public interface TransacaoRepository {

    Transacao post(Transacao transacao);
    void delete();
}
