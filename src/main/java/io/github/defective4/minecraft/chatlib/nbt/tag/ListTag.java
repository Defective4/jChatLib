package io.github.defective4.minecraft.chatlib.nbt.tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import io.github.defective4.minecraft.chatlib.nbt.NBTParseException;
import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;
import io.github.defective4.minecraft.chatlib.nbt.TagRegistry;

public class ListTag extends Tag implements Iterable<Tag> {

    public static final TagGenerator<ListTag> GENERATOR = in -> {
        int typeID = in.readByte();
        Class<? extends Tag> type = TagRegistry.getTagType(typeID);
        TagGenerator<?> gen = TagRegistry.getGenerator(typeID);
        if (type == null || gen == null) throw new NBTParseException("Couldn't parse list: unknown tag id " + typeID);
        int size = in.readInt();
        List<Tag> tags = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Tag tag = gen.generateFromStream(in);
            tags.add(tag);
        }
        return new ListTag(Collections.unmodifiableList(tags), type);
    };

    private final List<? extends Tag> tags;
    private final Class<? extends Tag> type;

    public ListTag(List<? extends Tag> tags, Class<? extends Tag> type) {
        this.type = type;
        this.tags = tags;
    }

    public void clear() {
        tags.clear();
    }

    public Tag get(int index) {
        return tags.get(index);
    }

    public List<? extends Tag> getTags() {
        return tags;
    }

    public Class<? extends Tag> getType() {
        return type;
    }

    public boolean isEmpty() {
        return tags.isEmpty();
    }

    @Override
    public Iterator<Tag> iterator() {
        return (Iterator<Tag>) tags.iterator();
    }

    public Tag remove(int index) {
        return tags.remove(index);
    }

    public boolean remove(Object o) {
        return tags.remove(o);
    }

    public int size() {
        return tags.size();
    }

    @Override
    public JsonElement toJson() {
        JsonArray array = new JsonArray();
        if (type != EndTag.class) {
            for (Tag t : tags) array.add(t.toJson());
        }
        return array;
    }

}
