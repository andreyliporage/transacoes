package tech.andlmat.transacaoAPI.domain.exceptions;

public class DataHoraTransacaoException extends RuntimeException {
    public DataHoraTransacaoException() {
        super("Campo dataHora nulo ou inválido");
    }
}
