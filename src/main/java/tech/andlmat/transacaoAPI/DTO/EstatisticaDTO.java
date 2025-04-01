package tech.andlmat.transacaoAPI.DTO;

import tech.andlmat.transacaoAPI.domain.Estatistica;

public record EstatisticaDTO(String count, String sum, String avg, String min, String max) {

    public static EstatisticaDTO fromEstatistica(Estatistica estatistica) {
        return new EstatisticaDTO(estatistica.getCount().toString(),
                estatistica.getSum().toString(),
                estatistica.getAvg().toString(),
                estatistica.getMin().toString(),
                estatistica.getMax().toString());
    }

    public Estatistica toEstatistica() {
        return new Estatistica(Integer.parseInt(count), Double.parseDouble(sum), Double.parseDouble(avg), Double.parseDouble(min), Double.parseDouble(max));
    }
}
