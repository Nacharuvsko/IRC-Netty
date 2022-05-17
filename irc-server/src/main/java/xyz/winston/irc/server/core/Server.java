package xyz.winston.irc.server.core;

import lombok.Getter;
import lombok.NonNull;
import xyz.winston.irc.protocol.packet.C01PacketClientMessage;
import xyz.winston.irc.protocol.packet.S01PacketServerMessage;
import xyz.winston.irc.protocol.packet.S02PacketServerInfo;
import xyz.winston.irc.server.misc.ServerPacketBroadcaster;
import xyz.winston.irc.server.processors.ChatMessagesProcessor;
import xyz.winston.nettytransporter.ConnectorServer;
import xyz.winston.nettytransporter.protocol.packet.PacketProtocol;

/**
 * @author winston
 */
@Getter
public final class Server {

    /** Server instance */
    private final ConnectorServer connectorServer;

    // -----------------------------------------------------------------

    private final ServerPacketBroadcaster broadcaster;

    // -----------------------------------------------------------------

    public Server(final int port, final @NonNull String host, final @NonNull String token) {
        connectorServer = new ConnectorServer(host, port, token);
        broadcaster = new ServerPacketBroadcaster(this);
    }

    public void loadAndBind() {
        connectorServer.registerProcessor(new ChatMessagesProcessor());
        PacketProtocol.PLAY.toServer(30, C01PacketClientMessage.class, C01PacketClientMessage::new);
        PacketProtocol.PLAY.toClient(31, S01PacketServerMessage.class, S01PacketServerMessage::new);
        PacketProtocol.PLAY.toClient(32, S02PacketServerInfo.class, S02PacketServerInfo::new);

        try {
            connectorServer.openConnection();
        } catch (final @NonNull Exception e) {
            e.printStackTrace();
            return;
        }

        broadcaster.initialize();
    }
}
