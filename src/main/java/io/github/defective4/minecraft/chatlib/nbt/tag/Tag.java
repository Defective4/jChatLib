package io.github.defective4.minecraft.chatlib.nbt.tag;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;

import io.github.defective4.minecraft.chatlib.nbt.TagGenerator;

public abstract class Tag {
    protected String name;

    public String getName() {
        return name;
    }

    public abstract JsonElement toJson();

    @Override
    public String toString() {
        List<String> fields = new ArrayList<>();
        fields.add("name=" + name);
        for (Field f : getClass().getDeclaredFields()) {
            if (f.getType() != TagGenerator.class) try {
                f.setAccessible(true);
                fields.add(f.getName() + "=" + f.get(this));
            } catch (Exception e) {}
        }
        return getClass().getSimpleName() + " [" + String.join(", ", fields.toArray(new String[0])) + "]";
    }

    protected void setName(String name) {
        this.name = name;
    }
}
