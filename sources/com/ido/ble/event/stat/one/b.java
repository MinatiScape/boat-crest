package com.ido.ble.event.stat.one;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes11.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private Map<String, Integer> f12218a = new HashMap();

    public void a() {
        this.f12218a.clear();
    }

    public void a(String str) {
        if (this.f12218a.containsKey(str)) {
            this.f12218a.put(str, Integer.valueOf(this.f12218a.get(str).intValue() + 1));
        } else {
            this.f12218a.put(str, 1);
        }
    }

    public String b() {
        return this.f12218a.size() == 0 ? "null" : new Gson().toJson(this.f12218a);
    }
}
