# Cars

## ESTÓRIAS DE USUÁRIO
### [001] Estrutura Inicial do projeto
### [002] Implementação do interceptor pra leitura do token


## SOLUÇÃO
### [001] Estrutura Inicial do projeto - Inclui estrutura inicial do projeto utilizando Spring Boot e Maven como derenciador de dependências

### [002] Implementação do interceptor pra leitura do token
Inclui HandlerInterceptor pra leitura e validação do token que vem no header das requisições /api/cars/* e um WebConfig que registra o inteceptor

### [003] Implementação servico de carros
Crição da Classe CarEntity, da tabela car, do Repository, Serviço e controler pra CRUD de um carro.Inclusão dos testes unitários utilizando Junit e Mockito

### [004] Implementação servico de usurios
Crição da Classe UserEntity, da tabela car, do Repository, Serviço e controler pra CRUD de um usurio.Inclusão dos testes unitários utilizando Junit e Mockito

### [005] Implementação de Autenticação/autorização 
Utilização de Spring Security para autenticação e autorização.Ao realizar login, um token JWT é atribuído ao usuário com duração de 15 minutos para acessar endpoint /api/car