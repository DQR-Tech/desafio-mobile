# CoinConverter
![Preview-Screens](https://github.com/Keader/desafio-mobile/blob/main/Screenshot/Print.png)

[![Android Workflow CI](https://github.com/Keader/desafio-mobile/actions/workflows/publication.yml/badge.svg)](https://github.com/Keader/desafio-mobile/actions/workflows/publication.yml)

### Qual a função do aplicativo?
Converter moedas baseado nos dados fornecidos pelo site [CurrencyLayer](https://currencylayer.com/).  
Além de listar as moedas, obtendo informações sobre os códigos e seus nomes.

### Decisões de Projeto
Para confecção deste aplicativo, foi decidido que a fonte da verdade (source of truth) seria o banco de dados local.  
Todos os dados são pegos da API e salvos no banco de dados, para só então serem utilizados pelo aplicativo.  

### Sobre o App
Este aplicativo utiliza a arquitetura MVVM (Model-View-View-Model), além de bibliotecas do Android Jetpack, Material Design.  
Dentre as bibliotecas pode-se destacar:
- [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started) para navegação entre os fragmentos.
- [Retrofit](https://square.github.io/retrofit/) para chamadas a API do CurrencyLayer.
- [OkHttp](https://square.github.io/okhttp/) utilizado como cliente HTTP do Retrofit.
- [Gson](https://github.com/google/gson) para conversão dos Json retornados pela API.
- [Hilt](https://dagger.dev/hilt/) para injeção de dependências.
- [Timber](https://github.com/JakeWharton/timber) para logs do app.
- [Room](https://developer.android.com/jetpack/androidx/releases/room) banco de dados local do aplicativo.
- [Datastore](https://developer.android.com/topic/libraries/architecture/datastore) como acesso rápido a user settings (substituindo o SharedPreferences).
- [Truth](https://truth.dev/) para realizar asserts nos testes, mais legíveis ao ser humano.

Para o CI, foi utilizado o Github Workflow (Actions), sua documentação está disponível [aqui](https://github.com/features/actions).
