package xyz.winston.irc.client.connection.processor;

import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import xyz.winston.irc.client.Bootstrap;
import xyz.winston.irc.client.gui.GUIHandler;
import xyz.winston.irc.protocol.packet.C01PacketClientMessage;
import xyz.winston.irc.protocol.processor.IrcClientProcessor;
import xyz.winston.nettytransporter.protocol.packet.ChannelProcessorContext;
import xyz.winston.nettytransporter.protocol.packet.handshake.Handshake;
import xyz.winston.nettytransporter.protocol.packet.handshake.processor.HandshakeServerProcessor;

@Log4j2
public final class IrcPacketProcessor implements IrcClientProcessor, HandshakeServerProcessor {

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
    public void process(@NotNull C01PacketClientMessage packet, @NotNull ChannelProcessorContext ctx) {
        Bootstrap.INSTANCE.getFramer().getTestClientGui().getHandler()
                .logMessage(GUIHandler.Level.ALL, packet.getUserName() + ": " + packet.getMessageContent());
    }
}
