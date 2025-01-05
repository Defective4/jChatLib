package io.github.defective4.minecraft.chatlib.nbt.tag;

import java.nio.ByteBuffer;

import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;
import io.github.defective4.minecraft.chatlib.nbt.TagSerializer;

public class ShortTag extends NumberTag {
    public static final TagGenerator<ShortTag> GENERATOR = in -> new ShortTag(in.readShort());
    public static final TagSerializer<ShortTag> SERIALIZER = tag -> {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.putShort(tag.getValue());
        return buffer.array();
    };

    private final short value;

    public ShortTag(short value) {
        super(value);
        this.value = value;
    }

    public ShortTag(String name, short value) {
        super(value);
        this.value = value;
        setName(name);
    }

    public short getValue() {
        return value;
    }
}
