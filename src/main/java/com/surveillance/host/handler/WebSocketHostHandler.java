package com.surveillance.host.handler;

import java.nio.ByteBuffer;

import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.WebSocketHandler;

import com.surveillance.host.service.WebSocketConnections;

import lombok.extern.slf4j.Slf4j;

@Slf4j // logger
public class WebSocketHostHandler implements WebSocketHandler {
    private WebSocketConnections sessions;

    public WebSocketHostHandler() {
        this.sessions = new WebSocketConnections();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Proxy Connected. Session: {}", session.getId());
        this.sessions.add(session);
        session.setBinaryMessageSizeLimit(1024 * 1024 * 5);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // String payload = message.getPayload().toString();
        // log.info(payload);
        for (WebSocketSession s : this.sessions.get()) {
            if (s.getId() == session.getId())
                continue;
            s.sendMessage(new BinaryMessage(message.getPayload().toString().getBytes()));
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("Exception: " + exception.getMessage(), session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.info("Connection closed on session: {} with status: {}", session.getId(), closeStatus.getReason());
        this.sessions.remove(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return true;
    }

}
