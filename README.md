# screenmatch web
Projeto para o curso "Java: criando sua primeira API e conectando ao front"

Existe uma parte frontend, que foi criada para testar as APIs criadas durante o curso. Instalar a extenção "live server" do VSCode para "rodar" o front.

# Banco de dados

## Postgres

Instalado no Docker para facilitar o desenvolvimento e não "sujar" o Sistema Operacional de trabalho.

_Nota: não criei um volume para esse container porque a aplicação é muito simples (somente estudo), não vejo necessidade de persistência_

### Rodar:
```bash
sudo docker run -p 5432:5432 -e POSTGRES_PASSWORD=admin -d postgres
```

Caso queira rodar com volume, eu preparei um arquivo do Docker Compose para rodar:
```bash
sudo docker compose up
```

# Utilidades

## Links

### Documentação sobre Derived Queries

https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

### Artigo sobre SQL Join

https://www.alura.com.br/artigos/join-e-seus-tipos


## Problemas que podem ocorrer

### Porta já ocupada

Pode acontecer de a porta 8080 já estar ocupada.

```bash
***************************
APPLICATION FAILED TO START
***************************

Description:

Web server failed to start. Port 8080 was already in use.

Action:

Identify and stop the process that's listening on port 8080 or configure this application to listen on another port.

Process finished with exit code 1
```

Neste caso altere a porta no **application.properties**:

```yaml
server.port=8081
```