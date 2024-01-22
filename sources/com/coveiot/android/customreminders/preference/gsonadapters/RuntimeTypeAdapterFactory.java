package com.coveiot.android.customreminders.preference.gsonadapters;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
/* loaded from: classes3.dex */
public final class RuntimeTypeAdapterFactory<T> implements TypeAdapterFactory {
    public final Class<?> h;
    public final String i;
    public final Map<String, Class<?>> j = new LinkedHashMap();
    public final Map<Class<?>, String> k = new LinkedHashMap();
    public final boolean l;
    public boolean m;

    public RuntimeTypeAdapterFactory(Class<?> cls, String str, boolean z) {
        if (str != null && cls != null) {
            this.h = cls;
            this.i = str;
            this.l = z;
            return;
        }
        throw null;
    }

    public static <T> RuntimeTypeAdapterFactory<T> of(Class<T> cls, String str, boolean z) {
        return new RuntimeTypeAdapterFactory<>(cls, str, z);
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <R> TypeAdapter<R> create(Gson gson, TypeToken<R> typeToken) {
        if (typeToken == null) {
            return null;
        }
        Class<? super R> rawType = typeToken.getRawType();
        if (this.m ? this.h.isAssignableFrom(rawType) : this.h.equals(rawType)) {
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final LinkedHashMap linkedHashMap = new LinkedHashMap();
            final LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            for (Map.Entry<String, Class<?>> entry : this.j.entrySet()) {
                TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get((Class) entry.getValue()));
                linkedHashMap.put(entry.getKey(), delegateAdapter);
                linkedHashMap2.put(entry.getValue(), delegateAdapter);
            }
            return new TypeAdapter<R>() { // from class: com.coveiot.android.customreminders.preference.gsonadapters.RuntimeTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public R read(JsonReader jsonReader) throws IOException {
                    JsonElement jsonElement = (JsonElement) adapter.read(jsonReader);
                    JsonElement remove = RuntimeTypeAdapterFactory.this.l ? jsonElement.getAsJsonObject().get(RuntimeTypeAdapterFactory.this.i) : jsonElement.getAsJsonObject().remove(RuntimeTypeAdapterFactory.this.i);
                    if (remove == null) {
                        throw new JsonParseException("cannot deserialize " + RuntimeTypeAdapterFactory.this.h + " because it does not define a field named " + RuntimeTypeAdapterFactory.this.i);
                    }
                    String asString = remove.getAsString();
                    TypeAdapter typeAdapter = (TypeAdapter) linkedHashMap.get(asString);
                    if (typeAdapter == null) {
                        throw new JsonParseException("cannot deserialize " + RuntimeTypeAdapterFactory.this.h + " subtype named " + asString + "; did you forget to register a subtype?");
                    }
                    return (R) typeAdapter.fromJsonTree(jsonElement);
                }

                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, R r) throws IOException {
                    Class<?> cls = r.getClass();
                    String str = (String) RuntimeTypeAdapterFactory.this.k.get(cls);
                    TypeAdapter typeAdapter = (TypeAdapter) linkedHashMap2.get(cls);
                    if (typeAdapter != null) {
                        JsonObject asJsonObject = typeAdapter.toJsonTree(r).getAsJsonObject();
                        if (RuntimeTypeAdapterFactory.this.l) {
                            adapter.write(jsonWriter, asJsonObject);
                            return;
                        }
                        JsonObject jsonObject = new JsonObject();
                        if (!asJsonObject.has(RuntimeTypeAdapterFactory.this.i)) {
                            jsonObject.add(RuntimeTypeAdapterFactory.this.i, new JsonPrimitive(str));
                            for (Map.Entry<String, JsonElement> entry2 : asJsonObject.entrySet()) {
                                jsonObject.add(entry2.getKey(), entry2.getValue());
                            }
                            adapter.write(jsonWriter, jsonObject);
                            return;
                        }
                        throw new JsonParseException("cannot serialize " + cls.getName() + " because it already defines a field named " + RuntimeTypeAdapterFactory.this.i);
                    }
                    throw new JsonParseException("cannot serialize " + cls.getName() + "; did you forget to register a subtype?");
                }
            }.nullSafe();
        }
        return null;
    }

    public RuntimeTypeAdapterFactory<T> recognizeSubtypes() {
        this.m = true;
        return this;
    }

    public RuntimeTypeAdapterFactory<T> registerSubtype(Class<? extends T> cls, String str) {
        if (cls != null && str != null) {
            if (!this.k.containsKey(cls) && !this.j.containsKey(str)) {
                this.j.put(str, cls);
                this.k.put(cls, str);
                return this;
            }
            throw new IllegalArgumentException("types and labels must be unique");
        }
        throw null;
    }

    public static <T> RuntimeTypeAdapterFactory<T> of(Class<T> cls, String str) {
        return new RuntimeTypeAdapterFactory<>(cls, str, false);
    }

    public static <T> RuntimeTypeAdapterFactory<T> of(Class<T> cls) {
        return new RuntimeTypeAdapterFactory<>(cls, "type", false);
    }

    public RuntimeTypeAdapterFactory<T> registerSubtype(Class<? extends T> cls) {
        return registerSubtype(cls, cls.getSimpleName());
    }
}
