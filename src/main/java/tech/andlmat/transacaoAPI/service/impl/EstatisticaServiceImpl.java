package tech.andlmat.transacaoAPI.service.impl;

import org.springframework.stereotype.Service;
import tech.andlmat.transacaoAPI.domain.Estatistica;
import tech.andlmat.transacaoAPI.service.EstatisticaService;
import tech.andlmat.transacaoAPI.service.TransacaoService;

import java.time.OffsetDateTime;

@Service
public class EstatisticaServiceImpl implements EstatisticaService {

    @Override
    public Estatistica get(String intervaloDeTempoEmSegundos) {
        var dataAgora = OffsetDateTime.now();
        var ultimos60Segundos = dataAgora.minusSeconds(Long.parseLong(intervaloDeTempoEmSegundos));
        var listaTransacoesIntervaloDeTempo = TransacaoService.transacoes.stream().filter(x ->
                x.getDataHora().isAfter(ultimos60Segundos)).toList();

        if (listaTransacoesIntervaloDeTempo.isEmpty())
            return new Estatistica(0, 0.0, 0.0, 0.0, 0.0);
        return new Estatistica(listaTransacoesIntervaloDeTempo);
    }
}
