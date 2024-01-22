package com.mappls.sdk.navigation.refresh;

import com.mappls.sdk.navigation.NavigationApplication;
/* loaded from: classes11.dex */
public final class h {

    /* renamed from: a  reason: collision with root package name */
    public NavigationApplication f12937a;
    public b b;
    public e c;
    public g d;
    public a e;

    public h(NavigationApplication navigationApplication) {
        this.f12937a = navigationApplication;
        this.c = new e(navigationApplication);
        this.d = new g(navigationApplication);
        this.b = new b(navigationApplication);
        this.e = new a(navigationApplication);
    }

    public final void a() {
        if (this.f12937a.h().q()) {
            this.c.a();
        }
    }

    public final void a(long j, long j2) {
        this.e.c();
    }

    public final void a(boolean z) {
        this.b.a(z);
    }

    public final void b() {
        if (this.f12937a.h().q()) {
            this.c.b();
            this.d.a();
            this.b.getClass();
            this.e.getClass();
            a.a();
        }
    }

    public final void b(boolean z) {
        this.d.a(Boolean.valueOf(z));
    }

    public final void c() {
        this.b.g();
    }

    public final void d() {
        if (this.f12937a.h().q()) {
            this.c.c();
            this.d.b();
            this.b.c();
            this.e.d();
        }
    }

    public final void e() {
        if (this.f12937a.h().q()) {
            this.c.e();
            if (this.f12937a.h().k().getEvents().size() <= 0) {
                this.d.c();
            }
            if (this.f12937a.h().k().getJunctionViews().size() <= 0) {
                this.b.d();
            }
            this.e.e();
        }
    }
}
