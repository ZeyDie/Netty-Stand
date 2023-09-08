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
import org.jetbrains.annotations.Nullable;

import java.io.*;

@Log
@ChannelHandler.Sharable
public class NettyPacketAdapter extends ChannelInboundHandlerAdapter {
    public @NotNull ChannelFuture write(
            @NotNull final ChannelHandlerContext channelHandlerContext,
            @NotNull final Packet packet,
            final boolean flush
    ) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try (final ObjectOutput objectOutput = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutput.writeObject(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        final int length = byteArrayOutputStream.size();
        final byte[] data = byteArrayOutputStream.toByteArray();

        final ByteBuf byteBuf = channelHandlerContext.alloc()
                .buffer(length)
                .writeInt(length)
                .writeBytes(data);

        return flush ? channelHandlerContext.writeAndFlush(byteBuf) : channelHandlerContext.write(byteBuf);
    }

    public @Nullable Packet read(@NotNull final ByteBuf byteBuf) {
        final int length = byteBuf.readInt();
        final byte[] data = new byte[length];

        for (int i = 0; i < length; i++)
            data[i] = byteBuf.readByte();

        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);

        try (final ObjectInput objectInput = new ObjectInputStream(byteArrayInputStream)) {
            final Object object = objectInput.readObject();

            if (object instanceof Packet)
                return (Packet) object;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void channelRead(
            @NotNull final ChannelHandlerContext channelHandlerContext,
            @NotNull final Object message
    ) {
        log.info("channelRead");

        try {
            final ByteBuf byteBuf = (ByteBuf) message;

            if (byteBuf.isReadable()) {
                final Packet packet = this.read(byteBuf);

                if (packet != null) {
                    log.info(packet.toString());
                    log.info("message " + new String(packet.getData()));
                } else log.info("packet is null");
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
