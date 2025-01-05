package io.github.defective4.minecraft.chatlib.nbt.tag;

import com.google.gson.JsonElement;

import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;
import io.github.defective4.minecraft.chatlib.nbt.TagSerializer;

public class EndTag extends Tag {
    public static final TagGenerator<EndTag> GENERATOR = in -> new EndTag();
    public static final TagSerializer<EndTag> SERIALIZER = tag -> new byte[0];

    public EndTag() {}

    public EndTag(String name) {
        setName(name);
    }

    @Override
    public JsonElement toJson() {
        return null;
    }
}
