package io.github.defective4.minecraft.chatlib.nbt.tag;

import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;
import io.github.defective4.minecraft.chatlib.nbt.TagSerializer;

public class ByteTag extends NumberTag {
    public static final TagGenerator<ByteTag> GENERATOR = in -> new ByteTag(in.readByte());
    public static final TagSerializer<ByteTag> SERIALIZER = tag -> new byte[] {
            tag.getValue()
    };

    private final byte value;

    public ByteTag(byte value) {
        super(value);
        this.value = value;
    }

    public ByteTag(String name, byte value) {
        super(value);
        this.value = value;
        setName(name);
    }

    public byte getValue() {
        return value;
    }

}
