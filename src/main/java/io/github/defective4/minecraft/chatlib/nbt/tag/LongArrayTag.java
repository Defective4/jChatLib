package io.github.defective4.minecraft.chatlib.nbt.tag;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

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

    @Override
    public JsonElement toJson() {
        JsonArray array = new JsonArray();
        for (long b : this.array) array.add(b);
        return array;
    }
}
