package xyz.winston.irc.protocol.processor;

import org.jetbrains.annotations.NotNull;
import xyz.winston.irc.protocol.packet.C01PacketClientMessage;
import xyz.winston.irc.protocol.packet.S01PacketServerMessage;
import xyz.winston.irc.protocol.packet.S02PacketServerInfo;
import xyz.winston.nettytransporter.protocol.packet.ChannelProcessorContext;
import xyz.winston.nettytransporter.protocol.packet.PacketProcessor;

public interface IrcProcessor extends PacketProcessor {

    default void process(final @NotNull C01PacketClientMessage packet, final @NotNull ChannelProcessorContext ctx) {
        // override me
    }

    default void process(final @NotNull S01PacketServerMessage packet, final @NotNull ChannelProcessorContext ctx) {
        // override me
    }

    default void process(final @NotNull S02PacketServerInfo packet, final @NotNull ChannelProcessorContext ctx) {
        // override me
    }


}
