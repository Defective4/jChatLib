package io.github.defective4.minecraft.chatlib.nbt.tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;

public class CompoundTag extends Tag {
    public static final TagGenerator<CompoundTag> GENERATOR = in -> {
        CompoundTag tag = new CompoundTag();
        while (true) {
            Tag child = NBTParser.parseI(in, true);
            if (child instanceof EndTag) break;
            if (child != null) tag.tags.add(child);
        }
        return tag;
    };

    private final List<Tag> tags = new ArrayList<>();

    public List<Tag> getTags() {
        return Collections.unmodifiableList(tags);
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
