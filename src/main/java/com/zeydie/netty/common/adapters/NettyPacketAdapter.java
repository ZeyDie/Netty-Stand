package com.zeydie.netty.common.adapters;

import com.zeydie.netty.api.data.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;

@Log
@ChannelHandler.Sharable
public class NettyPacketAdapter extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(
            @NotNull final ChannelHandlerContext channelHandlerContext,
            @NotNull final Object message
    ) {
        log.info("channelRead");

        try {
            final ByteBuf byteBuf = (ByteBuf) message;

            if (byteBuf.isReadable()) {
                final int id = byteBuf.readInt();
                final int length = byteBuf.readInt();
                final byte[] data = new byte[length];

                for (int i = 0; i < length; i++)
                    data[i] = byteBuf.readByte();

                final Packet packet = new Packet(id, data);

                log.info(packet.toString());
                log.info("message " + new String(packet.getData()));
            }
        } finally {
            ReferenceCountUtil.release(message);
        }
    }

    @Override
    public void exceptionCaught(
            @NotNull final ChannelHandlerContext channelHandlerContext,
            @NotNull final Throwable cause
    ) {
        cause.printStackTrace();
        channelHandlerContext.close();
    }
}
