package com.zeydie.netty.api.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public final class Packet {
    private final int id;
    private byte[] data;

    public int getSize() {
        return 4 + 2 * this.data.length;
    }
}
