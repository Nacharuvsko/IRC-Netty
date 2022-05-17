package xyz.winston.irc.client.connection.processor;

import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import xyz.winston.irc.client.Bootstrap;
import xyz.winston.irc.client.gui.GUIHandler;
import xyz.winston.irc.protocol.packet.S01PacketServerMessage;
import xyz.winston.irc.protocol.packet.S02PacketServerInfo;
import xyz.winston.irc.protocol.processor.IrcServerProcessor;
import xyz.winston.nettytransporter.protocol.packet.ChannelProcessorContext;
import xyz.winston.nettytransporter.protocol.packet.handshake.Handshake;
import xyz.winston.nettytransporter.protocol.packet.handshake.processor.HandshakeServerProcessor;

@Log4j2
public final class IrcPacketProcessor implements IrcServerProcessor, HandshakeServerProcessor {

    @Override
    public void process(Handshake.Response packet, ChannelProcessorContext ctx) {
        if (packet.getResult() == Handshake.Result.FAILED) {
            Bootstrap.INSTANCE.getFramer().getTestClientGui().getHandler()
                    .logMessage(GUIHandler.Level.SYSTEM, "Couldn't connect to target server: " + packet.getMessage());
            return;
        }

        Bootstrap.INSTANCE.getFramer().getTestClientGui().getHandler()
                .logMessage(GUIHandler.Level.SYSTEM, "Connection to server established!");
    }

    @Override
    public void process(@NotNull S01PacketServerMessage packet, @NotNull ChannelProcessorContext ctx) {
        Bootstrap.INSTANCE.getFramer().getTestClientGui().getHandler()
                .logMessage(GUIHandler.Level.ALL, packet.getMessage());
    }

    @Override
    public void process(@NotNull S02PacketServerInfo packet, @NotNull ChannelProcessorContext ctx) {
        Bootstrap.INSTANCE.getFramer().getTestClientGui().getHandler()
                .updateServerInformation(packet.getServerEpoch(), packet.getOnline());
    }
}
