### Exemplo de utilização das API's

Para cadastrar um veículo, enviar um POST para: http://localhost:8080/vehicle/

Body da requisição:

```
{
	"name": "Palio",
	"make": "Fiat",
	"model": 2017,
	"manufactureDate": "1981-04-20",
	"cityConsumption": 5,
	"highwayConsumption": 5
}
```

Para realizar o cálculo de previsão de gastos, enviar uma requisição GET para: http://localhost:8080/vehicle/compute?price=5&city=10&highway=20


## Projeto desenvolvido com:

* [Spring Boot](https://spring.io/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
