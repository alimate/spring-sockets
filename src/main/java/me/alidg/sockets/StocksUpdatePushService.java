package me.alidg.sockets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.function.Consumer;

import static me.alidg.sockets.StocksSocketHandler.sessions;

@Component
class StocksUpdatePushService {
    private static final int THREE_SECONDS = 3000;
    private static final Logger LOGGER = LoggerFactory.getLogger(StocksUpdatePushService.class);

    /**
     * Run every 3 seconds and send random stock updates to all open sessions
     */
    @Scheduled(fixedRate = THREE_SECONDS)
    public void pushStockUpdate() {
        LOGGER.info("Starting the push task");

        sessions().forEach(sendRandomStockUpdateMessage());

        LOGGER.info("Wrapping up the push task");
    }

    private Consumer<WebSocketSession> sendRandomStockUpdateMessage() {
        return session -> {
            try {
                LOGGER.info("About to send a message to {}", session.getId());
                session.sendMessage(new TextMessage(Stock.randomStockUpdate().toJson()));
                LOGGER.info("Successfully sent the message to {}", session.getId());
            } catch (IOException e) {
                LOGGER.error("Couldn't send the message", e);
            }
        };
    }
}