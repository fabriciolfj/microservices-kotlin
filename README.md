### Microservices Kotlin

Demonstrando a criação de microservices na linhaguem kotlin com spring boot.

- Customer: microservice comum.
- Product: microservice reativo.

###### docker swarm
- Para iniciar um docker swarm:
```
docker swarm init
```
- Criando um service:
```
docker service create --replicas 1 --name helloworld alpine ping google.com
docker service ls
```

