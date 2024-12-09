package io.github.defective4.minecraft.chatlib.nbt.tag;

import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;

public class ByteTag extends Tag {
    public static final TagGenerator<ByteTag> GENERATOR = in -> new ByteTag(in.readByte());

    private final byte value;

    public ByteTag(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}
