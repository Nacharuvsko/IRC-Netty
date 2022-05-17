package xyz.winston.irc.client.connection;

import lombok.Getter;
import lombok.NonNull;
import org.graalvm.collections.Pair;
import org.jetbrains.annotations.NotNull;
import xyz.winston.irc.client.Bootstrap;
import xyz.winston.irc.client.connection.processor.IrcPacketProcessor;
import xyz.winston.irc.client.connection.type.ConnectionState;
import xyz.winston.irc.client.gui.GUIHandler;
import xyz.winston.irc.client.gui.type.PasswordContainer;
import xyz.winston.irc.protocol.packet.C01PacketClientMessage;
import xyz.winston.irc.protocol.packet.S01PacketServerMessage;
import xyz.winston.irc.protocol.packet.S02PacketServerInfo;
import xyz.winston.nettytransporter.ConnectorClient;
import xyz.winston.nettytransporter.connection.Connection;
import xyz.winston.nettytransporter.protocol.packet.PacketProtocol;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

@Getter
public final class ConnectionManager {

    private final Collection<ConnectorClient> junk = new ArrayList<>();

    // -----------------------------------------------------------
    public static ConnectionManager IMP = new ConnectionManager();
    // -----------------------------------------------------------

    private ConnectionState state;
    private ConnectorClient connectorClient;

    // -----------------------------------------------------------

    public boolean isConnected() {
        return connectorClient != null && connectorClient.getConnection().isConnected();
    }

    public void initialize() {
        state = ConnectionState.IDLE;
        PacketProtocol.PLAY.toServer(30, C01PacketClientMessage.class, C01PacketClientMessage::new);
        PacketProtocol.PLAY.toClient(31, S01PacketServerMessage.class, S01PacketServerMessage::new);
        PacketProtocol.PLAY.toClient(32, S02PacketServerInfo.class, S02PacketServerInfo::new);
    }

    public @NotNull CompletableFuture<Pair<Boolean, String>> connect(
            final @NonNull String address,
            final int port,
            final @NonNull String userName,
            final @NonNull PasswordContainer container
    ) {
        return CompletableFuture.supplyAsync(() -> {
            if (isConnected()) dropConnection();
            connectorClient = new ConnectorClient(address, port, userName,
                    container.isUsing()
                            ? container.getToken()
                            : "zal00pa"
            );
            connectorClient.getClientConfiguration().setAutoReconnect(false);
            connectorClient.getClientConfiguration().setDisconnectAction(() -> {
                Bootstrap.INSTANCE.getFramer().getTestClientGui().getHandler().handleDisconnectButton();
                Bootstrap.INSTANCE.getFramer().getTestClientGui().getHandler().logMessage(
                        GUIHandler.Level.SYSTEM,
                        "Lost connection to the server!"
                );
                dropConnection();
            });

            junk.add(connectorClient);

            try {
                connectorClient.openConnection();
                Connection.registerProcessor(new IrcPacketProcessor());
                junk.remove(connectorClient); // eshe kostili :3
                state = ConnectionState.CONNECTED;
                return Pair.create(true, null);
            } catch (Exception e) {
                e.printStackTrace();
                state = ConnectionState.DISCONNECTED;
                return Pair.create(false, e.getMessage());
            }
        });
    }

    public void dropConnection() {
        connectorClient.getConnection().closeConnection();
        nuke();
    }

    /**
     * govnoooooo mmmMmMM))))
     */
    private void nuke() {
        junk.forEach(c -> c.getConnection().closeConnection());
        junk.clear(); // flushing le shit :3
    }
}
