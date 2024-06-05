# Descrição

O projeto "Conversor de Moeda" é uma aplicação Java que permite ao usuário converter valores entre diferentes moedas. A aplicação utiliza a API ExchangeRate-API para obter as taxas de câmbio atuais e converte os valores conforme solicitado pelo usuário. As buscas realizadas são armazenadas em um arquivo JSON para referência futura. esse projeto foi proposto pela curso de back-end da Alura

# Funcionalidades

Converter valores entre diferentes moedas.
Suporte para as seguintes moedas:
Dólar Americano (USD)
Peso Argentino (ARS)
Real Brasileiro (BRL)
Peso Colombiano (COP)
Salvar todas as buscas realizadas em um arquivo JSON.

# Estrutura do Projeto
O projeto está organizado nas seguintes classes:

ConversorDeMoeda: Classe principal que contém o método main e gerencia a interação com o usuário.
ApiClient: Classe responsável por fazer a requisição à API ExchangeRate-API e obter as taxas de câmbio.
Conversor: Classe que realiza a conversão dos valores com base na taxa de câmbio obtida.
Currency: Enum que define as moedas suportadas pelo conversor.
Busca: Classe que representa uma busca realizada pelo usuário, contendo as informações da conversão.

# Uso
1- Ao executar o programa, será exibida uma mensagem de boas-vindas e um menu com as opções de conversão.
2- Escolha a opção desejada digitando o número correspondente.
3- Digite a quantidade que deseja converter.
4- O programa exibirá o valor convertido.