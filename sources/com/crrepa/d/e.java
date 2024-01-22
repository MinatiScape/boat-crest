package com.crrepa.d;

import com.crrepa.j.n;
/* loaded from: classes9.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    public com.crrepa.d.a f7701a;
    public boolean b;

    /* loaded from: classes9.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final e f7702a = new e();
    }

    public e() {
        this.b = false;
    }

    public static e a() {
        return b.f7702a;
    }

    public void a(com.crrepa.d.a aVar) {
        this.f7701a = aVar;
        this.b = false;
    }

    public void a(String str) {
        if (this.f7701a == null || this.b) {
            return;
        }
        this.b = true;
        com.crrepa.i0.c.a("onProtocolVersion: " + str);
        this.f7701a.a(n.a(str));
    }
}
