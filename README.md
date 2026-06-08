# Plataforma de Monitoramento Espacial

Projeto desenvolvido para a Global Solution 2026 da FIAP — disciplina de Programação Orientada a Objetos.

Simula uma estação espacial com monitoramento de sensores, controle de propulsão, gerenciamento de dados de missão e sistema de alertas automatizado, via menu de texto no console.

---

## Estrutura do Projeto

```
projeto-espacial/
├── ComponenteEspacial.java   # Classe abstrata base
├── Sensor.java               # Interface de sensores
├── SensorTemperatura.java    # Implementa Sensor + herda ComponenteEspacial
├── SensorPressao.java        # Implementa Sensor + herda ComponenteEspacial
├── SensorRadiacao.java       # Implementa Sensor + herda ComponenteEspacial
├── DadosMissao.java          # Encapsulamento de dados sensíveis
├── SistemaPropulsao.java     # Classe abstrata de propulsão
├── PropulsaoQuimica.java     # Herda SistemaPropulsao
├── PropulsaoEletrica.java    # Herda SistemaPropulsao
└── SistemaMonitoramento.java # Classe principal com menu interativo
```

---

## Conceitos de POO Aplicados

### Classe Abstrata — `ComponenteEspacial`
Serve como base para todos os componentes do sistema. Define atributos comuns (`id`, `nome`, `status`, `temperatura`), métodos concretos (`ligar`, `desligar`) e o método abstrato `exibirStatus()`, que cada subclasse implementa à sua maneira.

### Interface — `Sensor`
Contrato que garante que todo sensor exponha os métodos `lerValor()`, `verificarFuncionamento()`, `retornarTipo()`, `getLimiteAlerta()` e `valorAcimaDoLimite()`. Implementada por `SensorTemperatura`, `SensorPressao` e `SensorRadiacao`.

### Encapsulamento — `DadosMissao`
Todos os atributos são `private`. Getters e setters validam os dados antes de aceitar alterações. As coordenadas da missão são protegidas por senha (`SPACE2026`) — sem a senha correta, o acesso é negado.

### Herança — `SistemaPropulsao`
`PropulsaoQuimica` e `PropulsaoEletrica` herdam de `SistemaPropulsao`, utilizam `super()` para chamar o comportamento base do método `acelerar()` e estendem com seus próprios atributos e cálculos de empuxo específicos.

### Sistema de Alertas
Os sensores são lidos automaticamente e os valores comparados com seus limites. Alertas são emitidos em três níveis:

| Nível | Critério |
|---|---|
| `ATENCAO` | Excesso de até 10% acima do limite |
| `ALERTA` | Excesso entre 10% e 30% |
| `CRITICO` | Excesso acima de 30% |

---

## Como Executar

### Pré-requisitos
- JDK 17 ou superior
- IntelliJ IDEA (recomendado) ou qualquer IDE Java

### IntelliJ IDEA
1. `File → New → Project → Java`
2. Defina o nome como `projeto-espacial` e selecione o JDK
3. Copie todos os `.java` para a pasta `src/`
4. Clique com botão direito em `SistemaMonitoramento.java → Run`

### Terminal
```bash
javac *.java
java SistemaMonitoramento
```

---

## Menu Principal

```
========================================
   PLATAFORMA DE MONITORAMENTO ESPACIAL
========================================
 1. Verificar sensores
 2. Controlar propulsão
 3. Gerenciar dados da missão
 4. Simular alertas
 5. Status completo
 0. Sair
========================================
```

---

## Dados Padrão de Inicialização

| Componente | ID | Detalhe |
|---|---|---|
| Sensor de Temperatura | S01 | Limite: 80 °C |
| Sensor de Pressão | S02 | Limite: 130 kPa |
| Sensor de Radiação | S03 | Limite: 5 mSv/h |
| Propulsão Química | P01 | LOX/LH2, 100% combustível |
| Propulsão Elétrica | P02 | 50 kW, 80% combustível |
| Missão | — | NEXUS-DEEP, 3 tripulantes |

Senha para acesso às coordenadas: `SPACE2026`

---

## Autores

Desenvolvido por Caique, Rafael e Taysir — FIAP, Global Solution 2026.1
