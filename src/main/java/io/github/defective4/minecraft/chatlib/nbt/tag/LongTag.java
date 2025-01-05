package io.github.defective4.minecraft.chatlib.nbt.tag;

import java.nio.ByteBuffer;

import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;
import io.github.defective4.minecraft.chatlib.nbt.TagSerializer;

public class LongTag extends NumberTag {
    public static final TagGenerator<LongTag> GENERATOR = in -> new LongTag(in.readLong());
    public static final TagSerializer<LongTag> SERIALIZER = tag -> {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(tag.getValue());
        return buffer.array();
    };

    private final long value;

    public LongTag(long l) {
        super(l);
        value = l;
    }

    public LongTag(String name, long value) {
        super(value);
        this.value = value;
        setName(name);
    }

    public long getValue() {
        return value;
    }
}
