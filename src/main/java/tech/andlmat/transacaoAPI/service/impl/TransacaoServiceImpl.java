package tech.andlmat.transacaoAPI.service.impl;

import tech.andlmat.transacaoAPI.domain.Transacao;
import tech.andlmat.transacaoAPI.infra.TransacaoRepository;
import tech.andlmat.transacaoAPI.service.TransacaoService;

public class TransacaoServiceImpl implements TransacaoService {

    private TransacaoRepository repository;

    public TransacaoServiceImpl(TransacaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Transacao post(Transacao transacao) {
        transacoes.add(transacao);
        repository.post(transacao);

        return transacao;
    }

    @Override
    public void delete() {
        transacoes.clear();
    }
}
