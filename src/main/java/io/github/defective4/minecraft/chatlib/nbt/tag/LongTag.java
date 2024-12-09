package io.github.defective4.minecraft.chatlib.nbt.tag;

import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;

public class LongTag extends Tag {
    public static final TagGenerator<LongTag> GENERATOR = in -> new LongTag(in.readLong());

    private final long value;

    public LongTag(long l) {
        value = l;
    }

    public long getValue() {
        return value;
    }
}
