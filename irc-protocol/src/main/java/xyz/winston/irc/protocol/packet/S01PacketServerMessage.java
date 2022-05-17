package xyz.winston.irc.protocol.packet;

import io.netty.buffer.ByteBuf;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import xyz.winston.irc.protocol.processor.IrcProcessor;
import xyz.winston.irc.protocol.processor.IrcServerProcessor;
import xyz.winston.nettytransporter.protocol.packet.ChannelProcessorContext;
import xyz.winston.nettytransporter.protocol.packet.Packet;
import xyz.winston.nettytransporter.protocol.packet.PacketProcessor;
import xyz.winston.nettytransporter.protocol.packet.PacketUtils;

/** Packet ID: 31 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public final class S01PacketServerMessage extends Packet<IrcProcessor> {

    private String message;

    @Override
    public boolean isProcessor(PacketProcessor packetProcessor) {
        return packetProcessor instanceof IrcServerProcessor;
    }

    @Override
    protected void read0(@NotNull ByteBuf byteBuf) {
        message = PacketUtils.readString(byteBuf);
    }

    @Override
    protected void write0(@NotNull ByteBuf byteBuf) {
        PacketUtils.writeString(byteBuf, message);
    }

    @Override
    public void process(@NotNull IrcProcessor processor, @NotNull ChannelProcessorContext channelProcessorContext) {
        processor.process(this, channelProcessorContext);
    }
}
