package io.github.defective4.minecraft.chatlib.nbt;

import java.util.HashMap;
import java.util.Map;

import io.github.defective4.minecraft.chatlib.nbt.tag.*;

public abstract class TagRegistry {
    private static final Map<Integer, Class<? extends Tag>> REGISTRY = new HashMap<>();

    static {
        REGISTRY.put(0x00, EndTag.class);
        REGISTRY.put(0x01, ByteTag.class);
        REGISTRY.put(0x02, ShortTag.class);
        REGISTRY.put(0x03, IntTag.class);
        REGISTRY.put(0x04, LongTag.class);
        REGISTRY.put(0x05, FloatTag.class);
        REGISTRY.put(0x06, DoubleTag.class);
        REGISTRY.put(0x07, ByteArrayTag.class);
        REGISTRY.put(0x08, StringTag.class);
        REGISTRY.put(0x09, ListTag.class);
        REGISTRY.put(0x0A, CompoundTag.class);
        REGISTRY.put(0x0B, IntArrayTag.class);
        REGISTRY.put(0x0C, LongArrayTag.class);
    }

    public static TagGenerator<?> getGenerator(int id) {
        if (!REGISTRY.containsKey(id)) return null;
        try {
            return (TagGenerator<?>) REGISTRY.get(id).getField("GENERATOR").get(null);
        } catch (Exception e) {
            return null;
        }
    }

    public static TagSerializer<?> getSerializer(int id) {
        if (!REGISTRY.containsKey(id)) return null;
        try {
            return (TagSerializer<?>) REGISTRY.get(id).getField("SERIALIZER").get(null);
        } catch (Exception e) {
            return null;
        }
    }

    public static int getTagId(Class<? extends Tag> clazz) {
        for (Map.Entry<Integer, Class<? extends Tag>> entry : REGISTRY.entrySet()) {
            if (entry.getValue() == clazz) return entry.getKey();
        }
        return -1;
    }

    public static Class<? extends Tag> getTagType(int id) {
        return REGISTRY.get(id);
    }
}
