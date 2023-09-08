package com.zeydie.netty.common.adapters;

import com.zeydie.netty.api.data.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;

import java.io.*;

@Log
@ChannelHandler.Sharable
public class NettyPacketAdapter extends ChannelInboundHandlerAdapter {
    public @NotNull ChannelFuture write(
            @NotNull final ChannelHandlerContext channelHandlerContext,
            @NotNull final Packet packet,
            final boolean flush
    ) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutput dataOutput = new DataOutputStream(byteArrayOutputStream);

        packet.writePacketData(dataOutput);

        final int length = byteArrayOutputStream.size();
        final byte[] data = byteArrayOutputStream.toByteArray();

        final ByteBuf byteBuf = channelHandlerContext.alloc()
                .buffer(length)
                .writeInt(length)
                .writeBytes(data);

        return flush ? channelHandlerContext.writeAndFlush(byteBuf) : channelHandlerContext.write(byteBuf);
    }

    public @NotNull Packet read(@NotNull final ByteBuf byteBuf) throws IOException {
        final int length = byteBuf.readInt();
        final byte[] data = new byte[length];

        for (int i = 0; i < length; i++)
            data[i] = byteBuf.readByte();

        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        final DataInput dataInput = new DataInputStream(byteArrayInputStream);
        final Packet packet = new Packet(dataInput.readInt());

        packet.readPacketData(dataInput);

        return packet;
    }

    @Override
    public void channelRead(
            @NotNull final ChannelHandlerContext channelHandlerContext,
            @NotNull final Object message
    ) throws IOException {
        log.info("channelRead");

        try {
            final ByteBuf byteBuf = (ByteBuf) message;

            if (byteBuf.isReadable()) {
                final Packet packet = this.read(byteBuf);

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
