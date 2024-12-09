package io.github.defective4.minecraft.chatlib.nbt.tag;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;

public class StringTag extends Tag {
    public static final TagGenerator<StringTag> GENERATOR = in -> new StringTag(in.readUTF());

    private final String value;

    public StringTag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public JsonElement toJson() {
        return new JsonPrimitive(value);
    }

}
