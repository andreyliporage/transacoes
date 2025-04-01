package tech.andlmat.transacaoAPI.service;

import tech.andlmat.transacaoAPI.domain.Estatistica;

public interface EstatisticaService {

    Estatistica get(String intervaloDeTempoEmSegundos);
}
