# TarefaGerenciador

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white) ![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white) ![Apache TomEE](https://img.shields.io/badge/apache%20tomee-%23F8DC75.svg?style=for-the-badge&logo=apache&logoColor=black) ![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white) ![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white) ![Eclipse](https://img.shields.io/badge/Eclipse-FE7A16.svg?style=for-the-badge&logo=Eclipse&logoColor=white) ![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)

Sistema de gerenciamento de tarefas desenvolvido em Java Web com arquitetura corporativa utilizando JSF, CDI, EJB e JPA com banco de dados PostgreSQL.

---

## 🛠️ Tecnologias Utilizadas

- **Java (JDK 8 ou 21)**
- **JSF (JavaServer Faces 2.3)**: Camada de apresentação e componentes de interface web
- **CDI (Contexts and Dependency Injection 2.0)**: Injeção de dependências e escopo de beans (`@Named`, `@ViewScoped`)
- **EJB (Enterprise JavaBeans 3.2)**: Camada de negócios e controle transacional (`@Stateless`)
- **JPA 2.2 / Hibernate**: Mapeamento objeto-relacional (ORM) e consultas dinâmicas
- **PostgreSQL 15**: Banco de dados relacional
- **Docker & Docker Compose**: Containerização do banco de dados
- **Apache TomEE 8 (WebProfile)**: Servidor de aplicação Java EE / Jakarta EE
- **Apache Maven**: Gerenciamento de dependências e build

---

## 📋 Pré-requisitos

Antes de iniciar, certifique-se de ter instalado em sua máquina:

- [JDK 8+](https://adoptium.net/) (Java instalado e configurado no PATH)
- [Apache Maven](https://maven.apache.org/download.cgi) (ou o Maven embutido na IDE)
- [Docker Desktop](https://www.docker.com/products/docker-desktop/) (com Docker Compose ativo)

---

## 🚀 Como Executar o Projeto

### Passo 1: Subir o Banco de Dados com Docker

No diretório raiz do projeto, execute o comando abaixo para iniciar o container do PostgreSQL em segundo plano:

```bash
docker compose up -d
```

> 💡 **Nota**: O banco de dados `tarefas` será inicializado automaticamente na porta padrão `5432`. As tabelas são criadas/atualizadas automaticamente pelo JPA Hibernate (`hbm2ddl.auto=update`).

---

### Passo 2: Executar a Aplicação

Você pode executar a aplicação diretamente pelo terminal ou utilizando o Eclipse IDE:

#### Opção A: Pelo Terminal / Linha de Comando (Recomendado)

Na pasta raiz do projeto, execute:

```bash
mvn tomee:run
```

O plugin do Maven baixará o runtime do **Apache TomEE 8** (se for a primeira vez) e inicializará o servidor já com a aplicação publicada.

#### Opção B: Pelo Eclipse IDE

1. Abra o projeto no Eclipse:
   - **File** > **Import...** > **Existing Maven Projects** e selecione a pasta do projeto.
2. Atualize as dependências:
   - Clique com o botão direito no projeto > **Maven** > **Update Project...** (ou atalho `Alt + F5`).
3. Para rodar:
   - Clique com o botão direito no projeto > **Run As** > **Maven build...**
   - No campo **Goals**, digite `tomee:run` e clique em **Run**.

---

### Passo 3: Acessar a Aplicação

Com o servidor iniciado, acesse no navegador:

- **Cadastrar Tarefa**: [http://localhost:8080/cadastro.xhtml](http://localhost:8080/cadastro.xhtml)
- **Listagem de Tarefas**: [http://localhost:8080/lista-tarefas.xhtml](http://localhost:8080/lista-tarefas.xhtml)

---

## 🛑 Como Parar os Serviços

- **Parar o servidor TomEE:** Pressione `Ctrl + C` no terminal em que o `mvn tomee:run` está rodando.
- **Parar o container do PostgreSQL:**
  ```bash
  docker compose down
  ```
