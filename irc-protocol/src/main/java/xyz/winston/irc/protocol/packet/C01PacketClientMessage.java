package xyz.winston.irc.protocol.packet;

import io.netty.buffer.ByteBuf;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import xyz.winston.irc.protocol.processor.IrcProcessor;
import xyz.winston.nettytransporter.protocol.packet.ChannelProcessorContext;
import xyz.winston.nettytransporter.protocol.packet.Packet;
import xyz.winston.nettytransporter.protocol.packet.PacketProcessor;
import xyz.winston.nettytransporter.protocol.packet.PacketUtils;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public final class C01PacketClientMessage extends Packet<IrcProcessor> {

    private String userName;
    private String messageContent;

    @Override
    public boolean isProcessor(PacketProcessor packetProcessor) {
        return packetProcessor instanceof IrcProcessor;
    }

    @Override
    protected void read0(@NotNull ByteBuf byteBuf) {
        userName = PacketUtils.readString(byteBuf);
        messageContent = PacketUtils.readString(byteBuf);
    }

    @Override
    protected void write0(@NotNull ByteBuf byteBuf) {
        PacketUtils.writeString(byteBuf, userName);
        PacketUtils.writeString(byteBuf, messageContent);
    }

    @Override
    public void process(@NotNull IrcProcessor processor, @NotNull ChannelProcessorContext channelProcessorContext) {
        processor.process(this, channelProcessorContext);
    }
}
