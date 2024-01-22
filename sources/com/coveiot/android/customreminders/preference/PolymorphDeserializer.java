package com.coveiot.android.customreminders.preference;

import android.os.Build;
import com.coveiot.android.customreminders.preference.gsonadapters.JsonSubtype;
import com.coveiot.android.customreminders.preference.gsonadapters.JsonType;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.function.Supplier;
/* loaded from: classes3.dex */
public class PolymorphDeserializer<T> implements JsonDeserializer<T> {
    public static /* synthetic */ boolean b(String str, JsonSubtype jsonSubtype) {
        return jsonSubtype.name().equals(str);
    }

    @Override // com.google.gson.JsonDeserializer
    public T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try {
            JsonType jsonType = (JsonType) (Build.VERSION.SDK_INT >= 28 ? Class.forName(type.getTypeName()) : null).getDeclaredAnnotation(JsonType.class);
            final String asString = jsonElement.getAsJsonObject().get(jsonType.property()).getAsString();
            return (T) jsonDeserializationContext.deserialize(jsonElement, ((JsonSubtype) Arrays.stream(jsonType.subtypes()).filter(new Predicate() { // from class: com.coveiot.android.customreminders.preference.a
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean b;
                    b = PolymorphDeserializer.b(asString, (JsonSubtype) obj);
                    return b;
                }
            }).findFirst().orElseThrow(new Supplier() { // from class: com.coveiot.android.customreminders.preference.b
                @Override // java.util.function.Supplier
                public final Object get() {
                    return new IllegalArgumentException();
                }
            })).clazz());
        } catch (Exception e) {
            throw new JsonParseException("Failed deserialize json", e);
        }
    }
}
