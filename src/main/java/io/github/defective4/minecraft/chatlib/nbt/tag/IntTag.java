package io.github.defective4.minecraft.chatlib.nbt.tag;

import java.nio.ByteBuffer;

import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;
import io.github.defective4.minecraft.chatlib.nbt.TagSerializer;

public class IntTag extends NumberTag {
    public static final TagGenerator<IntTag> GENERATOR = in -> new IntTag(in.readInt());
    public static final TagSerializer<IntTag> SERIALIZER = tag -> {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putInt(tag.getValue());
        return buffer.array();
    };

    private final int value;

    public IntTag(int value) {
        super(value);
        this.value = value;
    }

    public IntTag(String name, int value) {
        super(value);
        this.value = value;
        setName(name);
    }

    public int getValue() {
        return value;
    }
}
