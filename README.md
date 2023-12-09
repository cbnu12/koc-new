# 로컬에서 구동하기
## mysql(h2)
JPA ddl auto 기능을 활용한다.

## RabbitMQ
도커로 컨테이너를 띄워서 사용한다. <br/>
todo. exchange와 queue 생성하는 script를 만든다. <br/>

```shell
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 --restart=unless-stopped rabbitmq:management
```