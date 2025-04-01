package tech.andlmat.transacaoAPI.service;

import tech.andlmat.transacaoAPI.domain.Transacao;

import java.util.ArrayList;
import java.util.List;

public interface TransacaoService {

    List<Transacao> transacoes = new ArrayList<>();

    Transacao post(Transacao transacao);
    void delete();

}
