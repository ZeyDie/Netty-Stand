package com.zeydie.netty.server.handlers;

import com.zeydie.netty.server.adapters.NettyServerPacketAdapter;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;

@Log
@ChannelHandler.Sharable
public final class NettyServerChannelInitializerHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(@NotNull final SocketChannel socketChannel) throws Exception {
        log.info("initChannel " + socketChannel.remoteAddress());

        socketChannel.pipeline().addLast(new NettyServerPacketAdapter());
    }
}
