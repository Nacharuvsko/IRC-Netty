package xyz.winston.irc.server.processors;

import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import xyz.winston.irc.protocol.packet.C01PacketClientMessage;
import xyz.winston.irc.protocol.processor.IrcServerProcessor;
import xyz.winston.nettytransporter.connection.client.ClientManager;
import xyz.winston.nettytransporter.protocol.packet.ChannelProcessorContext;

/**
 * @author winston
 */
@Log4j2
public final class ChatMessagesProcessor implements IrcServerProcessor {

    @Override
    public void process(@NotNull C01PacketClientMessage packet, @NotNull ChannelProcessorContext ctx) {
        ClientManager.IMP.sendToClients(packet); // do the echo thingy :3
        log.info(packet.getUserName() + ": " + packet.getMessageContent());
    }
}
