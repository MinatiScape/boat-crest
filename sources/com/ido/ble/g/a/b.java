package com.ido.ble.g.a;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private String f12284a;
    private String b;
    public List<a> c;
    private long d;

    public b() {
        this.f12284a = "";
        this.b = "";
        this.c = new ArrayList();
        this.d = System.currentTimeMillis();
    }

    public b(String str, String str2) {
        this.f12284a = "";
        this.b = "";
        this.c = new ArrayList();
        this.d = System.currentTimeMillis();
        this.f12284a = str;
        this.b = str2;
    }

    public String a() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("__source__", (Object) this.b);
        jSONObject.put("__topic__", (Object) this.f12284a);
        JSONArray jSONArray = new JSONArray();
        for (a aVar : this.c) {
            jSONArray.add(new JSONObject(aVar.a()));
        }
        jSONObject.put("__logs__", (Object) jSONArray);
        return jSONObject.toJSONString();
    }

    public void a(long j) {
        this.d = j;
    }

    public void a(a aVar) {
        this.c.add(aVar);
    }

    public void a(String str) {
        this.b = str;
    }

    public long b() {
        return this.d;
    }

    public void b(String str) {
        this.f12284a = str;
    }
}
