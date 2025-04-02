package com.example.chatmessage2;


import com.example.chatmessage2.grpc.ChatMessage;
import com.example.chatmessage2.grpc.ChatMessageResponse;
import com.example.chatmessage2.grpc.ChatServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class ChatServiceImpl2 extends ChatServiceGrpc.ChatServiceImplBase {
    @Override
    public StreamObserver<ChatMessage> chat(StreamObserver<ChatMessageResponse> responseObserver) {
        return new StreamObserver<ChatMessage>() {
            @Override
            public void onNext(ChatMessage message) {
                System.out.println("Received from " + message.getSender() + ": " + message.getContent());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Server 1 disconnected from Server 2");
                responseObserver.onCompleted();
            }
        };
    }
}