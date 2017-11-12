# Gerenciar os dados do socio torcedor

# Premissas
Maven
Java 8

# Tecnologias

  Java 8
  Spring Boot
  Spring Rest
  Spring Data
  Swagger
  JUnit
  Base de Dados H2
  
Aplicação Spring boot com API REST e Java 8.
OBS: Foi utilizado o h2database em tempo de execução.

Instruções de execução da aplicação

Realizar o clone do projeto.
Entrar no prompt de comando.
Executar o seguinte comando " mvn clean package " na pasta raiz do projeto.
Logo em seguida se tudo ocorrer bem será apresentando a mensagem de build com sucesso
executar o comando: java -jar target/campanha-0.0.1-SNAPSHOT.jar.
Será iniciado on port(s) 8080 (http/1.1).
A aplicação estará respondendo na url: http://localhost:8080.
Exemplo de requisição via postman:


Cadastrar Campanha e Time.

Uri: http://localhost:8080/campanha/inserirCampanha

	{
        "nomeCampanha": "Sou mais Vitória",
         "dtInicio": "01/11/2017",
        "dtFim": "30/11/2017",
         "timeCoracao": 
           {
        	"nomeTime": "Vitória"
          }
    }

Listar campanhas vigentes:

Uri:  http://localhost:8080/campanha/listaCampanhasVigente

Remover campanha por código
http://localhost:8080/campanha/removerCampanha/1

Alterar Campanha:
http://localhost:8080/campanha/alterarCampanha/1

{
        "nomeCampanha": "Avante Palmeiras",
        "dtInicio": "11/11/2017",
        "dtFim": "13/11/2017",
        "timeCoracao": {
        	"idTime": "2",
            "nomeTime": "Palmeiras"
        }
}

Inserir Socio
http://localhost:8080/socio/inserirSocio
{
        "email": "dmjesus89@gmail.com",
        "nomeCompleto": "Diego Mauricio de Jesus Oliveira",
        "dtNascimento": "25/04/1989",
         "timeCoracao": 
           {
        	"nomeTime": "Vitória"
          }
    }


Cadastrar Socio
  
  
