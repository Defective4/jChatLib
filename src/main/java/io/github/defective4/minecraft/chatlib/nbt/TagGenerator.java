package io.github.defective4.minecraft.chatlib.nbt;

import java.io.DataInputStream;
import java.io.IOException;

import io.github.defective4.minecraft.chatlib.nbt.tag.Tag;

public interface TagGenerator<T extends Tag> {
    T generateFromStream(DataInputStream in) throws IOException, NBTParseException;
}