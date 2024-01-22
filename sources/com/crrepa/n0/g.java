package com.crrepa.n0;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes9.dex */
public final class g {
    public String h;

    /* renamed from: a  reason: collision with root package name */
    public com.crrepa.p0.d f7779a = com.crrepa.p0.d.h;
    public w b = w.f7784a;
    public e c = d.f7774a;
    public final Map<Type, h<?>> d = new HashMap();
    public final List<y> e = new ArrayList();
    public final List<y> f = new ArrayList();
    public boolean g = false;
    public int i = 2;
    public int j = 2;
    public boolean k = false;
    public boolean l = false;
    public boolean m = true;
    public boolean n = false;
    public boolean o = false;
    public boolean p = false;

    public f a() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.e);
        Collections.reverse(arrayList);
        arrayList.addAll(this.f);
        b(this.h, this.i, this.j, arrayList);
        return new f(this.f7779a, this.c, this.d, this.g, this.k, this.o, this.m, this.n, this.p, this.l, this.b, arrayList);
    }

    public g a(double d) {
        this.f7779a = this.f7779a.a(d);
        return this;
    }

    public g a(int i) {
        this.i = i;
        this.h = null;
        return this;
    }

    public g a(int i, int i2) {
        this.i = i;
        this.j = i2;
        this.h = null;
        return this;
    }

    public g a(b bVar) {
        this.f7779a = this.f7779a.a(bVar, false, true);
        return this;
    }

    public g a(d dVar) {
        this.c = dVar;
        return this;
    }

    public g a(e eVar) {
        this.c = eVar;
        return this;
    }

    public g a(w wVar) {
        this.b = wVar;
        return this;
    }

    public g a(y yVar) {
        this.e.add(yVar);
        return this;
    }

    public g a(Class<?> cls, Object obj) {
        boolean z = obj instanceof t;
        com.crrepa.p0.a.a(z || (obj instanceof k) || (obj instanceof x));
        if ((obj instanceof k) || z) {
            this.f.add(0, com.crrepa.q0.l.a(cls, obj));
        }
        if (obj instanceof x) {
            this.e.add(com.crrepa.q0.n.b(cls, (x) obj));
        }
        return this;
    }

    public g a(String str) {
        this.h = str;
        return this;
    }

    public g a(Type type, Object obj) {
        boolean z = obj instanceof t;
        com.crrepa.p0.a.a(z || (obj instanceof k) || (obj instanceof h) || (obj instanceof x));
        if (obj instanceof h) {
            this.d.put(type, (h) obj);
        }
        if (z || (obj instanceof k)) {
            this.e.add(com.crrepa.q0.l.b(com.crrepa.s0.a.a(type), obj));
        }
        if (obj instanceof x) {
            this.e.add(com.crrepa.q0.n.a(com.crrepa.s0.a.a(type), (x) obj));
        }
        return this;
    }

    public g a(int... iArr) {
        this.f7779a = this.f7779a.a(iArr);
        return this;
    }

    public g a(b... bVarArr) {
        for (b bVar : bVarArr) {
            this.f7779a = this.f7779a.a(bVar, true, true);
        }
        return this;
    }

    public g b() {
        this.m = false;
        return this;
    }

    public g b(b bVar) {
        this.f7779a = this.f7779a.a(bVar, true, false);
        return this;
    }

    public final void b(String str, int i, int i2, List<y> list) {
        a aVar;
        if (str != null && !"".equals(str.trim())) {
            aVar = new a(str);
        } else if (i == 2 || i2 == 2) {
            return;
        } else {
            aVar = new a(i, i2);
        }
        list.add(com.crrepa.q0.l.a(com.crrepa.s0.a.a(Date.class), aVar));
        list.add(com.crrepa.q0.l.a(com.crrepa.s0.a.a(Timestamp.class), aVar));
        list.add(com.crrepa.q0.l.a(com.crrepa.s0.a.a(java.sql.Date.class), aVar));
    }

    public g c() {
        this.f7779a = this.f7779a.b();
        return this;
    }

    public g d() {
        this.k = true;
        return this;
    }

    public g e() {
        this.f7779a = this.f7779a.c();
        return this;
    }

    public g f() {
        this.o = true;
        return this;
    }

    public g g() {
        this.g = true;
        return this;
    }

    public g h() {
        this.l = true;
        return this;
    }

    public g i() {
        this.p = true;
        return this;
    }

    public g j() {
        this.n = true;
        return this;
    }
}
