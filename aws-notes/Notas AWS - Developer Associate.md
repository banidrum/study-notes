# Notas AWS

- NÃO há a possibilidade de rodar AWS Lambda com um runtime de Node.js não disponibilizado pela AWS;
- O padrão de filas SQS em paralelo para envio de mensagens do SNS se chama Fan-out;
- A API para aumentar o tempo de resposta do SQS é a ChangeMessageVisibility;
- O cálculo de WCU é feito da seguinte maneira:

    WCU’s dependem do tamanho dos arquivos, por exemplo:

    Se serão lidos 6 arquivos/segundo e eles pesarem 4.5Kb cada um, vai haver um arredondamento para 5Kb, ou seja, 6 * 5 = 30 WCU’s

- A chave SSE-S3 é totalmente gerenciada pela AWS (data e master keys);
- A API para aumentar o tempo de resposta do SQS é a ChangeMessageVisibility;
- A chave SSE-S3 é totalmente gerenciada pela AWS (data e master keys);
- Uma única operação PUT tem o limite de 5GB no S3. Caso a operação seja multi-part, o limite é 5TB;
- Para fazer o push de uma imagem ao ECR é necessário usar os seguintes comandos, na seguinte ordem:
    1. $(aws ecr get-login --no-include-email)
    2. docker push <url>
- Os arquivos de configuração de ambiente do EBS devem ser configurados da seguinte forma:

    Ser colocado em uma pasta chamada “.ebsextensions” e a pasta deve possuir um arquivo chamado “.config”

- Mensagens do SNS são “pushadas” por subscribers, enquanto as mensagens do SQS dependem do poll do consumer
- Para calcular o RCU quando o item a ser lido tem um tamanho > 4KB

    Cada RCU tem 4KB, e as RCUS são arrendondas, por exemplo:

    1200 itens, cada um com 6KB. 2 RCU’s são necessárias, então 8KB em RCU’S

    1200/60 = 20 itens / segundo. 20 itens * 2 RCUS = 40

- O API Gateway suporta todos os métodos HTTP, incluindo HEAD e OPTIONS
- Algumas alternativas para uma situação aonde se tem um banco de dados do RDS recebendo muitas requests são: Usar o ElastiCache, criar réplicas de leitura…
- As duas API’s do DynamoDB que são mais performáticas usando a chave primária para busca são: Query e GetItem
- O BeanStalk usa o CloudFormation para provisionar os recursos
- O tamanho total dos arquivos no S3 pode ultrapassar 5TB
- O BeanStalk tem somente uma versão da aplicação
- A seção necessária em todo template do CloudFormation é a Resources
- Kinesis Streams não escalam automaticamente, quem faz isso é Firehose
- Os subscribers do SNS são adicionados no SNS Topics
- Counters não pertencem ao API Gateway
- Parameters não permitem condições no CloudFormation
- O número máximo de atributos para gerar uma chave primária no Dynamo é 2
- CloudFront não suporta UDP
- X-Ray sampling não é útil para debuggar
- STS não é suportado pelo API Gateway
- Um consumer do SQS recupera no máximo 10 mensagens de uma vez só
- Repositórios são automaticamente criptografados (at rest)
- 'Error: Memory Size: 3008 MB Max Memory Used' - Lambda ficou sem RAM
- Quando não há preocupação em relação ao tempo de indisponibilidade, usar o all-at-once no EBS é uma boa ideia.
- Uma opção “barata” de deletar uma tabela no DynamoDB é deletar e recriar a tabela
- Versões NÃO podem ser habilitadas para uma única pasta no S3
- Para conseguir fazer queries rápidas no DynamoDB, é uma boa ideia usar parallel scans
- Quando se quer invalidar o cache é necessário passar o seguinte Header: Cache-Control: max-age=0
- CloudTrail não é útil para monitorar eventos e também não é util para troubleshooting
- Lambda escala automaticamente
- Para retornar UM ou MAIS itens do DynamoDB, usa-se o BatchGetItem
- O CodeBuild pega as instruções de um arquivo no diretório root chamado buildspec.yml
- Uma configuração de política do IAM deve ter: Effect, Actions e Resources
- No CodePipeline cada stage possui um conjunto de actions
- Quando tiver uma Exception em alguma Lambda Function, é possível visualizar a causa do CloudWatch Logs
- KMS armazena a CMK, recebe dados dos clientes, que são criptografados e mandados de volta
- Um bom jeito de fazer deploy sem aumentar os custos é o Rolling
- Exported Output Values do CloudFormation devem ter nomes únicos em uma Região
- Serviços de terceiros que estão no Route 53 devem usar CNAMES para fazer o mapeamento de domínio
- É possível determinar expiração de Logs do Cloudwatch através de políticas de expiração de logs
- A conta de admin da AWS deve ser acessada apenas por um administrador usando MFA
- SSE-C cuida de chaves disponibilizadas pelo cliente
- Para disponibilizar informação do CloudFormation devemos usar Export
- A sessão Groups não é válida no CloudFormation
- CloudWatch auxilia apenas nas métricas e nao seria util para visualizar problemas de autorização
- Quando se tem a ProvisionedThroughputExceededException no Kinesis é necessário fazer mensagens por batch
- Quando os dados não estao sendo enviadas para o X-Ray usando Lambda, é necessário permitir o rastreamento
- DependentParamater não é um parâmetro válido no CloudFormation
- Para verificar se o deploy do CodeDeploy teve sucesso, o ValidateService deve ser usado
- Nao existe um limite para mensagens armazenadas no SQS
- Para criptografar grandes volumes de dados (>4KB) com o KMS, usa-se a API GenerateDataKey
- ALB não suporta TCP
- Para criar um cluster de containers, é necessário colocar o nome no arquivo ecs.config
- Para configurar alta disponibilidade de instâncias EC2 é bom ter 2 ASG’s como mínimo
- SQS escala automaticamente
- Quando é preciso que as mensagens estejam ordenadas, usa-se a SQS FIFO
- CodeBuild escala automaticamente
- Security Groups não ajudam na proteção de objetos do S3
- Para deletar mensagens do SQS sem nenhuma recofinguração, se usa o PurgeQueue
- Se uma região nao for selecionada, a região que estará sendo usada é a us-east-1
- Para rapidamente reverter o estado da aplicação caso algum problema ocorra, usa-se o deploy do tipo Immutable no CodeDeploy
- Para estar preparado contra possíveis desastres que possam afetar um banco de dados RDS, algumas opções são habilitar Multi AZ e backups do RDS
- Para atrasar uma mensagem do SQS, é preciso usar a API DelaySeconds
- A ordem dos eventos no Appsec é a seguinte: DownloadBundle => BeforeInstall => ApplicationStart => ValidateService
- Para atualizar o DNS sem downtime, cria-se um Elastic IP
- Para evitar receber responses vazios no SQS, usa-se o Long Pollling
- Quando se quer realizar uma Query no DynamoDB usando uma chave primária e outra alternativa, usa-se LSI
- Quando a aplicação precisa atender milhares de requisições no EC2, usa-se o Network Load Balancer
- Para organizar os rastreios do X-Ray em order e filtra-los, usa-se Annotations
- Para empacotar uma Lambda function com dependências, o correto é colocar a função e as dependências em um ZIP
- O parâmetro para ordenar mensagens no SQS é o MessageGroupId
- Para fazer dados expirarem automaticamente no DynamoDB, usa-se TTL
- No Elastic Beanstalk, recursos que devem persistir a remoção de ambientes, devem ser criados externamente
- Autorização no API Gateway pode ser feita usando um Lambda Authorizer
- É possível compartilhar objetos de um bucket S3 através de uma Pre Signed URL
- Para criptografar artefatos do CodeBuild, usa-se uma chave do KMS
- Quando se fala em DNS switch no Elastic Beanstalk, se trata de um deployment Blue/Green
- Para criptografar dados que serão passados para uma Lambda Function, usa-se Envelope Encryption
- CodeDeploy Agent pode ser usado nos deploys do CodeDeploy para armazenar apenas 2 revisões da aplicação
- Para continuar rastreando uma aplicação com o X-Ray e reduzir os custos, é possível usar o X-Ray Sampling
- Cognito User Pools é um serviço totalmente gerenciado, e é útil para fazer autenticação na camada do API Gateway
- O tempo do monitoramento detalhado de instâncias EC2 é de 1 minuto
- Toda invocação assíncrona de função Lambda no SQS tem a execução tentada 2 vezes, caso a execução falhe, a invocação vai parar em uma DLQ
- Para ter métricas de uso médio de memória RAM em ASG’s do EC2, é necessário usar a API PutMetricData
- Alarmes de alta resolução do CloudWatch podem ser definidos em 10s ou 30s
- Para mudar o DNS do IP da instância sem muito tempo de indisponibilidade, usa-se Elastic IP
- Recursos que devem persistir a delete de um ambiente devem ser criados externamente
- Para controlar acesso usando uma aplicação de terceiros no API Gateway, usa-se o Lambda Authorizer
- Quando é preciso realizar um deploy no Beanstalk sem ter muito tempo de indisponibilidade, usa-se o Rolling with additional batches
- Para resolver 403 em um bucket do S3, é necessário criar uma bucket policy
- Um arquivo de configuração do Beanstalk que aplica configurações automaticamente é o ebsextensions
- Para controlar detalhes de deploy é necessário ter um arquivo appspec.yaml na pasta root
- Tamanho máximo da mensagem do SQS é 256Kb
- Para fazer o upload de Lambda functions e templates do CloudFormation para a AWS, são usados os comandos cloudformation package e cloudformation deploy
- Para mostrar x resultados por página no S3 usa-se --max-items e --starting-token
- Para fazer uma query com múltiplos atributos no DynamoDB, se usa um GSI
- SNS é pub-sub
- O tamanho máximo de um .env de uma Lambda é de 4Kb
- O Elastic Block Storage suporta encriptação in-flight e encriptação at rest usando o KMS
- A menor métrica possível no CloudWatch é de 1s
- Para expor um endpoint HTTPS no Beanstalk, é necessário criar um .ebsextensions para configurar o load balancer
- Cognito User Pools disponibiliza JWT por padrão
- Para ser mais eficiente ao recupera alguns atributos no DynamoDB, usa-se ProjectionExpression (também pode ser usado para retornar um subconjunto)
- Para melhorar a perfomance do CodeBuild, é possível adicionar as dependências no fonte durante o último estágio de build
- Quando os dados não aparecem na DLQ, pode ser um problema com o IAM
- !GetAtt é uma função intrínseca que retorna o valor de um atributo de um recurso no template
- !Ref retornar o valor especificado de um parâmetro ou recurso
- Para usar um valor exportado de outra stack no CloudFormation, usa-se a função !ImportValue
- SSE-C rejeitara uma conexão se a requisição não estiver usando HTTPS
- Sigv4 no API Gateway possui um header com credenciais do IAM
- Lambda Layers são runtimes de linguagens que não são suportadas nativamente pela AWS e também para externalizar dependências e reusa-lás
- Com a API PutMetricData é possível obter dados a cada 1s
- DynamoDB Streams geralmente são usados em contextos menores (event-driven) por exemplo
- Para diminuir o tempo para uma mensagem chegar ao cliente, pode-se reduzir o Visibility Timeout
- Um snapshot de um volume do RDS encriptado está sempre encriptado
- Quando há muitas dependências dentro de uma aplicação, é possível utilizar o S3 Caching para agilizar o processo do CodeBuild
- Para ter mais flexibilidade com controle de acesso, auditoria, remoção de keys, etc, usa-se o SSE-KMS
- CodeDeploy permite controle total dos passos do deploy usando blue green deployment
- Para garantir que as métricas cheguem ao CloudWatch, é possível usar Exponential Backoff
- Para agilizar o processo de build com o CodeBuild quando se tem muitas dependências, é possível utilizar o S3 caching