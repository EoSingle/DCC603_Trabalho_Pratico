# Sistema de RPG Online

## Descrição

Este é um protótipo desenvolvido durante a disciplina **DCC603 - Engenharia de Software**.

O **Sistema de RPG Online** é uma plataforma que permite a jogadores de RPG de mesa jogarem de forma remota. Com funcionalidades voltadas tanto para o mestre quanto para os jogadores, o sistema oferece:

- Criação e gerenciamento de campanhas.
- Definição de regras e cenários personalizados.
- Fichas interativas de personagens.
- Rolagem de dados integrada.
- Chat em tempo real para comunicação durante as sessões.
- Visualização de mapas com movimentação de tokens.
- Gerenciamento de turnos de combate.
- Registro de logs para acompanhar o histórico das sessões.

## Membros do Grupo

- Lucas Albano Olive Cruz
- Romana Gallete Mota

## Funcionalidades Implementadas

- Criação de campanhas.
- Cadastro e gerenciamento de jogadores.
- Fichas de personagens com atributos personalizáveis.
- Rolagem de dados.
- Comunicação via chat em tempo real.
- Salvamento e carregamento de campanhas em arquivos JSON.

## Requisitos

### Dependências

Este projeto utiliza o **Maven** para gerenciamento de dependências. O arquivo `pom.xml` contém todas as dependências necessárias.

Principais bibliotecas utilizadas:

- **Gson**: Manipulação de arquivos JSON.
- **JUnit**: Testes automatizados.

## Estrutura do Projeto

```plaintext
DCC603_Trabalho_Pratico/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │    └── com/
│   │   │        └── rpg/
│   │   │            ├── models/      # Classes de modelo e lógica de negócios
│   │   │            ├── services/    # Classes de controle e serviços
│   │   │            ├── utils/       # Classes auxiliares
│   │   │            └── Main.java    # Ponto de entrada da aplicação
│   │   └── resources/                # Dados persistidos (JSON)
│   │       ├── campaigns/
│   │       ├── users/
│   │       ├── chats/
│   │       └── logs/
│   └── test/
│       └── java/
│           └── com/
│               └── rpg/
│                   ├── models/       # Testes das classes de modelo
│                   ├── services/     # Testes das classes de serviço
│                   └── utils/        # Testes das classes auxiliares
├── LICENSE                           # Licença do projeto
├── pom.xml                           # Arquivo de configuração do Maven
├── run.sh                            # Script para execução do projeto
└── README.md                         # Documentação do projeto
```

## Como Executar o Projeto

### Pré-requisitos

Certifique-se de ter o **Java** e o **Maven** instalados em sua máquina:

- **Java 17 ou superior**:  
  Verifique a instalação com o comando:  

  ```bash
  java -version
  ```

- **Maven**:  
  Verifique a instalação com o comando:  

  ```bash
  mvn -v
  ```

### Passos para Execução

1. Clone o repositório para sua máquina local:

   ```bash
   git clone https://github.com/EoSingle/DCC603_Trabalho_Pratico.git
   cd DCC603_Trabalho_Pratico
   ```

2. Compile e execute o projeto utilizando o script `run.sh`:

   ```bash
   sudo chmod +x run.sh
   ./run.sh
   ```

   Esse comando compilará o projeto e executará a classe principal `Main.java`.

### Executando Testes Automatizados

Para rodar os testes automatizados, utilize o comando:

```bash
mvn test
```

