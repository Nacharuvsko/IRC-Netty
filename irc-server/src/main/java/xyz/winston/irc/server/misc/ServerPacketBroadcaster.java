package xyz.winston.irc.server.misc;

import lombok.NonNull;
import xyz.winston.irc.protocol.packet.S02PacketServerInfo;
import xyz.winston.irc.server.core.Server;
import xyz.winston.nettytransporter.connection.client.ClientManager;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author winston
 */
public final class ServerPacketBroadcaster {

    private final Server server;

    public ServerPacketBroadcaster(final @NonNull Server server) {
        this.server = server;
    }

    public void initialize() {
        server.getConnectorServer().getScheduler().scheduleAtFixedRate(
                new ServerInfoRunnable(),
                1,
                1,
                TimeUnit.SECONDS
        );
    }

    private static final class ServerInfoRunnable implements Runnable {

        @Override public void run() {
            ClientManager.IMP.sendToClients(new S02PacketServerInfo(
                    System.currentTimeMillis(),
                    ClientManager.IMP.getClientsCount()
            ));
        }
    }

}
