package io.github.defective4.minecraft.chatlib.nbt.tag;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;

import io.github.defective4.minecraft.chatlib.nbt.NBTParseException;
import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;
import io.github.defective4.minecraft.chatlib.nbt.TagRegistry;

public class NBTParser {
    private NBTParser() {}

    public static void main(String[] args) {
        try (InputStream is = new FileInputStream("Test.nbt")) {
            System.out.println(parse(is, true, true).toJson());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CompoundTag parse(DataInput dataInput, boolean readRootName) throws IOException, NBTParseException {
        Tag tag = parseI(dataInput, readRootName);
        if (!(tag instanceof CompoundTag)) throw new NBTParseException("Root tag is not a compound tag");
        return (CompoundTag) tag;
    }

    public static CompoundTag parse(InputStream input, boolean readRootName, boolean gzip)
            throws IOException, NBTParseException {
        if (gzip) input = new GZIPInputStream(input);
        DataInputStream dataInput = new DataInputStream(input);
        Tag tag = parseI(dataInput, readRootName);
        if (!(tag instanceof CompoundTag)) throw new NBTParseException("Root tag is not a compound tag");
        return (CompoundTag) tag;
    }

    protected static Tag parseI(DataInput dataInput, boolean readName) throws IOException, NBTParseException {
        int type = dataInput.readByte();
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
