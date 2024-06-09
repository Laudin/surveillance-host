package com.surveillance.host.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.WebSocketSession;

import lombok.extern.slf4j.Slf4j;

@Slf4j // logger
public class WebSocketConnections {
    private Set<WebSocketSession> sessions;

    public WebSocketConnections() {
        this.sessions = new HashSet<WebSocketSession>(Arrays.asList());
    }

    public void add(WebSocketSession session) {
        log.info("Adding session: {}", session.getId());
        sessions.add(session);
    }

    public void remove(WebSocketSession session) {
        log.info("Removing session: {}", session.getId());
        sessions.remove(session);
    }

    public Set<WebSocketSession> get() {
        return sessions;
    }
}
