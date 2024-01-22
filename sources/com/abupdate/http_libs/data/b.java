package com.abupdate.http_libs.data;
/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public int f1868a;
    public String b;
    public String c;

    public b(int i, String str) {
        this.f1868a = i;
        this.b = str;
    }

    public int a() {
        return this.f1868a;
    }

    public String b() {
        return "code: " + this.f1868a + ", " + this.b;
    }

    public boolean c() {
        int i = this.f1868a;
        return i < 300 || i == 600;
    }

    public String d() {
        return this.f1868a + ":" + this.b + " (" + e() + ")";
    }

    public final String e() {
        String str = this.c;
        if (str != null) {
            return str;
        }
        Object obj = null;
        try {
            obj = b.class.getDeclaredField("STATUS_" + this.f1868a).get(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str2 = "" + obj;
        this.c = str2;
        return str2;
    }

    public String toString() {
        return b();
    }
}
