package io.github.toquery.example.spring.grpc;

import io.github.toquery.example.spring.grpc.helloworld.GreeterGrpc;
import io.github.toquery.example.spring.grpc.helloworld.HelloReply;
import io.github.toquery.example.spring.grpc.helloworld.HelloRequest;
import io.grpc.ManagedChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLException;

@Component
public class HelloWorldClient {
    @Autowired
    private GrpcClientManager grpcClientManager;

    public String sayHello() throws SSLException {
        ManagedChannel channel = grpcClientManager.getChannel();
        HelloRequest nameRequestOrBuilder = HelloRequest.newBuilder().setName("Geek").build();
        GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);
        HelloReply echoResponse = stub.sayHello(nameRequestOrBuilder);
        return echoResponse.getMessage();
    }
}

