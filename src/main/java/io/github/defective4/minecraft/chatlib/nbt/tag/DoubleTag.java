package io.github.defective4.minecraft.chatlib.nbt.tag;

import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;

public class DoubleTag extends Tag {
    public static final TagGenerator<DoubleTag> GENERATOR = in -> new DoubleTag(in.readDouble());

    private final double value;

    public DoubleTag(double d) {
        value = d;
    }

    public double getValue() {
        return value;
    }
}
