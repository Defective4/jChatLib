package io.github.defective4.minecraft.chatlib.nbt.tag;

import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;

public class IntTag extends Tag {
    public static final TagGenerator<IntTag> GENERATOR = in -> new IntTag(in.readInt());

    private final int value;

    public IntTag(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
