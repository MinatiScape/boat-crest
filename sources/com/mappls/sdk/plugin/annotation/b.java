package com.mappls.sdk.plugin.annotation;

import androidx.annotation.Nullable;
import com.google.gson.JsonArray;
/* loaded from: classes11.dex */
public class b {
    @Nullable
    public static JsonArray a(Float[] fArr) {
        if (fArr != null) {
            JsonArray jsonArray = new JsonArray();
            for (Float f : fArr) {
                jsonArray.add(f);
            }
            return jsonArray;
        }
        return null;
    }

    @Nullable
    public static JsonArray b(String[] strArr) {
        if (strArr != null) {
            JsonArray jsonArray = new JsonArray();
            for (String str : strArr) {
                jsonArray.add(str);
            }
            return jsonArray;
        }
        return null;
    }

    @Nullable
    public static Float[] c(JsonArray jsonArray) {
        if (jsonArray != null) {
            Float[] fArr = new Float[jsonArray.size()];
            for (int i = 0; i < jsonArray.size(); i++) {
                fArr[i] = Float.valueOf(jsonArray.get(i).getAsFloat());
            }
            return fArr;
        }
        return null;
    }

    @Nullable
    public static String[] d(JsonArray jsonArray) {
        if (jsonArray != null) {
            String[] strArr = new String[jsonArray.size()];
            for (int i = 0; i < jsonArray.size(); i++) {
                strArr[i] = jsonArray.get(i).getAsString();
            }
            return strArr;
        }
        return null;
    }
}
