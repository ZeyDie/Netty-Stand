package com.zeydie;

import com.zeydie.netty.client.NettyClient;
import com.zeydie.netty.server.NettyServer;
import org.jetbrains.annotations.NotNull;

public class Main {
    public static void main(@NotNull final String[] args) {
        switch (args[0]) {
            case "server":
                new NettyServer(args).run();
                break;
            case "client":
                new NettyClient(args).run();
                break;
        }
    }
}