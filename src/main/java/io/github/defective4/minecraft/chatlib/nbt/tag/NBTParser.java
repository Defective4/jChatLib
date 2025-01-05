package io.github.defective4.minecraft.chatlib.nbt.tag;

import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;

import io.github.defective4.minecraft.chatlib.nbt.NBTParseException;

public class NBTParser {
    private NBTParser() {}

    /**
     * @deprecated Use {@link NBT#parse(DataInput,boolean)} instead
     */
    @Deprecated
    public static Tag parse(DataInput dataInput, boolean readRootName) throws IOException, NBTParseException {
        return NBT.parse(dataInput, readRootName);
    }

    /**
     * @deprecated Use {@link NBT#parse(InputStream,boolean,boolean)} instead
     */
    @Deprecated
    public static Tag parse(InputStream input, boolean readRootName, boolean gzip)
            throws IOException, NBTParseException {
        return NBT.parse(input, readRootName, gzip);
    }

    /**
     * @deprecated Use {@link NBT#parseI(DataInput,boolean)} instead
     */
    @Deprecated
    protected static Tag parseI(DataInput dataInput, boolean readName) throws IOException, NBTParseException {
        return NBT.parseI(dataInput, readName);
    }
}
