package com.crrepa.o;

import com.crrepa.p.c;
/* loaded from: classes9.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public boolean f7786a;
    public boolean b;

    /* loaded from: classes9.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static a f7787a = new a();
    }

    public a() {
        this.f7786a = false;
        this.b = false;
    }

    public static a a() {
        return b.f7787a;
    }

    public final void b() {
        if (this.f7786a && this.b) {
            c.b().f();
        }
    }

    public void c() {
        this.b = true;
        b();
    }

    public void d() {
        this.f7786a = false;
        this.b = false;
    }

    public void e() {
        this.f7786a = true;
        b();
    }
}
