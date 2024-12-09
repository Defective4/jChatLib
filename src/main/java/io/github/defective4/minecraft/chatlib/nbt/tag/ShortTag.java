package io.github.defective4.minecraft.chatlib.nbt.tag;

import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;

public class ShortTag extends NumberTag {
    public static final TagGenerator<ShortTag> GENERATOR = in -> new ShortTag(in.readShort());

    private final short value;

    public ShortTag(short value) {
        super(value);
        this.value = value;
    }

    public short getValue() {
        return value;
    }
}
