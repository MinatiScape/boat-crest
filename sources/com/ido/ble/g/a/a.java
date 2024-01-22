package com.ido.ble.g.a;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes11.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public Map<String, Object> f12283a;

    public a() {
        HashMap hashMap = new HashMap();
        this.f12283a = hashMap;
        hashMap.put("__time__", Integer.valueOf(new Long(System.currentTimeMillis() / 1000).intValue()));
    }

    public Map<String, Object> a() {
        return this.f12283a;
    }

    public void a(int i) {
        this.f12283a.put("__time__", Integer.valueOf(i));
    }

    public void a(String str, String str2) {
        if (str == null || str.isEmpty()) {
            return;
        }
        if (str2 == null) {
            this.f12283a.put(str, "");
        } else {
            this.f12283a.put(str, str2);
        }
    }

    public String toString() {
        return "Log{mContent=" + this.f12283a + '}';
    }
}
