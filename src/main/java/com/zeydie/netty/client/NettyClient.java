package com.zeydie.netty.client;

import com.zeydie.netty.api.data.NettyChannelOption;
import com.zeydie.netty.client.handlers.NettyClientChannelInitializerHandler;
import com.zeydie.netty.common.NettyCommon;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;

@Log
public final class NettyClient extends NettyCommon {
    public NettyClient(@NotNull final String[] args) {
        super(args);
    }

    @Override
    public void run() {
        log.info("Starting client...");

        final EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            new Bootstrap()
                    .group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new NettyClientChannelInitializerHandler())
                    //.option(ChannelOption.SO_BACKLOG, NettyChannelOption.SO_BACKLOG)
                    .option(ChannelOption.SO_KEEPALIVE, NettyChannelOption.SO_KEEPALIVE)
                    .option(ChannelOption.TCP_NODELAY, NettyChannelOption.TCP_NODELAY)
                    .connect("localhost", NettyChannelOption.PORT)
                    .channel()
                    .closeFuture()
                    .sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
