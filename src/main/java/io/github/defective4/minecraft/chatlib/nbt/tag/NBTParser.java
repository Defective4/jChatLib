package io.github.defective4.minecraft.chatlib.nbt.tag;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import io.github.defective4.minecraft.chatlib.nbt.NBTParseException;
import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;
import io.github.defective4.minecraft.chatlib.nbt.TagRegistry;

public class NBTParser {
    private NBTParser() {}

    public static void main(String[] args) {
        try (InputStream is = new FileInputStream("Test.nbt")) {
            System.out.println(parse(is, true).getTags().get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CompoundTag parse(InputStream input, boolean readRootName) throws IOException, NBTParseException {
        DataInputStream dataInput = new DataInputStream(input);
        Tag tag = parseI(dataInput, readRootName);
        if (!(tag instanceof CompoundTag)) throw new NBTParseException("Root tag is not a compound tag");
        return (CompoundTag) tag;
    }

    protected static Tag parseI(DataInputStream dataInput, boolean readName) throws IOException, NBTParseException {
        int type = dataInput.read();
        TagGenerator<?> gen = TagRegistry.getGenerator(type);
        if (gen == null) return null;
        String name = null;
        if (readName && type != 0) {
            byte[] data = new byte[dataInput.readShort()];
            dataInput.readFully(data);
            name = new String(data, StandardCharsets.UTF_8);
        }
        Tag tag = gen.generateFromStream(dataInput);
        tag.setName(name);
        return tag;
    }
}
