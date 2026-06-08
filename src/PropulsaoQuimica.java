public class PropulsaoQuimica extends SistemaPropulsao {

    private String tipoCombustivel;
    private double pressaoCamara;

    public PropulsaoQuimica(String id, String nome, double combustivel, String tipoCombustivel) {
        super(id, nome, combustivel);
        this.tipoCombustivel = tipoCombustivel;
        this.pressaoCamara = 0.0;
    }

    @Override
    public void acelerar(double porcentagem) {
        super.acelerar(porcentagem);
        if (porcentagem >= 0 && porcentagem <= 100 && isAtivo()) {
            pressaoCamara = porcentagem * 2.5;
            empuxo = calcularEmpuxo();
            System.out.printf("[Quimica] Pressao na camara: %.1f bar | Empuxo: %.2f kN%n", pressaoCamara, empuxo);
        }
    }

    @Override
    public double calcularEmpuxo() {
        return potencia * 4.5 * (combustivel / 100.0);
    }

    @Override
    public void exibirStatus() {
        System.out.println("--- Propulsao Quimica [" + getId() + "] ---");
        System.out.println("Nome           : " + getNome());
        System.out.println("Ativo          : " + (isAtivo() ? "SIM" : "NAO"));
        System.out.println("Combustivel    : " + tipoCombustivel);
        System.out.printf("Nivel comb.    : %.1f%%%n", combustivel);
        System.out.printf("Potencia       : %.1f%%%n", potencia);
        System.out.printf("Pressao camara : %.1f bar%n", pressaoCamara);
        System.out.printf("Empuxo         : %.2f kN%n", empuxo);
    }

    public String getTipoCombustivel() { return tipoCombustivel; }
    public double getPressaoCamara() { return pressaoCamara; }
}