package io.github.defective4.minecraft.chatlib.nbt.tag;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class NumberTag extends Tag {

    private final Number number;

    protected NumberTag(Number number) {
        this.number = number;
    }

    public Number getNumber() {
        return number;
    }

    @Override
    public JsonElement toJson() {
        return new JsonPrimitive(number);
    }

}
