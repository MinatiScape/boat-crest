package com.coveiot.android.spo2sdk.utils;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes7.dex */
public class LowercaseEnumTypeAdapterFactory implements TypeAdapterFactory {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes7.dex */
    public class a<T> extends TypeAdapter<T> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Map f5792a;

        public a(Map map) {
            this.f5792a = map;
        }

        @Override // com.google.gson.TypeAdapter
        public T read(JsonReader jsonReader) {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return (T) this.f5792a.get(jsonReader.nextString());
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, T t) {
            if (t == null) {
                jsonWriter.nullValue();
            } else {
                jsonWriter.value(LowercaseEnumTypeAdapterFactory.this.a(t));
            }
        }
    }

    public final String a(Object obj) {
        return obj.toString().toLowerCase();
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Object[] enumConstants;
        Class<? super T> rawType = typeToken.getRawType();
        if (rawType.isEnum()) {
            HashMap hashMap = new HashMap();
            for (Object obj : rawType.getEnumConstants()) {
                hashMap.put(a(obj), obj);
            }
            return new a(hashMap);
        }
        return null;
    }
}
