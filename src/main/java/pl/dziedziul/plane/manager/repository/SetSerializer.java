package pl.dziedziul.plane.manager.repository;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Set;

/**
 * Exclude empty sets
 * Created on 2015-02-08.
 */
class SetSerializer implements JsonSerializer<Set<?>> {
    @Override
    public JsonElement serialize(Set<?> src, Type typeOfSrc, JsonSerializationContext context) {
        if (src == null || src.isEmpty()) //exclude empty list
            return null;

        JsonArray array = new JsonArray();
        for (Object child : src) {
            JsonElement element = context.serialize(child);
            array.add(element);
        }
        return array;
    }
}
