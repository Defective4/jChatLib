package io.github.defective4.minecraft.chatlib.nbt.tag;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;

public class ByteArrayTag extends Tag {
    public static final TagGenerator<ByteArrayTag> GENERATOR = in -> {
        byte[] array = new byte[in.readInt()];
        in.readFully(array);
        return new ByteArrayTag(array);
    };

    private final byte[] array;

    public ByteArrayTag(byte[] array) {
        this.array = array;
    }

    public byte[] getArray() {
        return array;
    }

    @Override
    public JsonElement toJson() {
        JsonArray array = new JsonArray();
        for (int b : this.array) array.add(b);
        return array;
    }

}
