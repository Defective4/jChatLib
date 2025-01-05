package io.github.defective4.minecraft.chatlib.nbt.tag;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.github.defective4.minecraft.chatlib.nbt.NBTParseException;
import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;
import io.github.defective4.minecraft.chatlib.nbt.TagRegistry;
import io.github.defective4.minecraft.chatlib.nbt.TagSerializer;

public class CompoundTag extends Tag {
    public static final TagGenerator<CompoundTag> GENERATOR = in -> {
        CompoundTag tag = new CompoundTag();
        while (true) {
            Tag child = NBT.parseI(in, true);
            if (child instanceof EndTag) break;
            if (child != null) tag.tags.add(child);
        }
        return tag;
    };
    public static final TagSerializer<CompoundTag> SERIALIZER = tag -> {
        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                DataOutputStream wrapper = new DataOutputStream(buffer)) {
            for (Tag t : tag.getTags()) {
                int type = TagRegistry.getTagId(t.getClass());
                if (type == -1) throw new NBTParseException(
                        "Couldn't encode a compound tag, because one of its children is not present in registry.");
                byte[] name = t.getName().getBytes(StandardCharsets.UTF_8);
                short nameLen = (short) name.length;
                wrapper.writeByte(type);
                wrapper.writeShort(nameLen);
                wrapper.write(name);
                TagSerializer<Tag> serializer = (TagSerializer<Tag>) TagRegistry.getSerializer(type);
                wrapper.write(serializer.serialize(t));
            }
            wrapper.write(0);
            return buffer.toByteArray();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    };

    private final List<Tag> tags;

    public CompoundTag() {
        this(new ArrayList<>());
    }

    public CompoundTag(List<Tag> tags) {
        this.tags = tags;
    }

    public CompoundTag(String name) {
        this(name, new ArrayList<>());
    }

    public CompoundTag(String name, List<Tag> tags) {
        this.tags = tags;
        setName(name);
    }

    public void add(int index, Tag element) {
        tags.add(index, element);
    }

    public boolean add(Tag e) {
        return tags.add(e);
    }

    public void clear() {
        tags.clear();
    }

    public boolean contains(Object o) {
        return tags.contains(o);
    }

    public boolean containsAll(Collection<?> c) {
        return tags.containsAll(c);
    }

    public Tag get(int index) {
        return tags.get(index);
    }

    public List<Tag> getTags() {
        return Collections.unmodifiableList(tags);
    }

    public Tag remove(int index) {
        return tags.remove(index);
    }

    public boolean remove(Object o) {
        return tags.remove(o);
    }

    public Tag set(int index, Tag element) {
        return tags.set(index, element);
    }

    public int size() {
        return tags.size();
    }

    @Override
    public JsonElement toJson() {
        JsonObject obj = new JsonObject();
        for (Tag t : tags) {
            obj.add(t.getName(), t.toJson());
        }
        return obj;
    }
}
