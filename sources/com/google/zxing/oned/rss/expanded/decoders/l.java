package com.google.zxing.oned.rss.expanded.decoders;
/* loaded from: classes11.dex */
public final class l {

    /* renamed from: a  reason: collision with root package name */
    public int f11839a = 0;
    public a b = a.NUMERIC;

    /* loaded from: classes11.dex */
    public enum a {
        NUMERIC,
        ALPHA,
        ISO_IEC_646
    }

    public int a() {
        return this.f11839a;
    }

    public void b(int i) {
        this.f11839a += i;
    }

    public boolean c() {
        return this.b == a.ALPHA;
    }

    public boolean d() {
        return this.b == a.ISO_IEC_646;
    }

    public void e() {
        this.b = a.ALPHA;
    }

    public void f() {
        this.b = a.ISO_IEC_646;
    }

    public void g() {
        this.b = a.NUMERIC;
    }

    public void h(int i) {
        this.f11839a = i;
    }
}
