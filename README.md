# Sentinela AI - Análise de sentimentos em larga escala (Azure & Spring Boot)

O Sentinela AI é uma solução de inteligência de dados desenvolvida para processar feedbacks de clientes em larga escala. Utilizando o ecossistema da Microsoft Azure, o projeto realiza a ingestão, análise e persistência de registros do dataset da Olist, transformando comentários brutos em métricas estratégicas.

## 📂 Base de Dados
Os dados utilizados neste projeto foram extraídos da plataforma Kaggle, referentes ao dataset público da Olist (Brazilian E-Commerce Public Dataset). Esta base contém informações reais de milhares de pedidos e avaliações de consumidores.

## Tecnologias e Arquitetura

* **Linguagem & Framework:** Java 21 com Spring Boot 3.4.12.
* **Inteligência Artificial:** Azure Cognitive Services (Text Analytics API) para classificação de sentimentos.
* **Banco de Dados:** Azure Cosmos DB (NoSQL) para armazenamento escalável.
* **Processamento de Dados:** OpenCSV para leitura eficiente de datasets massivos.

## 📊 Resultados da Análise (Dataset Olist)

Após o processamento de **10.620 registros**, consolidamos os seguintes indicadores de satisfação:






<details>
  <summary> Clique aqui para ver os prints da Azure (Cosmos DB & IA)</summary>

  #### 1. Volume de Dados no Azure Cosmos DB
  Neste print, mostro a execução da query SQL que comprova o processamento de mais de 10.000 registros.
  ![Print SQL Cosmos](https://github.com/JaiDev-bot/sentinela-AI/blob/main/Cosmo.png)

  #### 2. Integração com Azure AI Services
  Comunicação com a API de IA para análise de sentimentos ao Cosmo db.
  ![Print Azure AI](https://github.com/JaiDev-bot/sentinela-AI/blob/main/Cosmo1.png)

  #### 3. Métricas do Azure Cognitive Search
  Metricas que indicam quantas vezes o serviço da microssoft tentou conversar com a API e gerou status 200 (sucesso).
  * A quantidade de successful calls foi menor que o total, isso pode ocorrer devido ao processamento rapido de 30.000 linhas, ou os comentarios fossem muito grandes e a capacidade do plano gratuito pode ser reduzida para payloads grandes.
  ![Print Azure AI](https://github.com/JaiDev-bot/sentinela-AI/blob/main/AzureCognit.png)

  #### 4. JSON com respostas geradas pela IA
  Arquivo JSON extraido do Cosmo DB depois da analise da IA.
  
 ![Print VSCODE](https://github.com/JaiDev-bot/sentinela-AI/blob/main/json.png)


