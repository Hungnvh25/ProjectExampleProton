package com.example.chatmessage1;

import com.example.chatmessage1.grpc.ChatMessage;
import com.example.chatmessage1.grpc.ChatMessageResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.example.chatmessage1.grpc.ChatServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class ChatServiceImpl extends ChatServiceGrpc.ChatServiceImplBase {
//    private final ManagedChannel channel;
//    private final ChatServiceGrpc.ChatServiceStub server2Stub;
//    private StreamObserver<ChatMessage> server2Observer;

//    public ChatServiceImpl() {
//        // Kết nối tới Server 2 (chạy trên port 9091)
//        this.channel = ManagedChannelBuilder.forAddress("localhost", 9091)
//                .usePlaintext()
//                .build();
//        this.server2Stub = ChatServiceGrpc.newStub(channel);
//        this.server2Observer = server2Stub.chat(new StreamObserver<ChatMessageResponse>() {
//
//            @Override
//            public void onNext(ChatMessageResponse response) {
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                System.err.println("Error from Server 2: " + t.getMessage());
//            }
//
//            @Override
//            public void onCompleted() {
//                System.out.println("Server 2 disconnected");
//            }
//        });
//    }
//
//    @Override
//    public StreamObserver<ChatMessage> chat(StreamObserver<ChatMessageResponse> responseObserver) {
//        return new StreamObserver<ChatMessage>() {
//            @Override
//            public void onNext(ChatMessage message) {
//                // Hiển thị tin nhắn trên terminal của Server 1
//                System.out.println("[Server 1] Received from " + message.getSender() + ": " + message.getContent());
//
//                // Gửi phản hồi về client
//                ChatMessageResponse response = ChatMessageResponse.newBuilder()
//                        .setReceivedMessage(message.getContent())
//                        .setServerResponse("Server 1 received your message!")
//                        .build();
//                responseObserver.onNext(response);
//
//                // Chuyển tiếp tin nhắn tới Server 2
//                server2Observer.onNext(message);
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                System.err.println("Error: " + t.getMessage());
//            }
//
//            @Override
//            public void onCompleted() {
//                System.out.println("Client disconnected from Server 1");
//                responseObserver.onCompleted();
//            }
//        };
//    }
}