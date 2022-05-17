package xyz.winston.irc.protocol.packet;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import xyz.winston.irc.protocol.processor.IrcProcessor;
import xyz.winston.irc.protocol.processor.IrcServerProcessor;
import xyz.winston.nettytransporter.protocol.packet.ChannelProcessorContext;
import xyz.winston.nettytransporter.protocol.packet.Packet;
import xyz.winston.nettytransporter.protocol.packet.PacketProcessor;

/** Packet ID: 32 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public final class S02PacketServerInfo extends Packet<IrcProcessor> {

    private long serverEpoch;
    private int online;

    @Override
    public boolean isProcessor(PacketProcessor packetProcessor) {
        return packetProcessor instanceof IrcServerProcessor;
    }

    @Override
    protected void read0(@NotNull ByteBuf byteBuf) {
        serverEpoch = byteBuf.readLong();
        online      = byteBuf.readInt();
    }

    @Override
    protected void write0(@NotNull ByteBuf byteBuf) {
        byteBuf.writeLong(serverEpoch);
        byteBuf.writeInt(online);
    }

    @Override
    public void process(@NotNull IrcProcessor processor, @NotNull ChannelProcessorContext channelProcessorContext) {
        processor.process(this, channelProcessorContext);
    }
}
