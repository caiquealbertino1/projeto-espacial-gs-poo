import java.util.Random;

public class SensorPressao extends ComponenteEspacial implements Sensor {

    private double limiteAlerta;
    private double valorAtual;
    private static final Random random = new Random();

    public SensorPressao(String id, String nome, double limiteAlerta) {
        super(id, nome);
        this.limiteAlerta = limiteAlerta;
        this.valorAtual = 101.3;
    }

    @Override
    public double lerValor() {
        valorAtual = 80.0 + random.nextDouble() * 60.0;
        return valorAtual;
    }

    @Override
    public boolean verificarFuncionamento() {
        return isAtivo() && valorAtual >= 0;
    }

    @Override
    public String retornarTipo() {
        return "Sensor de Pressao";
    }

    @Override
    public double getLimiteAlerta() {
        return limiteAlerta;
    }

    @Override
    public boolean valorAcimaDoLimite() {
        return valorAtual > limiteAlerta;
    }

    @Override
    public void exibirStatus() {
        System.out.println("--- " + retornarTipo() + " [" + getId() + "] ---");
        System.out.println("Nome   : " + getNome());
        System.out.println("Ativo  : " + (isAtivo() ? "SIM" : "NAO"));
        System.out.printf("Valor  : %.2f kPa%n", valorAtual);
        System.out.printf("Limite : %.2f kPa%n", limiteAlerta);
    }
}