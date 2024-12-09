package io.github.defective4.minecraft.chatlib.nbt.tag;

import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;

public class FloatTag extends Tag {
    public static final TagGenerator<FloatTag> GENERATOR = in -> new FloatTag(in.readFloat());

    private final float value;

    public FloatTag(float f) {
        value = f;
    }

    public float getValue() {
        return value;
    }
}
