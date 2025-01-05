package io.github.defective4.minecraft.chatlib.nbt.tag;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;

import io.github.defective4.minecraft.chatlib.nbt.NBTParseException;
import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;
import io.github.defective4.minecraft.chatlib.nbt.TagRegistry;
import io.github.defective4.minecraft.chatlib.nbt.TagSerializer;

public class NBT {

    public static byte[] encode(Tag tag, boolean network) throws NBTParseException {
        int id = TagRegistry.getTagId(tag.getClass());
        TagSerializer<Tag> serializer = (TagSerializer<Tag>) TagRegistry.getSerializer(id);
        if (serializer == null) throw new NBTParseException("Tag " + tag.getClass() + " is not registered!");
        byte[] data = serializer.serialize(tag);
        byte[] name = tag.getName().getBytes(StandardCharsets.UTF_8);
        short nameLen = (short) name.length;
        ByteBuffer buffer = ByteBuffer.allocate(data.length + (network ? 1 : name.length + 3));
        buffer.put((byte) id);
        if (!network) {
            buffer.putShort(nameLen);
            buffer.put(name);
        }
        buffer.put(data);
        return buffer.array();
    }

    public static void main(String[] args) {
        try (OutputStream os = new FileOutputStream("test.nbt")) {
            CompoundTag tag = new CompoundTag("root");
            tag.add(new ListTag("testlist", Arrays.asList(new LongTag(21), new LongTag(37)), LongTag.class));
            os.write(encode(tag, false));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Tag parse(DataInput dataInput, boolean readRootName) throws IOException, NBTParseException {
        return parseI(dataInput, readRootName);
    }

    public static Tag parse(InputStream input, boolean readRootName, boolean gzip)
            throws IOException, NBTParseException {
        if (gzip) input = new GZIPInputStream(input);
        DataInputStream dataInput = new DataInputStream(input);
        return parseI(dataInput, readRootName);
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
