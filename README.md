### Microservices Kotlin

Demonstrando a criação de microservices na linhaguem kotlin com spring boot.

- Customer: microservice comum.
- Product: microservice reativo.

###### docker swarm
- Para iniciar um docker swarm:
```
docker swarm init
```
- Comandos uteis:
```
docker service create --replicas 1 --name helloworld alpine ping google.com
docker service ls
docker service rm helloworld
```
###### jconsole
- jconsole: monitoramento nativo da jdk

###### openshift
- Site para cadastro http://manage.openshift.com/
- Utilizando linha de comando:
```
./oc login https://api.starter-ca-central-1.openshift.com Authentication required for https://api.starter-ca-central-1.openshift.com:443 (openshift)
```
