# Attornatus API REST

##Resposta das Questões:

###1.	Durante a implementação de uma nova funcionalidade de software solicitada, quais critérios você avalia e implementa para garantia de qualidade de software?

    O primeiro aspectos que busco compreender é o proposito da nova funcionalidade, primeiramente compreendendo qual a demanda, necessidade especifica ou geral que trouxe a tona a precisão de sua implementação. Com a devida clareza sobre o que demandou a nova funcionalidade, busco me familiarizar com o que de fato ela se propõe a fazer e como será sua relação com as demais funcionalidades, desta forma fica mais claro como e por onde seu desenvolvimento de se dá. 
A partir deste ponto, umas das abordagens que podem ser adotadas para a implementação da funcionalidade é o uso do TDD(Test Drive Development), uma prática de desenvolvimento de software que toma como base desenvolver as funcionalidades começando pelos testes e ir implementando as classes e métodos necessários de acordo com as falhas dos testes, outra prática de desenvolvimento que em todos os casos deve ser abordada é a do Clean Code, pois um código de fácil compreensão e legibilidade, além de ser uma boa prática para com os companheiros de time que podem vir a trabalhar com o código, garante a produtividade e continuidade na produção do mesmo.
  
###2.	Em qual etapa da implementação você considera a qualidade de software?

    A qualidade do software deve ser pensada e garantida desde o momento de sua idealização como projeto, até a entrega do produto final, onde a qualidade pode ser garantida através do uso de testes automatizados, boas práticas, colaboração entre os membros da equipe, com o usa de pipelines desenvolvidas pelos DevOps, dentre outras práticas, mas o mais importante está no compromisso e na seriedade com o desenvolvimento de um código limpo e claro.

# Sobre API REST:

    Avaliação Attornatus é um Projeto direcionado ao desenvolvimento back-end de uma API, com a Linguagem Java, Framework Spring Boot, dentro do modelo arquitetural RESTful, fazendo uso da prática do Clean Code.
 
    O projeto é basicamente uma API de consulta e cadastro de pessoas e seus endereços. Todos seus End-Points podem ser acessados através da interface do Swagger V3, basta acessar este Link: http://localhost:8080/swagger-ui/index.html#/, quando o projeto estiver sendo executado. O projeto pode ser executado de duas formas, clonando o projeto em um repositório local, importando-o para a IDE de sua preferencia e o rodando, ou executando mo terminal do seu S.O. o arquivo attornatus-assessment-0.0.1-SNAPSHOT.jar contido na pasta “target” do projeto.
