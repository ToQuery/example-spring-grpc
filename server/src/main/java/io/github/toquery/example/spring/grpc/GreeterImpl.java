package io.github.toquery.example.spring.grpc;

import io.github.toquery.example.spring.grpc.helloworld.GreeterGrpc;
import io.github.toquery.example.spring.grpc.helloworld.HelloReply;
import io.github.toquery.example.spring.grpc.helloworld.HelloRequest;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@GrpcService
public class GreeterImpl extends GreeterGrpc.GreeterImplBase {

    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        HelloReply helloReply = HelloReply.newBuilder().setMessage(request.getName() + "Hello World From gRPC").build();
        responseObserver.onNext(helloReply);
        responseObserver.onCompleted();
    }
}
