package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public final class zzcz {
    @VisibleForTesting
    public static Object zza(Object obj) throws JSONException {
        if (!(obj instanceof JSONArray)) {
            if (!JSONObject.NULL.equals(obj)) {
                if (obj instanceof JSONObject) {
                    JSONObject jSONObject = (JSONObject) obj;
                    HashMap hashMap = new HashMap();
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        hashMap.put(next, zza(jSONObject.get(next)));
                    }
                    return hashMap;
                }
                return obj;
            }
            throw new RuntimeException("JSON nulls are not supported");
        }
        throw new RuntimeException("JSONArrays are not supported");
    }
}
