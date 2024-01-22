package com.mappls.sdk.navigation;

import com.mappls.sdk.services.api.directions.DirectionsCriteria;
/* loaded from: classes11.dex */
public final class v {

    /* renamed from: a  reason: collision with root package name */
    public boolean f13044a;
    public boolean b;
    public String c;
    public String d;
    public String e;
    public String f;

    /* loaded from: classes11.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public boolean f13045a;
        public boolean b;
        public boolean c;
        public boolean d;
        public String e;
        public String f;
        public String g;
        public String h;

        public a() {
        }

        public /* synthetic */ a(int i) {
            this();
        }

        public final a a() {
            this.c = false;
            return this;
        }

        public final a a(String str) {
            this.h = str;
            return this;
        }

        public final a b(String str) {
            this.g = str;
            return this;
        }

        public final v b() {
            return new v(this, 0);
        }

        public final a c() {
            this.f13045a = true;
            return this;
        }

        public final a d() {
            this.e = "driving";
            return this;
        }

        public final a e() {
            this.f = DirectionsCriteria.RESOURCE_ROUTE_ETA;
            return this;
        }

        public final a f() {
            this.d = true;
            return this;
        }

        public final a g() {
            this.b = true;
            return this;
        }
    }

    public v(a aVar) {
        aVar.f13045a;
        this.f13044a = aVar.b;
        this.b = aVar.c;
        aVar.d;
        this.c = aVar.e;
        this.d = aVar.f;
        this.e = aVar.g;
        this.f = aVar.h;
        aVar.getClass();
        aVar.getClass();
        aVar.getClass();
    }

    public /* synthetic */ v(a aVar, int i) {
        this(aVar);
    }

    public static a a() {
        return new a(0);
    }
}
