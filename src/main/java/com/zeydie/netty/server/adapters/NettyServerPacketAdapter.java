package com.zeydie.netty.server.adapters;

import com.zeydie.netty.api.data.Packet;
import com.zeydie.netty.common.adapters.NettyPacketAdapter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;

@Log
public final class NettyServerPacketAdapter extends NettyPacketAdapter {
    @Override
    public void channelActive(@NotNull final ChannelHandlerContext channelHandlerContext) {
        log.info("channelActive");

        final Packet packet = new Packet(0);

        packet.setData("Hello!".getBytes(StandardCharsets.UTF_8));

        final ByteBuf byteBuf = channelHandlerContext.alloc().buffer(packet.getSize());

        byteBuf.writeInt(packet.getId());
        byteBuf.writeInt(packet.getData().length);
        byteBuf.writeBytes(packet.getData());

        channelHandlerContext.writeAndFlush(byteBuf)
                .addListener(ChannelFutureListener.CLOSE);
    }
}
