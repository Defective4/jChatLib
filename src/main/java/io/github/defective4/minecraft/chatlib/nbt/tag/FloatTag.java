package io.github.defective4.minecraft.chatlib.nbt.tag;

import java.nio.ByteBuffer;

import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;
import io.github.defective4.minecraft.chatlib.nbt.TagSerializer;

public class FloatTag extends NumberTag {
    public static final TagGenerator<FloatTag> GENERATOR = in -> new FloatTag(in.readFloat());
    public static final TagSerializer<FloatTag> SERIALIZER = tag -> {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putFloat(tag.getValue());
        return buffer.array();
    };

    private final float value;

    public FloatTag(float f) {
        super(f);
        value = f;
    }

    public FloatTag(String name, float f) {
        super(f);
        value = f;
        setName(name);
    }

    public float getValue() {
        return value;
    }
}
