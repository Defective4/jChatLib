package io.github.defective4.minecraft.chatlib.nbt.tag;

import java.nio.ByteBuffer;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;
import io.github.defective4.minecraft.chatlib.nbt.TagSerializer;

public class IntArrayTag extends Tag {
    public static final TagGenerator<IntArrayTag> GENERATOR = in -> {
        int[] array = new int[in.readInt()];
        for (int i = 0; i < array.length; i++) array[i] = in.readInt();
        return new IntArrayTag(array);
    };
    public static final TagSerializer<IntArrayTag> SERIALIZER = tag -> {
        int len = tag.getArray().length;
        ByteBuffer buffer = ByteBuffer.allocate(len * 4 + 4);
        buffer.putInt(len);
        for (int i : tag.getArray()) buffer.putInt(i);
        return buffer.array();
    };

    private final int[] array;

    public IntArrayTag(int[] array) {
        this.array = array;
    }

    public IntArrayTag(String name, int[] array) {
        this.array = array;
        setName(name);
    }

    public int[] getArray() {
        return array;
    }

    @Override
    public JsonElement toJson() {
        JsonArray array = new JsonArray();
        for (int b : this.array) array.add(b);
        return array;
    }

}
