package io.github.defective4.minecraft.chatlib.nbt.tag;

import java.nio.ByteBuffer;

import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;
import io.github.defective4.minecraft.chatlib.nbt.TagSerializer;

public class DoubleTag extends NumberTag {
    public static final TagGenerator<DoubleTag> GENERATOR = in -> new DoubleTag(in.readDouble());
    public static final TagSerializer<DoubleTag> SERIALIZER = tag -> {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putDouble(tag.getValue());
        return buffer.array();
    };

    private final double value;

    public DoubleTag(double d) {
        super(d);
        value = d;
    }

    public DoubleTag(String name, double d) {
        super(d);
        value = d;
        setName(name);
    }

    public double getValue() {
        return value;
    }
}
