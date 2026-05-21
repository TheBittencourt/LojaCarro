# Relatório - Automação de Testes com GitHub Actions

## Introdução
O objetivo desta atividade foi implementar um pipeline de integração contínua (CI) utilizando **GitHub Actions** para garantir que nenhum código seja integrado ao repositório principal sem passar pelos testes automatizados.  

## Funcionamento do GitHub Actions
O GitHub Actions é uma ferramenta de automação que permite configurar workflows para rodar em eventos específicos do repositório, como `push` ou `pull_request`.  
No nosso caso, configuramos um workflow que:
1. Faz checkout do código.
2. Configura o ambiente Java (versão 17).
3. Utiliza cache de dependências Maven para otimizar o tempo de execução.
4. Sobe um serviço MySQL em container para rodar os testes.
5. Executa `mvn test` para validar as funcionalidades do sistema.

## Etapas do Workflow
- **Checkout**: baixa o código do repositório para o runner.  
- **Setup Java**: instala a versão do Java necessária (17 ou 21).  
- **Cache Maven**: reaproveita dependências já baixadas, acelerando o build.  
- **Serviço MySQL**: cria um banco de dados temporário para os testes.  
- **Execução dos testes**: roda `mvn test`, validando cadastro, busca, atualização, remoção de veículos e login.  

## Vantagens da Integração Contínua
- Detecção rápida de erros antes do merge.  
- Garantia de qualidade: apenas código validado entra na branch principal.  
- Automação: elimina a necessidade de rodar testes manualmente.  
- Consistência: todos os desenvolvedores têm o mesmo processo de validação.  

## Problemas Encontrados
Durante a atividade enfrentamos alguns desafios:
- **Falha no ApplicationContext**: os testes não subiam porque faltava configuração mínima de banco de dados.  
- **Configuração do MySQL no Actions**: inicialmente o container não iniciava corretamente, sendo necessário ajustar variáveis de ambiente (`MYSQL_ALLOW_EMPTY_PASSWORD`).  
- **Aviso de Node.js 20**: o GitHub Actions alertou sobre a depreciação do Node.js 20, resolvido adicionando `FORCE_JAVASCRIPT_ACTIONS_TO_NODE24=true` no workflow.  
- **Simulação de falhas**: ao alterar um teste de propósito, o pipeline bloqueou o merge, confirmando que a proteção estava funcionando.  
- **Correção dos testes**: após corrigir, o pipeline passou e o merge foi permitido.  

## Conclusão
Com o pipeline configurado, garantimos que:
- Nenhum código é integrado sem passar pelos testes.  
- O ambiente de CI está preparado para rodar com MySQL.  
- O processo de auto-merge e branch protection assegura qualidade e confiabilidade no repositório.  

Essa automação traz mais segurança, velocidade e confiança no desenvolvimento do sistema da loja de carros.
