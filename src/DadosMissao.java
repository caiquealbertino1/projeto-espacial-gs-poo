public class DadosMissao {

    private String nomeMissao;
    private double coordenadaX;
    private double coordenadaY;
    private double coordenadaZ;
    private double nivelCombustivel;
    private String trajetoria;
    private int numeroDeTripulantes;
    private String codigoAcesso;

    private static final String SENHA_COORDENADAS = "SPACE2026";
    private static final double LIMITE_ALERTA_COMBUSTIVEL = 20.0;

    public DadosMissao(String nomeMissao, String codigoAcesso, int numeroDeTripulantes) {
        this.nomeMissao = nomeMissao;
        this.codigoAcesso = codigoAcesso;
        this.numeroDeTripulantes = numeroDeTripulantes;
        this.nivelCombustivel = 100.0;
        this.trajetoria = "Indefinida";
        this.coordenadaX = 0;
        this.coordenadaY = 0;
        this.coordenadaZ = 0;
    }

    public String getNomeMissao() { return nomeMissao; }

    public double getNivelCombustivel() { return nivelCombustivel; }

    public void setNivelCombustivel(double nivel) {
        if (nivel < 0 || nivel > 100) {
            System.out.println("[ERRO] Nivel de combustivel invalido. Use valores entre 0 e 100.");
            return;
        }
        this.nivelCombustivel = nivel;
        if (nivel < LIMITE_ALERTA_COMBUSTIVEL) {
            System.out.println("[ALERTA] Combustivel abaixo de 20%! Nivel atual: " + nivel + "%");
        }
    }

    public String getTrajetoria() { return trajetoria; }

    public void setTrajetoria(String trajetoria) {
        if (trajetoria == null || trajetoria.isBlank()) {
            System.out.println("[ERRO] Trajetoria invalida.");
            return;
        }
        this.trajetoria = trajetoria;
    }

    public int getNumeroDeTripulantes() { return numeroDeTripulantes; }

    public void setNumeroDeTripulantes(int numero) {
        if (numero < 0) {
            System.out.println("[ERRO] Numero de tripulantes nao pode ser negativo.");
            return;
        }
        this.numeroDeTripulantes = numero;
    }

    public String getCoordenadas(String senha) {
        if (!senha.equals(SENHA_COORDENADAS)) {
            return "[ACESSO NEGADO] Senha incorreta.";
        }
        return String.format("X=%.2f | Y=%.2f | Z=%.2f", coordenadaX, coordenadaY, coordenadaZ);
    }

    public boolean setCoordenadas(String senha, double x, double y, double z) {
        if (!senha.equals(SENHA_COORDENADAS)) {
            System.out.println("[ACESSO NEGADO] Senha incorreta. Coordenadas nao alteradas.");
            return false;
        }
        this.coordenadaX = x;
        this.coordenadaY = y;
        this.coordenadaZ = z;
        System.out.println("[OK] Coordenadas atualizadas.");
        return true;
    }

    public boolean verificarCodigoAcesso(String codigo) {
        return this.codigoAcesso.equals(codigo);
    }

    public void exibirResumo() {
        System.out.println("=== DADOS DA MISSAO ===");
        System.out.println("Missao       : " + nomeMissao);
        System.out.println("Tripulantes  : " + numeroDeTripulantes);
        System.out.println("Trajetoria   : " + trajetoria);
        System.out.printf("Combustivel  : %.1f%%%n", nivelCombustivel);
        System.out.println("Coordenadas  : [PROTEGIDAS - requer senha]");
    }
}