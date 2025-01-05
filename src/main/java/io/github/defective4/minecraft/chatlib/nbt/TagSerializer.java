package io.github.defective4.minecraft.chatlib.nbt;

import io.github.defective4.minecraft.chatlib.nbt.tag.Tag;

public interface TagSerializer<T extends Tag> {
    byte[] serialize(T tag) throws NBTParseException;
}
