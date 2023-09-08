package com.zeydie.netty.server.adapters;

import com.zeydie.netty.api.data.Packet;
import com.zeydie.netty.common.adapters.NettyPacketAdapter;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;

@Log
@ChannelHandler.Sharable
public final class NettyServerPacketAdapter extends NettyPacketAdapter {
    @Override
    public void channelActive(@NotNull final ChannelHandlerContext channelHandlerContext) {
        log.info("channelActive");

        final Packet packet = new Packet(0);

        packet.setData("Hello!".getBytes(StandardCharsets.UTF_8));

        super.write(channelHandlerContext, packet, true).addListener(ChannelFutureListener.CLOSE);
    }
}
