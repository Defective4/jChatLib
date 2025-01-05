package io.github.defective4.minecraft.chatlib.nbt.tag;

import java.nio.ByteBuffer;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;
import io.github.defective4.minecraft.chatlib.nbt.TagSerializer;

public class ByteArrayTag extends Tag {
    public static final TagGenerator<ByteArrayTag> GENERATOR = in -> {
        byte[] array = new byte[in.readInt()];
        in.readFully(array);
        return new ByteArrayTag(array);
    };

    public static final TagSerializer<ByteArrayTag> SERIALIZER = tag -> {
        ByteBuffer buffer = ByteBuffer.allocate(4 + tag.getArray().length);
        buffer.putInt(tag.getArray().length);
        buffer.put(tag.getArray());
        return buffer.array();
    };

    private final byte[] array;

    public ByteArrayTag(byte[] array) {
        this.array = array;
    }

    public ByteArrayTag(String name, byte[] array) {
        this.array = array;
        setName(name);
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
