package xyz.winston.irc.protocol.processor;

import org.jetbrains.annotations.NotNull;
import xyz.winston.irc.protocol.packet.C01PacketClientMessage;
import xyz.winston.nettytransporter.protocol.packet.ChannelProcessorContext;
import xyz.winston.nettytransporter.protocol.packet.PacketProcessor;

public interface IrcProcessor extends PacketProcessor {

    default void process(final @NotNull C01PacketClientMessage packet, final @NotNull ChannelProcessorContext ctx) {
        // override me
    }

}
