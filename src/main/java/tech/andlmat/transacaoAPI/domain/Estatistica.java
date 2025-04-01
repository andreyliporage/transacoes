package tech.andlmat.transacaoAPI.domain;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class Estatistica {

    private Integer count;
    private Double sum;
    private Double avg;
    private Double min;
    private Double max;

    public Estatistica(Integer count, Double sum, Double avg, Double min, Double max) {
        this.count = count;
        this.sum = sum;
        this.avg = avg;
        this.min = min;
        this.max = max;
    }

    public Estatistica(List<Transacao> transacoes) {
        this.count = transacoes.size();
        this.sum = somaValoresTransacoes(transacoes);
        this.avg = calculaAvgValoresTransacoes(transacoes);
        this.min = retornaMenorValorDasTransacoes(transacoes);
        this.max = retornaMaiorValorDasTransacoes(transacoes);
    }

    private Double somaValoresTransacoes(List<Transacao> transacoes) {
        return transacoes.stream()
                .map(Transacao::getValor)
                .reduce(0.0, Double::sum);
    }

    private Double calculaAvgValoresTransacoes(List<Transacao> transacoes) {
        var tamanhoLista = transacoes.size();

        var valorTotal = transacoes.stream()
                .map(Transacao::getValor)
                .reduce(0.0, Double::sum);

        return valorTotal / tamanhoLista;
    }

    private Double retornaMenorValorDasTransacoes(List<Transacao> transacoes) {
        return transacoes.stream().min(Comparator.comparing(Transacao::getValor)).orElseThrow(NoSuchElementException::new).getValor();
    }

    private Double retornaMaiorValorDasTransacoes(List<Transacao> transacoes) {
        return transacoes.stream().max(Comparator.comparing(Transacao::getValor)).orElseThrow(NoSuchElementException::new).getValor();
    }

    public Integer getCount() {
        return count;
    }

    public Double getSum() {
        return sum;
    }

    public Double getAvg() {
        return avg;
    }

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }
}
