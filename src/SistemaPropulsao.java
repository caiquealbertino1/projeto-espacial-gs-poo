public abstract class SistemaPropulsao extends ComponenteEspacial {

    protected double potencia;
    protected double empuxo;
    protected double combustivel;

    public SistemaPropulsao(String id, String nome, double combustivel) {
        super(id, nome);
        this.potencia = 0;
        this.empuxo = 0;
        this.combustivel = combustivel;
    }

    public void acelerar(double porcentagem) {
        if (porcentagem < 0 || porcentagem > 100) {
            System.out.println("[ERRO] Potencia invalida. Use valores entre 0 e 100.");
            return;
        }
        if (!isAtivo()) {
            System.out.println("[ERRO] Sistema de propulsao desligado. Ligue primeiro.");
            return;
        }
        this.potencia = porcentagem;
        System.out.printf("[%s] Acelerando para %.1f%% de potencia.%n", getNome(), porcentagem);
    }

    public abstract double calcularEmpuxo();

    public double getPotencia() { return potencia; }
    public double getEmpuxo() { return empuxo; }
    public double getCombustivel() { return combustivel; }

    public void setCombustivel(double combustivel) {
        if (combustivel < 0) {
            System.out.println("[ERRO] Combustivel nao pode ser negativo.");
            return;
        }
        this.combustivel = combustivel;
    }
}