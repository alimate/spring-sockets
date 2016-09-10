package me.alidg.sockets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class StocksSocketHandler extends AbstractWebSocketHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(StocksSocketHandler.class);
    private static final ConcurrentMap<String, WebSocketSession> SESSIONS = new ConcurrentHashMap<>();

    /**
     * After a successful connection establishment, we add the current session to the list of
     * all open sessions.
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        LOGGER.info("Successful connection for {}", session.getId());
        SESSIONS.putIfAbsent(session.getId(), session);
    }

    /**
     * After disconnection, the session would be removed from the list of open sessions.
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        LOGGER.info("{} is just disconnected", session.getId());
        SESSIONS.remove(session.getId());
    }

    /**
     * Return a read-only list of all open sessions
     */
    static Collection<WebSocketSession> sessions() {
        return Collections.unmodifiableCollection(SESSIONS.values());
    }
}