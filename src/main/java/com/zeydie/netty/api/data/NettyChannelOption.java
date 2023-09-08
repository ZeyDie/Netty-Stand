package com.zeydie.netty.api.data;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.WriteBufferWaterMark;
import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;
import java.net.NetworkInterface;

public final class NettyChannelOption {
    public static int PORT;

    public static ByteBufAllocator ALLOCATOR;
    public static RecvByteBufAllocator RCVBUF_ALLOCATOR;
    public static MessageSizeEstimator MESSAGE_SIZE_ESTIMATOR;
    public static int CONNECT_TIMEOUT_MILLIS;
    @Deprecated
    public static int MAX_MESSAGES_PER_READ;
    public static int MAX_MESSAGES_PER_WRITE;
    public static int WRITE_SPIN_COUNT;
    @Deprecated
    public static int WRITE_BUFFER_HIGH_WATER_MARK;
    @Deprecated
    public static int WRITE_BUFFER_LOW_WATER_MARK;
    public static WriteBufferWaterMark WRITE_BUFFER_WATER_MARK;
    public static boolean ALLOW_HALF_CLOSURE;
    public static boolean AUTO_READ;
    public static boolean AUTO_CLOSE;
    public static boolean SO_BROADCAST;
    public static boolean SO_KEEPALIVE;
    public static int SO_SNDBUF;
    public static int SO_RCVBUF;
    public static boolean SO_REUSEADDR;
    public static int SO_LINGER;
    public static int SO_BACKLOG;
    public static int SO_TIMEOUT;
    public static int IP_TOS;
    public static InetAddress IP_MULTICAST_ADDR;
    public static NetworkInterface IP_MULTICAST_IF;
    public static int IP_MULTICAST_TTL;
    public static boolean IP_MULTICAST_LOOP_DISABLED;
    public static boolean TCP_NODELAY;
    public static boolean TCP_FASTOPEN_CONNECT;
    public static int TCP_FASTOPEN;
    @Deprecated
    public static boolean DATAGRAM_CHANNEL_ACTIVE_ON_REGISTRATION;
    public static boolean SINGLE_EVENTEXECUTOR_PER_GROUP;

    public static void init(@NotNull final String[] args) {
        for (int i = 1; i < args.length; i++) {
            final String[] key_value = args[i].split("=");

            if (key_value.length <= 1) continue;

            final String key = key_value[0];
            final String value = key_value[1];

            switch (key) {
                case "PORT":
                    PORT = Integer.parseInt(value);
                    break;
                case "CONNECT_TIMEOUT_MILLIS":
                    CONNECT_TIMEOUT_MILLIS = Integer.parseInt(value);
                    break;
                case "MAX_MESSAGES_PER_READ":
                    MAX_MESSAGES_PER_READ = Integer.parseInt(value);
                    break;
                case "MAX_MESSAGES_PER_WRITE":
                    MAX_MESSAGES_PER_WRITE = Integer.parseInt(value);
                    break;
                case "WRITE_SPIN_COUNT":
                    WRITE_SPIN_COUNT = Integer.parseInt(value);
                    break;
                case "WRITE_BUFFER_HIGH_WATER_MARK":
                    WRITE_BUFFER_HIGH_WATER_MARK = Integer.parseInt(value);
                    break;
                case "WRITE_BUFFER_LOW_WATER_MARK":
                    WRITE_BUFFER_LOW_WATER_MARK = Integer.parseInt(value);
                    break;
                case "ALLOW_HALF_CLOSURE":
                    ALLOW_HALF_CLOSURE = Boolean.parseBoolean(value);
                    break;
                case "AUTO_READ":
                    AUTO_READ = Boolean.parseBoolean(value);
                    break;
                case "SO_BROADCAST":
                    SO_BROADCAST = Boolean.parseBoolean(value);
                    break;
                case "SO_KEEPALIVE":
                    SO_KEEPALIVE = Boolean.parseBoolean(value);
                    break;
                case "SO_SNDBUF":
                    SO_SNDBUF = Integer.parseInt(value);
                    break;
                case "SO_RCVBUF":
                    SO_RCVBUF = Integer.parseInt(value);
                    break;
                case "SO_REUSEADDR":
                    SO_REUSEADDR = Boolean.parseBoolean(value);
                    break;
                case "SO_LINGER":
                    SO_LINGER = Integer.parseInt(value);
                    break;
                case "SO_BACKLOG":
                    SO_BACKLOG = Integer.parseInt(value);
                    break;
                case "SO_TIMEOUT":
                    SO_TIMEOUT = Integer.parseInt(value);
                    break;
                case "IP_TOS":
                    IP_TOS = Integer.parseInt(value);
                    break;
                case "IP_MULTICAST_TTL":
                    IP_MULTICAST_TTL = Integer.parseInt(value);
                    break;
                case "IP_MULTICAST_LOOP_DISABLED":
                    IP_MULTICAST_LOOP_DISABLED = Boolean.parseBoolean(value);
                    break;
                case "TCP_NODELAY":
                    TCP_NODELAY = Boolean.parseBoolean(value);
                    break;
                case "TCP_FASTOPEN_CONNECT":
                    TCP_FASTOPEN_CONNECT = Boolean.parseBoolean(value);
                    break;
                case "TCP_FASTOPEN":
                    TCP_FASTOPEN = Integer.parseInt(value);
                    break;
                case "DATAGRAM_CHANNEL_ACTIVE_ON_REGISTRATION":
                    DATAGRAM_CHANNEL_ACTIVE_ON_REGISTRATION = Boolean.parseBoolean(value);
                    break;
                case "SINGLE_EVENTEXECUTOR_PER_GROUP":
                    SINGLE_EVENTEXECUTOR_PER_GROUP = Boolean.parseBoolean(value);
                    break;
            }
        }
    }
}
