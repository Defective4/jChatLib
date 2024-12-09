package io.github.defective4.minecraft.chatlib.nbt.tag;

import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;

public class ByteArrayTag extends Tag {
    public static final TagGenerator<ByteArrayTag> GENERATOR = in -> {
        byte[] array = new byte[in.readInt()];
        in.readFully(array);
        return new ByteArrayTag(array);
    };

    private final byte[] array;

    public ByteArrayTag(byte[] array) {
        this.array = array;
    }

    public byte[] getArray() {
        return array;
    }

}
