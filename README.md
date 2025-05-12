# 🌍 Jornada Milhas API
API REST desenvolvida com o objetivo de fornecer dados sobre destinos de viagem e depoimentos de viajantes. A aplicação está preparada para integração com o frontend, seguindo o design proposto no Figma oficial do projeto.

### ✈️ Sobre o Projeto
O Jornada Milhas é uma aplicação voltada para inspirar viagens, apresentando ao usuário uma seleção de destinos com descrições e imagens, além de permitir o registro e consulta de depoimentos de outros viajantes.

A API foi construída com Java e Spring Boot, e está publicada na plataforma [Railway](https://railway.app).

### 🎨 Layout
O design do frontend pode ser visualizado no Figma:

👉 [Protótipo no Figma](https://www.figma.com/proto/1qD4hmpnvxoeHRC1cbWKgR/Challenge-Escola-de-Programação?type=design&node-id=4-6408&scaling=min-zoom&page-id=0%3A1)

### ⚙️ Tecnologias Utilizadas
- Java 17
- Spring Boot 3
- Spring Web
- Spring Validation
- Lombok
- Maven
- Swagger (OpenAPI)

### 🚀 Como Executar Localmente

#### Pré-requisitos
- Java 17 instalado
- Maven instalado

#### Passos para rodar a aplicação

```bash
# Clone o repositório
git clone <https://github.com/dayvidj/jornada-milhas-api.git>

# Acesse o diretório
cd jornadamilhas

# Execute o projeto
./mvnw spring-boot:run
```

A API estará disponível em: [http://localhost:8080](http://localhost:8080)

### 🌐 Ambiente de Produção
A aplicação está disponível em produção via Railway:

🔗 https://jornada-milhas-api-production-54b9.up.railway.app/

### 📑 Documentação da API (Swagger)
A documentação interativa da API está disponível via Swagger:

- **Desenvolvimento**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **Produção**: https://jornada-milhas-api-production-54b9.up.railway.app/swagger-ui/index.html

### 📦 Exemplos de Requisições

#### 📜 Listar depoimentos 
GET /depoimentos

Response body: 200 OK
```http
{
  "id": 1,
  "foto": "imagem.jpg",
  "texto": "Coloque aqui o seu depoimento",
  "nome": "Exemplo Nome"
},
{
  "id": 2,
  "foto": "foto.jpg",
  "texto": "Exemplo de depoimento",
  "nome": "Exemplo Nome"
}
```

#### ➕ Adicionar depoimento
POST /depoimentos

Request Body:
```http
{
  "foto": "imagem.jpg",
  "texto": "Excelente serviço! Fiquei muito satisfeito com a rapidez e qualidade.",
  "nome": "Ana Silva"
}
```

Response body: 201 CREATED
```http
{
  "id": 1,
  "foto": "imagem.jpg",
  "texto": "Excelente serviço! Fiquei muito satisfeito com a rapidez e qualidade.",
  "nome": "Ana Silva"
}
```

