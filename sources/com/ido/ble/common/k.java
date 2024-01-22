package com.ido.ble.common;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes11.dex */
public class k {

    /* renamed from: a  reason: collision with root package name */
    private static Gson f12152a = new Gson();

    /* loaded from: classes11.dex */
    public static class a extends TypeToken<ArrayList<JsonObject>> {
    }

    public static String a(Object obj) {
        return f12152a.toJson(obj);
    }

    public static <T> List<T> a(String str, Class<T[]> cls) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return Arrays.asList((Object[]) f12152a.fromJson(str, (Class<Object>) cls));
    }

    public static <T> List<T> b(String str, Class<T> cls) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Type type = new a().getType();
        ArrayList arrayList = new ArrayList();
        Iterator it = ((ArrayList) f12152a.fromJson(str, type)).iterator();
        while (it.hasNext()) {
            arrayList.add(f12152a.fromJson((JsonElement) ((JsonObject) it.next()), (Class<Object>) cls));
        }
        return arrayList;
    }

    public static <T> T c(String str, Class<T> cls) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return (T) f12152a.fromJson(str, (Class<Object>) cls);
    }
}
