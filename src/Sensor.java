public interface Sensor {

    double lerValor();

    boolean verificarFuncionamento();

    String retornarTipo();

    double getLimiteAlerta();

    boolean valorAcimaDoLimite();
}