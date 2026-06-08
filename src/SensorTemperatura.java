import java.util.Random;

public class SensorTemperatura extends ComponenteEspacial implements Sensor {

    private double limiteAlerta;
    private double valorAtual;
    private static final Random random = new Random();

    public SensorTemperatura(String id, String nome, double limiteAlerta) {
        super(id, nome);
        this.limiteAlerta = limiteAlerta;
        this.valorAtual = 20.0;
    }

    @Override
    public double lerValor() {
        valorAtual = 15.0 + random.nextDouble() * 80.0;
        return valorAtual;
    }

    @Override
    public boolean verificarFuncionamento() {
        return isAtivo() && valorAtual >= -273.15;
    }

    @Override
    public String retornarTipo() {
        return "Sensor de Temperatura";
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
        System.out.printf("Valor  : %.2f C%n", valorAtual);
        System.out.printf("Limite : %.2f C%n", limiteAlerta);
    }
}