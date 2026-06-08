public abstract class ComponenteEspacial {

    protected String id;
    protected String nome;
    protected boolean status;
    protected double temperatura;

    public ComponenteEspacial(String id, String nome) {
        this.id = id;
        this.nome = nome;
        this.status = false;
        this.temperatura = 20.0;
    }

    public void ligar() {
        this.status = true;
        System.out.println("[" + nome + "] Componente ligado.");
    }

    public void desligar() {
        this.status = false;
        System.out.println("[" + nome + "] Componente desligado.");
    }

    public abstract void exibirStatus();

    public String getId() { return id; }
    public String getNome() { return nome; }
    public boolean isAtivo() { return status; }
    public double getTemperatura() { return temperatura; }
    public void setTemperatura(double temperatura) { this.temperatura = temperatura; }
}