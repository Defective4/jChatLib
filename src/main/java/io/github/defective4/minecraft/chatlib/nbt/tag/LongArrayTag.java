package io.github.defective4.minecraft.chatlib.nbt.tag;

import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;

public class LongArrayTag extends Tag {
    public static final TagGenerator<LongArrayTag> GENERATOR = in -> {
        long[] array = new long[in.readInt()];
        for (int i = 0; i < array.length; i++) array[i] = in.readLong();
        return new LongArrayTag(array);
    };

    private final long[] array;

    public LongArrayTag(long[] array) {
        this.array = array;
    }

    public long[] getArray() {
        return array;
    }

}
