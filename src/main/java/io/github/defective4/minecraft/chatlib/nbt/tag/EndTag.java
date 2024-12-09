package io.github.defective4.minecraft.chatlib.nbt.tag;

import com.google.gson.JsonElement;

import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;

public class EndTag extends Tag {
    public static final TagGenerator<EndTag> GENERATOR = in -> new EndTag();

    @Override
    public JsonElement toJson() {
        return null;
    }
}
