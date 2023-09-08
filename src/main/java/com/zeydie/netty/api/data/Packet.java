package com.zeydie.netty.api.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public final class Packet implements Serializable {
    private final int id;
    private byte[] data;

    public void readPacketData(@NotNull final DataInput dataInput) throws IOException {
        final short length = dataInput.readShort();

        this.data = new byte[length];

        dataInput.readFully(this.data);
    }

    public void writePacketData(@NotNull final DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(this.id);
        dataOutput.writeShort(this.data.length);
        dataOutput.write(this.data);
    }
}
