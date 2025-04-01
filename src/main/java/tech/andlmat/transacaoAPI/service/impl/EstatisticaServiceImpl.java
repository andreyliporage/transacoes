package tech.andlmat.transacaoAPI.service.impl;

import tech.andlmat.transacaoAPI.domain.Estatistica;
import tech.andlmat.transacaoAPI.infra.TransacaoRepository;
import tech.andlmat.transacaoAPI.service.EstatisticaService;

import java.time.OffsetDateTime;

public class EstatisticaServiceImpl implements EstatisticaService {

    private TransacaoRepository repository;

    public EstatisticaServiceImpl(TransacaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Estatistica get(String intervaloDeTempoEmSegundos) {
        repository.get();
        var dataAgora = OffsetDateTime.now();
        var ultimos60Segundos = dataAgora.minusSeconds(Long.parseLong(intervaloDeTempoEmSegundos));
        var listaTransacoesIntervaloDeTempo = TransacaoRepository.transacoes.stream().filter(x ->
                x.getDataHora().isAfter(ultimos60Segundos)).toList();

        return new Estatistica(listaTransacoesIntervaloDeTempo);
    }
}
