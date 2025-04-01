package tech.andlmat.transacaoAPI.service;

import tech.andlmat.transacaoAPI.domain.Transacao;

public interface TransacaoService {

    Transacao post(Transacao transacao);
    void delete();

}
