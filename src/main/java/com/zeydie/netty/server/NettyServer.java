package com.zeydie.netty.server;

import com.zeydie.netty.api.data.NettyChannelOption;
import com.zeydie.netty.common.NettyCommon;
import com.zeydie.netty.server.handlers.NettyServerChannelInitializerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;

@Log
public final class NettyServer extends NettyCommon {
    public NettyServer(@NotNull final String[] args) {
        super(args);
    }

    @Override
    public void run() {
        log.info("Starting server...");

        final EventLoopGroup bossGroup = new NioEventLoopGroup();
        final EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            log.info("Server started on " + NettyChannelOption.PORT);

            new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new NettyServerChannelInitializerHandler())
                    .option(ChannelOption.SO_BACKLOG, NettyChannelOption.SO_BACKLOG)
                    .childOption(ChannelOption.SO_KEEPALIVE, NettyChannelOption.SO_KEEPALIVE)
                    .childOption(ChannelOption.TCP_NODELAY, NettyChannelOption.TCP_NODELAY)
                    .bind(NettyChannelOption.PORT)
                    .channel()
                    .closeFuture()
                    .sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}

