package tech.andlmat.transacaoAPI.infra;

import tech.andlmat.transacaoAPI.domain.Transacao;

import java.util.ArrayList;
import java.util.List;

public interface TransacaoRepository {

    Transacao post(Transacao transacao);
    void delete();
    List<Transacao> get();
    List<Transacao> transacoes = new ArrayList<>();

}
