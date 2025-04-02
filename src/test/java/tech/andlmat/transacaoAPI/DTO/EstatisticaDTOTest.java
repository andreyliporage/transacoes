package tech.andlmat.transacaoAPI.DTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tech.andlmat.transacaoAPI.builders.TransacaoBuilder;
import tech.andlmat.transacaoAPI.domain.Estatistica;

import java.util.Collections;

@DisplayName("DTO: EstatísticaDTO")
public class EstatisticaDTOTest {

    @Test
    @DisplayName("Deve retornar EstatisticaDTO from estatistica")
    public void deveRetornarEstatisticaDTOFromEstatistica() {
        Estatistica estatistica = new Estatistica(Collections.singletonList(TransacaoBuilder.umaTransacao().build()));

        EstatisticaDTO estatisticaDTO = EstatisticaDTO.fromEstatistica(estatistica);

        Assertions.assertEquals(estatistica.getCount().toString(), estatisticaDTO.count());
        Assertions.assertEquals(estatistica.getSum().toString(), estatisticaDTO.sum());
        Assertions.assertEquals(estatistica.getAvg().toString(), estatisticaDTO.avg());
        Assertions.assertEquals(estatistica.getMin().toString(), estatisticaDTO.min());
        Assertions.assertEquals(estatistica.getMax().toString(), estatisticaDTO.max());
    }

    @Test
    @DisplayName("Deve retornar Estatistica from EstatisticaDTO")
    public void deveRetornarEstatisticaDTOtoEstatistica() {
        EstatisticaDTO estatisticaDTO = new EstatisticaDTO("10", "20.0", "30.0", "40.0", "50.0");

        Estatistica estatistica = estatisticaDTO.toEstatistica();

        Assertions.assertEquals(estatisticaDTO.count(), estatistica.getCount().toString());
        Assertions.assertEquals(estatisticaDTO.sum(), estatistica.getSum().toString());
        Assertions.assertEquals(estatisticaDTO.avg(), estatistica.getAvg().toString());
        Assertions.assertEquals(estatisticaDTO.min(), estatistica.getMin().toString());
        Assertions.assertEquals(estatisticaDTO.max(), estatistica.getMax().toString());
    }
}
