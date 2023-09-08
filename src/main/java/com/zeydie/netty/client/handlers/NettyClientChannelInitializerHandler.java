package com.zeydie.netty.client.handlers;

import com.zeydie.netty.common.adapters.NettyPacketAdapter;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;

@Log
@ChannelHandler.Sharable
public final class NettyClientChannelInitializerHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(@NotNull final SocketChannel socketChannel) throws Exception {
        log.info("initChannel " + socketChannel.remoteAddress());

        socketChannel.pipeline().addLast(new NettyPacketAdapter());
    }
}
