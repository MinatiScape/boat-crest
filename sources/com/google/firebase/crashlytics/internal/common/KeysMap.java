package com.google.firebase.crashlytics.internal.common;

import androidx.annotation.NonNull;
import com.google.firebase.crashlytics.internal.Logger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes10.dex */
public class KeysMap {

    /* renamed from: a  reason: collision with root package name */
    public final Map<String, String> f11138a = new HashMap();
    public final int b;
    public final int c;

    public KeysMap(int i, int i2) {
        this.b = i;
        this.c = i2;
    }

    public final String a(String str) {
        if (str != null) {
            return sanitizeAttribute(str);
        }
        throw new IllegalArgumentException("Custom attribute key must not be null.");
    }

    @NonNull
    public synchronized Map<String, String> getKeys() {
        return Collections.unmodifiableMap(new HashMap(this.f11138a));
    }

    public String sanitizeAttribute(String str) {
        if (str != null) {
            String trim = str.trim();
            int length = trim.length();
            int i = this.c;
            return length > i ? trim.substring(0, i) : trim;
        }
        return str;
    }

    public synchronized void setKey(String str, String str2) {
        String a2 = a(str);
        if (this.f11138a.size() >= this.b && !this.f11138a.containsKey(a2)) {
            Logger logger = Logger.getLogger();
            logger.w("Ignored entry \"" + str + "\" when adding custom keys. Maximum allowable: " + this.b);
        }
        this.f11138a.put(a2, str2 == null ? "" : sanitizeAttribute(str2));
    }

    public synchronized void setKeys(Map<String, String> map) {
        int i = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String a2 = a(entry.getKey());
            if (this.f11138a.size() >= this.b && !this.f11138a.containsKey(a2)) {
                i++;
            }
            String value = entry.getValue();
            this.f11138a.put(a2, value == null ? "" : sanitizeAttribute(value));
        }
        if (i > 0) {
            Logger logger = Logger.getLogger();
            logger.w("Ignored " + i + " entries when adding custom keys. Maximum allowable: " + this.b);
        }
    }
}
