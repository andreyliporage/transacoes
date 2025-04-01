package tech.andlmat.transacaoAPI.domain.exceptions;

public class ValorTransacaoException extends RuntimeException {

    public ValorTransacaoException() {
      super("O valor deve ser maior que zero");
    }
}
