package com.example.chatmessage1;

import com.example.chatmessage1.grpc.ChatMessage;
import com.example.chatmessage1.grpc.ChatMessageResponse;
import com.example.chatmessage1.grpc.ChatServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
@SpringBootApplication
public class ChatClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        ChatServiceGrpc.ChatServiceStub stub = ChatServiceGrpc.newStub(channel);
        StreamObserver<ChatMessage> requestObserver = stub.chat(new StreamObserver<ChatMessageResponse>() {
            @Override
            public void onNext(ChatMessageResponse response) {
                System.out.println("Server: " + response.getServerResponse());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Server completed");
            }
        });

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String sender = scanner.nextLine();

        while (true) {
            System.out.print("Enter message (or 'exit' to quit): ");
            String content = scanner.nextLine();

            if ("exit".equalsIgnoreCase(content)) {
                requestObserver.onCompleted();
                break;
            }

            ChatMessage message = ChatMessage.newBuilder()
                    .setSender(sender)
                    .setContent(content)
                    .build();
            requestObserver.onNext(message);
        }

        channel.shutdown();
    }
}