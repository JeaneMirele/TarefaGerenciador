## TarefaGerenciador

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)  ![Apache Tomcat](https://img.shields.io/badge/apache%20tomcat-%23F8DC75.svg?style=for-the-badge&logo=apache-tomcat&logoColor=black) ![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white) ![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white)  ![Eclipse](https://img.shields.io/badge/Eclipse-FE7A16.svg?style=for-the-badge&logo=Eclipse&logoColor=white) ![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)

## Detalhes técnicos
A. Java Web utilizando JavaServer Faces (JSF)

B. Banco de dados PostgreSQL:

Operações de Crud Create - Read - Update - Delete

## Como executar

### Pré-requisitos

JDK 1.8

Apache Tomcat 9.0

Apache Maven

IDE Eclipse

PostgreSQL

Dependência JSF 2.2 (conforme o arquivo pom.xml)

### Passos para a instalação local

- Clone o repositório ou baixe o arquivo compactado em .zip e descompacte;

- Abra o projeto no Eclipse;

- Restaure as dependências com o Maven. Para isso, navegue para Project > Update Maven Project;

- Configure o compilador Java. Para isso, navegue para Project > Properties > Java Compiler e certifique-se de estar configurado para 8;

- Crie um banco postgres local com as propriedades de conexão que estão presentes na classe ConnectionFactory;

- Navegue para Project > Properties > Server e configure o Apache Tomcat 9.0;

- Em Project Explorer, clique com o botão direito do mouse sobre o projeto e clique em Run As > Run on Server

## Schema do Banco de dados

Tabela: tarefa
id SERIAL PRIMARY KEY,

titulo VARCHAR(255) NOT NULL,

descricao VARCHAR(255), 

responsavel VARCHAR(100),

prioridade VARCHAR(50),

deadline DATE,

situacao VARCHAR(50)

