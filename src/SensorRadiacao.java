import java.util.Random;

public class SensorRadiacao extends ComponenteEspacial implements Sensor {

    private double limiteAlerta;
    private double valorAtual;
    private static final Random random = new Random();

    public SensorRadiacao(String id, String nome, double limiteAlerta) {
        super(id, nome);
        this.limiteAlerta = limiteAlerta;
        this.valorAtual = 0.1;
    }

    @Override
    public double lerValor() {
        valorAtual = 0.05 + random.nextDouble() * 10.0;
        return valorAtual;
    }

    @Override
    public boolean verificarFuncionamento() {
        return isAtivo() && valorAtual >= 0;
    }

    @Override
    public String retornarTipo() {
        return "Sensor de Radiacao";
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
        System.out.printf("Valor  : %.4f mSv/h%n", valorAtual);
        System.out.printf("Limite : %.4f mSv/h%n", limiteAlerta);
    }
}