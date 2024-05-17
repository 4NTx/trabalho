# Trabalho de Imobiliária

Este é um projeto de gerenciamento de imóveis desenvolvido em JavaFX. Ele permite adicionar, listar, filtrar, alterar e excluir imóveis, além de gerar dados aleatórios para testes.

## Índice

- [Descrição do Projeto](#descrição-do-projeto)
- [Checklist das Solicitações](#checklist-das-solicitações)
- [Funcionalidades](#funcionalidades)
- [Classes e Funções](#classes-e-funções)
- [Tecnologias Usadas](#tecnologias-usadas)
- [Pré-requisitos](#pré-requisitos)
- [Configuração do Ambiente](#configuração-do-ambiente)
- [Execução do Projeto](#execução-do-projeto)
- [Dependências](#dependências)

## Descrição do Projeto

Este projeto foi criado para gerenciar uma lista de imóveis, com funcionalidades para adicionar, listar, filtrar, alterar e excluir imóveis. Também inclui a funcionalidade de preenchimento automático de dados para facilitar os testes.

## Checklist das Solicitações

<details>
  <summary>Descrição do Projeto</summary>

- [x] **Programa permite o controle dos imóveis disponíveis em uma imobiliária.**
- [x] **Imobiliária possui uma lista de imóveis com as seguintes características:**
  - [x] **Código**
  - [x] **Área construída**
  - [x] **Área total**
  - [x] **Número de quartos**
  - [x] **Tipo (Casa ou Apartamento)**
  - [x] **Preço**
  - [x] **Endereço (Cidade e Bairro)**

</details>

<details>
  <summary>Funcionalidades do Sistema</summary>

- [x] **Cadastro de um novo imóvel:**
  - [x] **Usuário deve fornecer todas as informações referentes ao novo imóvel.**
- [x] **Listagem de todos os imóveis:**
- [x] **Listagem dos imóveis que atendem a uma determinada condição:**
  - [x] **Tipo do imóvel (Casa ou Apartamento):**
  - [x] **Disponíveis em uma determinada cidade:**
  - [x] **Disponíveis em um determinado bairro de uma determinada cidade:**
  - [x] **Pertencentes a uma determinada faixa de preço:**
  - [x] **Número mínimo de quartos desejado:**
- [x] **Exclusão de Imóveis:**
  - [x] **Usuário exclui um imóvel a partir de seu código.**
- [x] **Alteração de Imóveis:**
  - [x] **Usuário altera um imóvel a partir de seu código.**

</details>

<details>
  <summary>Classes</summary>

### Classe `Imobiliaria`

- [x] **Atributo: `lista_de_imoveis` (Imovel)**

### Classe `Endereco`

- [x] **Atributos:**
  - [x] **cidade (String)**
  - [x] **bairro (String)**

### Classe `Imovel`

- [x] **Atributos:**
  - [x] **codigo (int)**
  - [x] **areaConstruida (float)**
  - [x] **areaTotal (float)**
  - [x] **numeroQuartos (int)**
  - [x] **tipo (int - Casa, Apartamento - ex: 0 – Casa, 1 – Apartamento)**
  - [x] **preco (float)**
  - [x] **localizacao (Endereco)**

### Métodos das Classes

- [x] **Atributos `private`.**
- [x] **Métodos `set` e `get` para todos os atributos.**

</details>

## Funcionalidades

- Adicionar Imóvel
- Listar Imóveis
- Filtrar Imóveis
- Alterar Imóvel
- Excluir Imóvel
- Gerar Dados Aleatórios para Testes

## Classes e Funções

### 1. `Main.java`

Esta é a classe principal que inicia a aplicação JavaFX.

### 2. Controladores

#### 2.1 `BaseController.java`

Classe abstrata que fornece métodos comuns para todos os controladores, como exibição de mensagens de erro e formatação de valores.

- `mostrarErro(String cabecalho, String mensagem)`: Exibe uma mensagem de erro com um cabeçalho e uma mensagem.
- `mostrarErroDeFormato(NumberFormatException e)`: Exibe uma mensagem de erro de formato.
- `parseFloat(String value, String fieldName)`: Converte uma string em um float.
- `parseInt(String value, String fieldName)`: Converte uma string em um inteiro.

#### 2.2 `AdicionarImovelController.java`

Controlador para a funcionalidade de adicionar imóveis.

- `handleAdicionarImovel()`: Abre um diálogo para adicionar um novo imóvel.
- `preencherCamposComDadosAleatorios()`: Preenche os campos do formulário com dados aleatórios para testes.
- `createFormGridPane()`: Cria o layout do formulário de adição de imóvel.

#### 2.3 `AlterarImovelController.java`

Controlador para a funcionalidade de alterar imóveis.

- `handleAlterarImovel()`: Abre um diálogo para alterar um imóvel existente.
- `createFormGridPane()`: Cria o layout do formulário de alteração de imóvel.

#### 2.4 `ExcluirImovelController.java`

Controlador para a funcionalidade de excluir imóveis.

- `handleExcluirImovel()`: Abre um diálogo para excluir um imóvel pelo código.

#### 2.5 `FiltrarImoveisController.java`

Controlador para a funcionalidade de filtrar imóveis.

- `handleFiltrarImoveis()`: Abre um diálogo para filtrar imóveis com base em critérios como tipo, cidade, bairro, preço e número de quartos.
- `createFormGridPane()`: Cria o layout do formulário de filtro de imóveis.

#### 2.6 `ListarImoveisController.java`

Controlador para a funcionalidade de listar imóveis.

- `handleListarImoveis()`: Atualiza a lista de imóveis exibida na tabela.

#### 2.7 `MainController.java`

Controlador principal que gerencia a interface principal da aplicação e instancia os outros controladores.

- `initialize()`: Inicializa a tabela de imóveis e os controladores.
- `handleAdicionarImovel()`: Chama o controlador de adicionar imóveis.
- `handleListarImoveis()`: Chama o controlador de listar imóveis.
- `handleFiltrarImoveis()`: Chama o controlador de filtrar imóveis.
- `handleExcluirImovel()`: Chama o controlador de excluir imóveis.
- `handleAlterarImovel()`: Chama o controlador de alterar imóveis.

### 3. Modelos

#### 3.1 `Endereco.java`

Classe que representa o endereço de um imóvel.

- `Endereco(String cidade, String bairro)`: Construtor para inicializar um endereço.
- `getCidade()`, `setCidade(String cidade)`: Métodos para obter e definir a cidade.
- `getBairro()`, `setBairro(String bairro)`: Métodos para obter e definir o bairro.

#### 3.2 `Imovel.java`

Classe que representa um imóvel.

- `Imovel(int codigo, float areaConstruida, float areaTotal, int numeroQuartos, int tipo, float preco, Endereco localizacao)`: Construtor para inicializar um imóvel.
- Métodos getters e setters para os atributos do imóvel.

### 4. Serviço

#### 4.1 `Imobiliaria.java`

Classe de serviço que gerencia a lista de imóveis.

- `adicionarImovel(Imovel imovel)`: Adiciona um novo imóvel à lista.
- `removerImovel(int codigo)`: Remove um imóvel da lista pelo código.
- `alterarImovel(int codigo, Imovel imovelAtualizado)`: Altera um imóvel existente na lista.
- `listarImoveis()`: Retorna a lista de todos os imóveis.
- Métodos para filtrar imóveis por tipo, cidade, bairro, preço e número de quartos.
- `imovelExiste(int codigo)`: Verifica se um imóvel com o código fornecido já existe.

## Tecnologias Usadas

- **JavaFX**: Usado para criar a interface gráfica do usuário (GUI).
- **Apache Maven**: Usado para gerenciar o ciclo de vida do projeto e suas dependências.
- **Apache Commons Lang**: Biblioteca que oferece utilitários de manipulação de strings e geração de dados aleatórios.
- **Java**: Linguagem de programação principal usada para desenvolver a aplicação.

## Pré-requisitos

- JDK 11 ou superior
- Apache Maven

## Configuração do Ambiente

1. Clone o repositório para o seu ambiente local:

   ```sh
   git clone https://github.com/4ntx/trabalho_java.git
   cd trabalho_java
   ```

2. Certifique-se de ter o JDK 11 ou superior instalado. Você pode verificar a versão instalada com:

   ```sh
   java -version
   ```

3. Certifique-se de ter o Maven instalado. Você pode verificar a versão instalada com:
   ```sh
   mvn -version
   ```

## Execução do Projeto

1. Compile o projeto com o Maven:

   ```sh
   mvn clean compile
   ```

2. Execute o projeto usando o plugin do JavaFX:
   ```sh
   mvn javafx:run
   ```

## Dependências

- JavaFX Controls (versão 13)
- JavaFX FXML (versão 13)
- Apache Commons Lang3 (versão 3.12.0)
