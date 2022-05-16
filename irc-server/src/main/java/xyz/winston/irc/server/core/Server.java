package xyz.winston.irc.server.core;

import lombok.NonNull;
import xyz.winston.irc.protocol.packet.C01PacketClientMessage;
import xyz.winston.irc.server.processors.ChatMessagesProcessor;
import xyz.winston.nettytransporter.ConnectorServer;
import xyz.winston.nettytransporter.protocol.packet.PacketProtocol;

/**
 * @author winston
 */
public final class Server {

    /** Server instance */
    private final ConnectorServer connectorServer;

    public Server(final int port, final @NonNull String host, final @NonNull String token) {
        connectorServer = new ConnectorServer(host, port, token);
    }

    public void loadAndBind() {
        connectorServer.registerProcessor(new ChatMessagesProcessor());
        PacketProtocol.PLAY.both(30, C01PacketClientMessage.class, C01PacketClientMessage::new);
        try {
            connectorServer.openConnection();
        } catch (final @NonNull Exception e) {
            e.printStackTrace();
        }
    }
}
