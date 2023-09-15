package com.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.config.MyStompSessionHandler;

@SpringBootApplication
public class WebSocketsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSocketsApplication.class, args);

        // WebSocket client code
        WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());

        String serverUri = "ws://localhost:9090/gs-guide-websocket"; // Replace with your WebSocket server URL
        String message = "Hello, WebSocket Server!";

        try {
            StompSessionHandler sessionHandler = new MyStompSessionHandler();

            // Updated connection method
            StompSession session = stompClient.connect(serverUri, sessionHandler).get();

            MessageObject messageObject = new MessageObject();
            messageObject.setContent("Hello, WebSocket Server!");

            // Send the object as JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonMessage = objectMapper.writeValueAsString(messageObject);

            session.send("/app/send-message", jsonMessage);
            // Sleep for a while to allow the client to receive messages
            Thread.sleep(5000);

            // Close the WebSocket session
            session.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
