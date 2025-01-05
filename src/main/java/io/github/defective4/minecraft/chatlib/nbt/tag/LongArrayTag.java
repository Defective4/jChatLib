package io.github.defective4.minecraft.chatlib.nbt.tag;

import java.nio.ByteBuffer;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;
import io.github.defective4.minecraft.chatlib.nbt.TagSerializer;

public class LongArrayTag extends Tag {
    public static final TagGenerator<LongArrayTag> GENERATOR = in -> {
        long[] array = new long[in.readInt()];
        for (int i = 0; i < array.length; i++) array[i] = in.readLong();
        return new LongArrayTag(array);
    };
    public static final TagSerializer<LongArrayTag> SERIALIZER = tag -> {
        int len = tag.getArray().length;
        ByteBuffer buffer = ByteBuffer.allocate(len * 8 + 4);
        buffer.putLong(len);
        for (long i : tag.getArray()) buffer.putLong(i);
        return buffer.array();
    };

    private final long[] array;

    public LongArrayTag(long[] array) {
        this.array = array;
    }

    public LongArrayTag(String name, long[] value) {
        array = value;
        setName(name);
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
