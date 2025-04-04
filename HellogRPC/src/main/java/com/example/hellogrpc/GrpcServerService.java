package com.example.hellogrpc;


import io.grpc.stub.StreamObserver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.example.hellogrpc.grpc.*;
@Service
public class GrpcServerService extends SimpleGrpc.SimpleImplBase {
    private static final Log log = LogFactory.getLog(GrpcServerService.class);

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {

        if (req.getName().startsWith("error")) {
            throw new IllegalArgumentException("Bad name: " + req.getName());
        }
        if (req.getName().startsWith("internal")) {
            throw new RuntimeException();
        }

        HelloReply reply = HelloReply.newBuilder()
                .setMessage("Hello ==> " + req.getName())
                .build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void streamHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        log.info("Streaming Hello " + req.getName());
        int count = 0;

        while (count < 10) {
            HelloReply reply = HelloReply.newBuilder()
                    .setMessage("Hello(" + count + ") ==> " + req.getName())
                    .build();

            responseObserver.onNext(reply);
            count++;

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                responseObserver.onError(e);
                return;
            }
        }
        responseObserver.onCompleted();
    }

    @Override
    public void addNumbers(AddRequest req, StreamObserver<AddReply> responseObserver) {
        log.info("Calculating sum: " + req.getNumber1() + " + " + req.getNumber2());

        int sum = req.getNumber1() + req.getNumber2();

        AddReply reply = AddReply.newBuilder()
                .setSum(sum)
                .build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}