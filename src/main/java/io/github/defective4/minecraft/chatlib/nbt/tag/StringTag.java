package io.github.defective4.minecraft.chatlib.nbt.tag;

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
}
