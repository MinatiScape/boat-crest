package com.coveiot.sdk.ble.utils;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.HashMap;
/* loaded from: classes9.dex */
public class LowercaseEnumTypeAdapterFactory implements TypeAdapterFactory {
    public final String b(Object obj) {
        return obj.toString().toLowerCase();
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Object[] enumConstants;
        Class<? super T> rawType = typeToken.getRawType();
        if (rawType.isEnum()) {
            final HashMap hashMap = new HashMap();
            for (Object obj : rawType.getEnumConstants()) {
                hashMap.put(b(obj), obj);
            }
            return new TypeAdapter<T>() { // from class: com.coveiot.sdk.ble.utils.LowercaseEnumTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public T read(JsonReader jsonReader) throws IOException {
                    if (jsonReader.peek() == JsonToken.NULL) {
                        jsonReader.nextNull();
                        return null;
                    }
                    return (T) hashMap.get(jsonReader.nextString());
                }

                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, T t) throws IOException {
                    if (t == null) {
                        jsonWriter.nullValue();
                    } else {
                        jsonWriter.value(LowercaseEnumTypeAdapterFactory.this.b(t));
                    }
                }
            };
        }
        return null;
    }
}
