package com.zeydie.netty.common;

import com.zeydie.netty.api.INetty;
import com.zeydie.netty.api.data.NettyChannelOption;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;

@Log
@RequiredArgsConstructor
public abstract class NettyCommon implements INetty {
    public NettyCommon(@NotNull final String[] args) {
        NettyChannelOption.init(args);
    }
}
