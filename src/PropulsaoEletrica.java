public class PropulsaoEletrica extends SistemaPropulsao {

    private double potenciaEletrica;
    private double velocidadeExaustao;

    public PropulsaoEletrica(String id, String nome, double combustivel, double potenciaEletrica) {
        super(id, nome, combustivel);
        this.potenciaEletrica = potenciaEletrica;
        this.velocidadeExaustao = 0.0;
    }

    @Override
    public void acelerar(double porcentagem) {
        super.acelerar(porcentagem);
        if (porcentagem >= 0 && porcentagem <= 100 && isAtivo()) {
            velocidadeExaustao = 30000 + porcentagem * 200;
            empuxo = calcularEmpuxo();
            System.out.printf("[Eletrica] Vel. exaustao: %.0f m/s | Empuxo: %.4f kN%n", velocidadeExaustao, empuxo);
        }
    }

    @Override
    public double calcularEmpuxo() {
        return (potenciaEletrica * (potencia / 100.0)) / velocidadeExaustao;
    }

    @Override
    public void exibirStatus() {
        System.out.println("--- Propulsao Eletrica [" + getId() + "] ---");
        System.out.println("Nome             : " + getNome());
        System.out.println("Ativo            : " + (isAtivo() ? "SIM" : "NAO"));
        System.out.printf("Pot. eletrica    : %.1f kW%n", potenciaEletrica);
        System.out.printf("Nivel comb.      : %.1f%%%n", combustivel);
        System.out.printf("Potencia         : %.1f%%%n", potencia);
        System.out.printf("Vel. exaustao    : %.0f m/s%n", velocidadeExaustao);
        System.out.printf("Empuxo           : %.4f kN%n", empuxo);
    }

    public double getPotenciaEletrica() { return potenciaEletrica; }
    public double getVelocidadeExaustao() { return velocidadeExaustao; }
}