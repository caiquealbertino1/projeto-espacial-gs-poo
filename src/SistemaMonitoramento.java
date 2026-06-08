import java.util.Scanner;

public class SistemaMonitoramento {

    private static SensorTemperatura sensorTemp = new SensorTemperatura("S01", "Sensor Temp Casco", 80.0);
    private static SensorPressao sensorPressao = new SensorPressao("S02", "Sensor Pressao Cabine", 130.0);
    private static SensorRadiacao sensorRadiacao = new SensorRadiacao("S03", "Sensor Radiacao Solar", 5.0);
    private static PropulsaoQuimica propQuimica = new PropulsaoQuimica("P01", "Motor Principal", 100.0, "LOX/LH2");
    private static PropulsaoEletrica propEletrica = new PropulsaoEletrica("P02", "Thruster Eletrico", 80.0, 50.0);
    private static DadosMissao missao = new DadosMissao("NEXUS-DEEP", "SPACE2026", 3);
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarSistemas();
        int opcao = -1;
        while (opcao != 0) {
            exibirMenu();
            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                opcao = -1;
            }
            processarOpcao(opcao);
        }
        System.out.println("Encerrando sistema de monitoramento. Boa viagem!");
        scanner.close();
    }

    private static void inicializarSistemas() {
        sensorTemp.ligar();
        sensorPressao.ligar();
        sensorRadiacao.ligar();
        propQuimica.ligar();
        propEletrica.ligar();
        System.out.println("Sistema de monitoramento inicializado.\n");
    }

    private static void exibirMenu() {
        System.out.println("\n========================================");
        System.out.println("   PLATAFORMA DE MONITORAMENTO ESPACIAL");
        System.out.println("========================================");
        System.out.println(" 1. Verificar sensores");
        System.out.println(" 2. Controlar propulsao");
        System.out.println(" 3. Gerenciar dados da missao");
        System.out.println(" 4. Simular alertas");
        System.out.println(" 5. Status completo");
        System.out.println(" 0. Sair");
        System.out.println("========================================");
        System.out.print("Opcao: ");
    }

    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1 -> menuSensores();
            case 2 -> menuPropulsao();
            case 3 -> menuMissao();
            case 4 -> simularAlertas();
            case 5 -> exibirStatusCompleto();
            case 0 -> {}
            default -> System.out.println("[ERRO] Opcao invalida.");
        }
    }

    // ── MENU SENSORES ──────────────────────────────────────────────────────────

    private static void menuSensores() {
        System.out.println("\n--- SENSORES ---");
        System.out.println("1. Ler todos os sensores");
        System.out.println("2. Verificar funcionamento");
        System.out.println("3. Status detalhado");
        System.out.print("Opcao: ");
        int op = lerInt();
        switch (op) {
            case 1 -> lerTodosSensores();
            case 2 -> verificarFuncionamentoSensores();
            case 3 -> {
                sensorTemp.exibirStatus();
                sensorPressao.exibirStatus();
                sensorRadiacao.exibirStatus();
            }
            default -> System.out.println("[ERRO] Opcao invalida.");
        }
    }

    private static void lerTodosSensores() {
        System.out.printf("Temperatura : %.2f C%n", sensorTemp.lerValor());
        System.out.printf("Pressao     : %.2f kPa%n", sensorPressao.lerValor());
        System.out.printf("Radiacao    : %.4f mSv/h%n", sensorRadiacao.lerValor());
        verificarLimitesSensores();
    }

    private static void verificarFuncionamentoSensores() {
        System.out.println("Sensor Temperatura : " + (sensorTemp.verificarFuncionamento() ? "OK" : "FALHA"));
        System.out.println("Sensor Pressao     : " + (sensorPressao.verificarFuncionamento() ? "OK" : "FALHA"));
        System.out.println("Sensor Radiacao    : " + (sensorRadiacao.verificarFuncionamento() ? "OK" : "FALHA"));
    }

    private static void verificarLimitesSensores() {
        emitirAlertaSensor(sensorTemp, "C");
        emitirAlertaSensor(sensorPressao, "kPa");
        emitirAlertaSensor(sensorRadiacao, "mSv/h");
    }

    private static void emitirAlertaSensor(Sensor sensor, String unidade) {
        if (!sensor.valorAcimaDoLimite()) return;
        double valor = sensor.lerValor();
        double limite = sensor.getLimiteAlerta();
        double excesso = ((valor - limite) / limite) * 100;
        String nivel = excesso < 10 ? "ATENCAO" : excesso < 30 ? "ALERTA" : "CRITICO";
        System.out.printf("[%s] %s: %.2f %s (limite: %.2f)%n", nivel, sensor.retornarTipo(), valor, unidade, limite);
    }

    // ── MENU PROPULSAO ─────────────────────────────────────────────────────────

    private static void menuPropulsao() {
        System.out.println("\n--- PROPULSAO ---");
        System.out.println("1. Ligar/desligar motores");
        System.out.println("2. Acelerar propulsao quimica");
        System.out.println("3. Acelerar propulsao eletrica");
        System.out.println("4. Status dos motores");
        System.out.print("Opcao: ");
        int op = lerInt();
        switch (op) {
            case 1 -> toggleMotores();
            case 2 -> {
                System.out.print("Potencia (0-100): ");
                propQuimica.acelerar(lerDouble());
            }
            case 3 -> {
                System.out.print("Potencia (0-100): ");
                propEletrica.acelerar(lerDouble());
            }
            case 4 -> {
                propQuimica.exibirStatus();
                propEletrica.exibirStatus();
            }
            default -> System.out.println("[ERRO] Opcao invalida.");
        }
    }

    private static void toggleMotores() {
        System.out.println("1. Ligar tudo  2. Desligar tudo");
        System.out.print("Opcao: ");
        int op = lerInt();
        if (op == 1) { propQuimica.ligar(); propEletrica.ligar(); }
        else if (op == 2) { propQuimica.desligar(); propEletrica.desligar(); }
    }

    // ── MENU MISSAO ────────────────────────────────────────────────────────────

    private static void menuMissao() {
        System.out.println("\n--- DADOS DA MISSAO ---");
        System.out.println("1. Ver resumo");
        System.out.println("2. Atualizar combustivel");
        System.out.println("3. Atualizar trajetoria");
        System.out.println("4. Ver coordenadas (requer senha)");
        System.out.println("5. Atualizar coordenadas (requer senha)");
        System.out.print("Opcao: ");
        int op = lerInt();
        switch (op) {
            case 1 -> missao.exibirResumo();
            case 2 -> {
                System.out.print("Novo nivel de combustivel (0-100): ");
                missao.setNivelCombustivel(lerDouble());
            }
            case 3 -> {
                System.out.print("Nova trajetoria: ");
                missao.setTrajetoria(scanner.nextLine().trim());
            }
            case 4 -> {
                System.out.print("Senha: ");
                System.out.println(missao.getCoordenadas(scanner.nextLine().trim()));
            }
            case 5 -> {
                System.out.print("Senha: ");
                String senha = scanner.nextLine().trim();
                System.out.print("X: "); double x = lerDouble();
                System.out.print("Y: "); double y = lerDouble();
                System.out.print("Z: "); double z = lerDouble();
                missao.setCoordenadas(senha, x, y, z);
            }
            default -> System.out.println("[ERRO] Opcao invalida.");
        }
    }

    // ── ALERTAS ────────────────────────────────────────────────────────────────

    private static void simularAlertas() {
        System.out.println("\n=== SIMULACAO DE ALERTAS ===");
        lerTodosSensores();
        if (missao.getNivelCombustivel() < 20) {
            System.out.printf("[ALERTA] Combustivel critico: %.1f%%%n", missao.getNivelCombustivel());
        }
        System.out.println("Simulacao concluida.");
    }

    // ── STATUS COMPLETO ────────────────────────────────────────────────────────

    private static void exibirStatusCompleto() {
        System.out.println("\n========== STATUS COMPLETO ==========");
        missao.exibirResumo();
        System.out.println();
        sensorTemp.exibirStatus();
        sensorPressao.exibirStatus();
        sensorRadiacao.exibirStatus();
        System.out.println();
        propQuimica.exibirStatus();
        propEletrica.exibirStatus();
        System.out.println("=====================================");
    }

    // ── HELPERS ───────────────────────────────────────────────────────────────

    private static int lerInt() {
        try { return Integer.parseInt(scanner.nextLine().trim()); }
        catch (NumberFormatException e) { return -1; }
    }

    private static double lerDouble() {
        try { return Double.parseDouble(scanner.nextLine().trim()); }
        catch (NumberFormatException e) { return -1; }
    }
}