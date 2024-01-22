package com.crrepa.p;

import com.crrepa.m.d;
import com.crrepa.m.e;
import com.crrepa.m.f;
/* loaded from: classes9.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    public com.crrepa.p.b f7790a;
    public boolean b;
    public long c;
    public e d;

    /* loaded from: classes9.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final c f7791a = new c();
    }

    public c() {
        this.f7790a = new com.crrepa.p.b();
        this.b = true;
        this.c = 0L;
    }

    public static c b() {
        return b.f7791a;
    }

    public void a() {
        a(true);
        this.f7790a.a();
    }

    public void a(e eVar) {
        this.d = eVar;
    }

    public void a(com.crrepa.p.a aVar) {
        this.f7790a.a(aVar);
        c();
    }

    public void a(boolean z) {
        com.crrepa.i0.c.c("setMessageHandleComplete: " + z);
        this.b = z;
    }

    public final void b(byte[] bArr) {
        int b2 = com.crrepa.i0.e.b(bArr[0], bArr[1]);
        com.crrepa.i0.c.a("requestMtu: " + b2);
        com.crrepa.l.a.b().a().requestMtu(b2);
    }

    public final synchronized void c() {
        if (!this.f7790a.d()) {
            com.crrepa.i0.c.c("message queue is null");
            return;
        }
        com.crrepa.i0.c.c("isMessageHandleComplete: " + d());
        if (!d()) {
            if (e()) {
                g();
                f();
            }
            return;
        }
        com.crrepa.p.a c = this.f7790a.c();
        if (c == null) {
            com.crrepa.i0.c.c("ble message is null");
            return;
        }
        a(false);
        int b2 = c.b();
        byte[] a2 = c.a();
        com.crrepa.i0.c.c("message type: " + b2);
        com.crrepa.i0.c.c("message content: " + com.crrepa.i0.e.c(a2));
        switch (b2) {
            case 0:
            case 8:
                f.d().e(a2);
                break;
            case 1:
                f.d().f(a2);
                break;
            case 2:
                f.d().g(a2);
                break;
            case 3:
                this.d.a(a2[0]);
                break;
            case 4:
                d.c().a(a2[0]);
                break;
            case 5:
                b(a2);
                break;
            case 6:
                com.crrepa.v.a.d().c(a2);
                break;
            case 7:
                com.crrepa.v.a.d().d(a2);
                break;
        }
        i();
    }

    public boolean d() {
        return this.b;
    }

    public final boolean e() {
        long currentTimeMillis = System.currentTimeMillis() - this.c;
        com.crrepa.i0.c.c("period: " + currentTimeMillis);
        return currentTimeMillis >= 5000;
    }

    public void f() {
        this.f7790a.e();
        h();
    }

    public void g() {
        f.d().f();
    }

    public final void h() {
        a(true);
        c();
    }

    public final void i() {
        this.c = System.currentTimeMillis();
    }
}
