package tech.andlmat.transacaoAPI.service.impl;

import org.springframework.stereotype.Service;
import tech.andlmat.transacaoAPI.domain.Transacao;
import tech.andlmat.transacaoAPI.infra.TransacaoRepository;
import tech.andlmat.transacaoAPI.service.TransacaoService;

@Service
public class TransacaoServiceImpl implements TransacaoService {


    @Override
    public Transacao post(Transacao transacao) {
        TransacaoService.transacoes.add(transacao);

        return transacao;
    }

    @Override
    public void delete() {
        TransacaoService.transacoes.clear();
    }
}
