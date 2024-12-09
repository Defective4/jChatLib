package io.github.defective4.minecraft.chatlib.nbt.tag;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;

public class IntArrayTag extends Tag {
    public static final TagGenerator<IntArrayTag> GENERATOR = in -> {
        int[] array = new int[in.readInt()];
        for (int i = 0; i < array.length; i++) array[i] = in.readInt();
        return new IntArrayTag(array);
    };

    private final int[] array;

    public IntArrayTag(int[] array) {
        this.array = array;
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
