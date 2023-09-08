package com.zeydie.netty.api.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public final class Packet implements Serializable {
    private final int id;
    private byte[] data;

    public int getSize() {
        return 4 + 2 * this.data.length;
    }
}
