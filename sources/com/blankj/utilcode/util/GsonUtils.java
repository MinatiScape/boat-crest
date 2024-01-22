package com.blankj.utilcode.util;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes.dex */
public final class GsonUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final Map<String, Gson> f2258a = new ConcurrentHashMap();

    public GsonUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static Gson a() {
        return new GsonBuilder().serializeNulls().disableHtmlEscaping().create();
    }

    public static Gson b() {
        Map<String, Gson> map = f2258a;
        Gson gson = map.get("logUtilsGson");
        if (gson == null) {
            Gson create = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            map.put("logUtilsGson", create);
            return create;
        }
        return gson;
    }

    public static <T> T fromJson(String str, @NonNull Class<T> cls) {
        Objects.requireNonNull(cls, "Argument 'type' of type Class<T> (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return (T) fromJson(getGson(), str, (Class<Object>) cls);
    }

    public static Type getArrayType(@NonNull Type type) {
        Objects.requireNonNull(type, "Argument 'type' of type Type (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return TypeToken.getArray(type).getType();
    }

    public static Gson getGson(String str) {
        return f2258a.get(str);
    }

    public static Type getListType(@NonNull Type type) {
        Objects.requireNonNull(type, "Argument 'type' of type Type (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return TypeToken.getParameterized(List.class, type).getType();
    }

    public static Type getMapType(@NonNull Type type, @NonNull Type type2) {
        Objects.requireNonNull(type, "Argument 'keyType' of type Type (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(type2, "Argument 'valueType' of type Type (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return TypeToken.getParameterized(Map.class, type, type2).getType();
    }

    public static Type getSetType(@NonNull Type type) {
        Objects.requireNonNull(type, "Argument 'type' of type Type (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return TypeToken.getParameterized(Set.class, type).getType();
    }

    public static Type getType(@NonNull Type type, @NonNull Type... typeArr) {
        Objects.requireNonNull(type, "Argument 'rawType' of type Type (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(typeArr, "Argument 'typeArguments' of type Type[] (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return TypeToken.getParameterized(type, typeArr).getType();
    }

    public static void setGson(String str, Gson gson) {
        if (TextUtils.isEmpty(str) || gson == null) {
            return;
        }
        f2258a.put(str, gson);
    }

    public static void setGsonDelegate(Gson gson) {
        if (gson == null) {
            return;
        }
        f2258a.put("delegateGson", gson);
    }

    public static String toJson(Object obj) {
        return toJson(getGson(), obj);
    }

    public static Gson getGson() {
        Map<String, Gson> map = f2258a;
        Gson gson = map.get("delegateGson");
        if (gson != null) {
            return gson;
        }
        Gson gson2 = map.get("defaultGson");
        if (gson2 == null) {
            Gson a2 = a();
            map.put("defaultGson", a2);
            return a2;
        }
        return gson2;
    }

    public static String toJson(Object obj, @NonNull Type type) {
        Objects.requireNonNull(type, "Argument 'typeOfSrc' of type Type (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return toJson(getGson(), obj, type);
    }

    public static <T> T fromJson(String str, @NonNull Type type) {
        Objects.requireNonNull(type, "Argument 'type' of type Type (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return (T) fromJson(getGson(), str, type);
    }

    public static String toJson(@NonNull Gson gson, Object obj) {
        Objects.requireNonNull(gson, "Argument 'gson' of type Gson (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return gson.toJson(obj);
    }

    public static <T> T fromJson(@NonNull Reader reader, @NonNull Class<T> cls) {
        Objects.requireNonNull(reader, "Argument 'reader' of type Reader (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(cls, "Argument 'type' of type Class<T> (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return (T) fromJson(getGson(), reader, (Class<Object>) cls);
    }

    public static String toJson(@NonNull Gson gson, Object obj, @NonNull Type type) {
        Objects.requireNonNull(gson, "Argument 'gson' of type Gson (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(type, "Argument 'typeOfSrc' of type Type (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return gson.toJson(obj, type);
    }

    public static <T> T fromJson(@NonNull Reader reader, @NonNull Type type) {
        Objects.requireNonNull(reader, "Argument 'reader' of type Reader (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(type, "Argument 'type' of type Type (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return (T) fromJson(getGson(), reader, type);
    }

    public static <T> T fromJson(@NonNull Gson gson, String str, @NonNull Class<T> cls) {
        Objects.requireNonNull(gson, "Argument 'gson' of type Gson (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(cls, "Argument 'type' of type Class<T> (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return (T) gson.fromJson(str, (Class<Object>) cls);
    }

    public static <T> T fromJson(@NonNull Gson gson, String str, @NonNull Type type) {
        Objects.requireNonNull(gson, "Argument 'gson' of type Gson (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(type, "Argument 'type' of type Type (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return (T) gson.fromJson(str, type);
    }

    public static <T> T fromJson(@NonNull Gson gson, Reader reader, @NonNull Class<T> cls) {
        Objects.requireNonNull(gson, "Argument 'gson' of type Gson (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(cls, "Argument 'type' of type Class<T> (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return (T) gson.fromJson(reader, (Class<Object>) cls);
    }

    public static <T> T fromJson(@NonNull Gson gson, Reader reader, @NonNull Type type) {
        Objects.requireNonNull(gson, "Argument 'gson' of type Gson (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(type, "Argument 'type' of type Type (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return (T) gson.fromJson(reader, type);
    }
}
