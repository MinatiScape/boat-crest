package com.mappls.sdk.navigation;

import com.google.android.gms.fitness.FitnessActivities;
import com.squareup.otto.Bus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
/* loaded from: classes11.dex */
public final class d {
    public static LinkedHashMap f = new LinkedHashMap();
    public static ArrayList g;
    public static final d h;
    public static final d i;
    public static final d j;
    public static final d k;
    public static final d l;

    /* renamed from: a  reason: collision with root package name */
    public final String f12893a;
    public d b;
    public float c = 10.0f;
    public int d = 50;
    public int e = 30;

    /* loaded from: classes11.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public d f12894a;

        public a() {
        }

        public /* synthetic */ a(int i) {
            this();
        }

        public final a a(float f, int i) {
            this.f12894a.c = f;
            this.f12894a.d = i;
            return this;
        }

        public final a b(int i) {
            this.f12894a.e = i;
            return this;
        }

        public final a c(d dVar) {
            this.f12894a.b = dVar;
            return this;
        }

        public final d d() {
            d.g.add(this.f12894a);
            return this.f12894a;
        }
    }

    static {
        new LinkedHashMap();
        g = new ArrayList();
        h = a(R.string.mappls_app_mode_default, Bus.DEFAULT_IDENTIFIER).a(1.5f, 5).b(30).d();
        d d = a(R.string.mappls_app_mode_car, "car").a(15.3f, 35).b(30).d();
        i = d;
        j = a(R.string.mappls_app_mode_bicycle, "bicycle").a(5.5f, 15).b(15).d();
        d d2 = a(R.string.mappls_app_mode_pedestrian, "pedestrian").a(1.5f, 5).b(30).d();
        k = d2;
        a(R.string.mappls_app_mode_aircraft, "aircraft").a(40.0f, 100).d();
        l = a(R.string.mappls_app_mode_boat, "boat").a(5.5f, 20).d();
        a(R.string.mappls_app_mode_hiking, FitnessActivities.HIKING).a(1.5f, 5).c(d2).d();
        a(R.string.mappls_app_mode_motorcycle, "motorcycle").a(15.3f, 40).c(d).d();
        a(R.string.mappls_app_mode_truck, "truck").a(15.3f, 40).c(d).d();
        a(R.string.mappls_app_mode_bus, "bus").a(15.3f, 40).c(d).d();
        a(R.string.mappls_app_mode_train, "train").a(25.0f, 40).d();
        new ArrayList();
    }

    public d(String str) {
        this.f12893a = str;
    }

    public static a a(int i2, String str) {
        a aVar = new a(0);
        aVar.f12894a = new d(str);
        return aVar;
    }

    public static ArrayList b() {
        return new ArrayList(g);
    }

    public final boolean a(d dVar) {
        return this == dVar || this.b == dVar;
    }

    public final int c() {
        return this.e;
    }

    public final float d() {
        return this.c;
    }

    public final int e() {
        return this.d;
    }

    public final d f() {
        return this.b;
    }

    public final String g() {
        return this.f12893a;
    }

    public final boolean h() {
        return this.c > 10.0f;
    }

    public static void a(d... dVarArr) {
        HashSet hashSet = new HashSet();
        if (dVarArr == null) {
            hashSet.addAll(g);
        } else {
            Collections.addAll(hashSet, dVarArr);
        }
        Iterator it = g.iterator();
        while (it.hasNext()) {
            d dVar = (d) it.next();
            if (hashSet.contains(dVar.b)) {
                hashSet.add(dVar);
            }
        }
        f.put("monitoring", hashSet);
    }

    public static d a(String str, d dVar) {
        Iterator it = g.iterator();
        while (it.hasNext()) {
            d dVar2 = (d) it.next();
            if (dVar2.f12893a.equals(str)) {
                return dVar2;
            }
        }
        return dVar;
    }
}
