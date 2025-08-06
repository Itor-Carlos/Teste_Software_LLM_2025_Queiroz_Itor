**Teste de Vulnerabilidade com LLM**

# Participantes da equipe:

Heitor Saulo Dantas Santos
Itor Carlos Souza Queiroz
Lanna Luara Novaes Silva
Lavínia Louise Rosa Santos
Rômulo Menezes De Santana

---

O objetivo desta aplicação é demonstrar a utilização do ChatGPT para geração de testes de segurança baseado no artigo "How well does LLM generate security tests?" disponível no repositório [LLM Testing](https://github.com/LLM-Testing/LLM4SoftwareTesting). 

A vulnerabilidade abordada neste projeto Java foi a existente nas versões 1.65 e 1.66 da biblioteca [Bouncy Castle](https://www.bouncycastle.org/java.html), especificamente no método `OpenBSDBCrypt.checkPassword(...)`. Esta vulnerabilidade foi a apresentada no artigo como exemplo prático na utilização de LLM utilizando a solução proposta.


> Vulnerabilidade: CVE-2020-28052  
> Frameworks: Java + Maven + JUnit  
> Objetivo: Testar o comportamento vulnerável com código gerado por LLM


## Sobre a vulnerabilidade

Na biblioteca Bouncy Castle (BC), uma coleção de APIs do Java usadas em criptografia, há a vulnerabilidade OpenBSDBCrypt.checkPassword(...) que permite que senhas incorretas sejam validadas. Isso compromete a verificação de autenticação baseada em BCrypt.


## Arquivos principais

- `main/java/org/example/BcryptPasswordHashFunction.java`: aplicação simples com uma função que chama diretamente o método com vulnerabilidade. 
- `prompt.txt`: o prompt criado para a geração do teste, seguindo as especificações do artigo
- `test/java/org/example/BcryptPasswordHashFunctionTest.java`: arquivo de teste gerado pela LLM 


## Execução

 ```bash
mvn clean test



