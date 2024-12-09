package io.github.defective4.minecraft.chatlib.nbt.tag;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import io.github.defective4.minecraft.chatlib.nbt.NBTParseException;
import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;
import io.github.defective4.minecraft.chatlib.nbt.TagRegistry;

public class NBTParser {
    private NBTParser() {}

    public static CompoundTag parse(InputStream input) throws IOException, NBTParseException {
        DataInputStream dataInput = new DataInputStream(input);
        Tag tag = parseI(dataInput);
        if (!(tag instanceof CompoundTag)) throw new NBTParseException("Root tag is not a compound tag");
        return (CompoundTag) tag;
    }

    protected static Tag parseI(DataInputStream dataInput) throws IOException, NBTParseException {
        int type = dataInput.read();
        TagGenerator<?> gen = TagRegistry.getGenerator(type);
        if (gen == null) return null;
        String name = null;
        if (type != 0) {
            byte[] data = new byte[dataInput.readShort()];
            dataInput.readFully(data);
            name = new String(data, StandardCharsets.UTF_8);
        }
        Tag tag = gen.generateFromStream(dataInput);
        tag.setName(name);
        return tag;
    }
}
