// Enum: Procedimento
// O que faz:
// Enum com tipos de procedimentos e seus preços.
// getPreco retorna o valor para cálculos de faturamento.


public enum Procedimento {
    BANHO(50.0),
    TOSA(80.0),
    VACINACAO(120.0),
    CORTE_UNHA(30.0),
    HIGIENIZACAO_ORELHA(25.0);

    private final double preco;

    Procedimento(double preco) {
        this.preco = preco;
    }
    
/**
 * Método: getPreco
 * O que faz: Retorna o valor do atributo 'preco'
 */
    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return name() + " (R$ " + preco + ")";
    }
}
