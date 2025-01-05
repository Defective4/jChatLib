package io.github.defective4.minecraft.chatlib.nbt.tag;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;
import io.github.defective4.minecraft.chatlib.nbt.TagSerializer;

public class StringTag extends Tag {
    public static final TagGenerator<StringTag> GENERATOR = in -> new StringTag(in.readUTF());
    public static final TagSerializer<StringTag> SERIALIZER = tag -> {
        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                DataOutputStream wrapper = new DataOutputStream(buffer)) {
            wrapper.writeUTF(tag.getValue());
            return buffer.toByteArray();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    };

    private final String value;

    public StringTag(String value) {
        this.value = value;
    }

    public StringTag(String name, String value) {
        this.value = value;
        setName(name);
    }

    public String getValue() {
        return value;
    }

    @Override
    public JsonElement toJson() {
        return new JsonPrimitive(value);
    }

}
