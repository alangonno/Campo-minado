# 🎮 Campo Minado

Este projeto é uma implementação em Java do jogo Campo Minado, utilizando gerenciamento de dependências com Maven, testes unitários com JUnit 5 e tratamento de exceções para controlar o fluxo do jogo.

## ✨ Funcionalidades

* **Jogabilidade**: Abra campos, marque minas e tente limpar o tabuleiro sem detonar nenhuma bomba.
* **Interface de Console**: Interaja com o jogo diretamente pelo terminal.
* **Geração Aleatória**: O tabuleiro é gerado de forma aleatória a cada nova partida, garantindo um desafio diferente a cada vez.
* **Lógica de Vitória/Derrota**: O jogo detecta automaticamente quando o jogador vence (abrindo todos os campos seguros) ou perde (abrindo um campo minado).

## 🛠️ Tecnologias Utilizadas

### MAVEN

O projeto é gerenciado com o **Apache Maven**.
* **Gerenciamento de Dependências**: O `pom.xml` está configurado para gerenciar todas as bibliotecas necessárias, com o JUnit 5, garantindo que as versões sejam consistentes e fáceis de atualizar.

### Tratamento de Erros e Exceções

Uma parte crucial da lógica do jogo é controlada por um sistema de exceções customizadas.

* `ExplosaoException`: Uma exceção do tipo `RuntimeException` que é lançada quando o jogador tenta abrir um campo que contém uma mina. Essa exceção interrompe imediatamente o fluxo normal do jogo e sinaliza a derrota.
* `SairException`: Uma exceção criada para gerenciar o encerramento do jogo a pedido do jogador. Ao digitar "sair", essa exceção é lançada para finalizar o loop principal do jogo de forma graciosa.

### JUnit 5

A qualidade e a corretude da lógica do jogo são garantidas por uma suíte de testes unitários desenvolvida com **JUnit 5**.

* **Testes de Lógica do Modelo**: As classes de modelo (como `Campo` e `Tabuleiro`) são extensivamente testadas para validar todas as regras de negócio:
    * Verificação da abertura de campos.
    * Validação da lógica de vizinhança.
    * Teste da alternância de marcação (bandeira).
  
