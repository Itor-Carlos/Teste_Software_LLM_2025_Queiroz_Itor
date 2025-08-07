# Teste de Vulnerabilidade com LLM

**Participantes da equipe:**

- Heitor Saulo Dantas Santos
- Itor Carlos Souza Queiroz
- Lanna Luara Novaes Silva
- Lavínia Louise Rosa Santos
- Rômulo Menezes De Santana

---

O objetivo desta aplicação é demonstrar a utilização do ChatGPT para geração de testes de segurança baseado no artigo "How well does LLM generate security tests?" disponível no repositório [LLM Testing](https://github.com/LLM-Testing/LLM4SoftwareTesting). 

A vulnerabilidade abordada neste projeto Java foi a existente nas versões 1.65 e 1.66 da biblioteca [Bouncy Castle](https://www.bouncycastle.org/java.html), especificamente no método `OpenBSDBCrypt.checkPassword(...)`. Esta vulnerabilidade foi a apresentada no artigo como exemplo prático na utilização de LLM utilizando a solução proposta.


> Vulnerabilidade: CVE-2020-28052  
> Frameworks: Java + Maven + JUnit  
> Objetivo: Testar o comportamento vulnerável com código gerado por LLM


## Sobre a vulnerabilidade

Na biblioteca Bouncy Castle (BC), uma coleção de APIs do Java usadas em criptografia, há a vulnerabilidade OpenBSDBCrypt.checkPassword(...) que permite que senhas incorretas sejam validadas. Isso compromete a verificação de autenticação baseada em BCrypt.

## Estrutura do Repositório

O repositório está dividido em *duas aplicações distintas*:

### 1. *Aplicação sem uso da API do ChatGPT*

Esta aplicação utiliza um prompt enviado à interface web do ChatGPT para gerar automaticamente um teste de segurança com base na vulnerabilidade descrita.

*Arquivos principais:*

* main/java/org/example/BcryptPasswordHashFunction.java: aplicação simples que invoca o método vulnerável.
* prompt.txt: prompt utilizado na geração do teste conforme o artigo.
* test/java/org/example/BcryptPasswordHashFunctionTest.java: arquivo de teste gerado pela LLM via API do ChatGPT.

*Execução:*

```bash
mvn clean test
```

### 2. *Aplicação com uso da API do ChatGPT*

Esta aplicação envia o código cliente e um prompt estruturado diretamente para a API oficial do ChatGPT, que retorna automaticamente um teste JUnit com o objetivo de verificar se o método está vulnerável à falha especificada (CVE-2020-28052).

*Arquivos principais:*

* src/main/java/org/example/BcryptPasswordHashFunction.java:
  Contém o código cliente que chama a API vulnerável `OpenBSDBCrypt.checkPassword`.
* src/main/java/org/example/GeradorDeTestes.java:
  Classe responsável por enviar o prompt e o código para a API do ChatGPT e gerar o arquivo de teste automaticamente.
* prompts/prompt.txt:
  Prompt estruturado enviado à API, contendo instruções e o código cliente.
* src/test/java/org/example/BcryptPasswordHashFunctionTest.java:
  Arquivo de teste gerado automaticamente pela API do ChatGPT com base no `prompt.txt`.

*Execução:*

Primeiro configure a variável de ambiente com a sua chave da API do OpenAI
```bash
mvn compile exec:java -Dexec.mainClass="org.example.GeradorDeTestes"
mvn clean test
```





