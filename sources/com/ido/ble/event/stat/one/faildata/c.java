package com.ido.ble.event.stat.one.faildata;
/* loaded from: classes11.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private Long f12223a;
    private String b;
    private String c;

    public c() {
    }

    public c(Long l, String str, String str2) {
        this.f12223a = l;
        this.b = str;
        this.c = str2;
    }

    public String a() {
        return this.b;
    }

    public void a(Long l) {
        this.f12223a = l;
    }

    public void a(String str) {
        this.b = str;
    }

    public Long b() {
        return this.f12223a;
    }

    public void b(String str) {
        this.c = str;
    }

    public String c() {
        return this.c;
    }

    public String toString() {
        return "FailLogInfo{detail='" + this.b + "', time='" + this.c + "'}";
    }
}
