package com.crrepa.q0;

import com.crrepa.n0.x;
import com.crrepa.n0.y;
import java.io.IOException;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public final class h extends x<Object> {
    public static final y b = new a();

    /* renamed from: a  reason: collision with root package name */
    public final com.crrepa.n0.f f7820a;

    /* loaded from: classes9.dex */
    public static class a implements y {
        @Override // com.crrepa.n0.y
        public <T> x<T> a(com.crrepa.n0.f fVar, com.crrepa.s0.a<T> aVar) {
            if (aVar.a() == Object.class) {
                return new h(fVar);
            }
            return null;
        }
    }

    /* loaded from: classes9.dex */
    public static /* synthetic */ class b {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f7821a;

        static {
            int[] iArr = new int[com.crrepa.t0.c.values().length];
            f7821a = iArr;
            try {
                iArr[com.crrepa.t0.c.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7821a[com.crrepa.t0.c.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7821a[com.crrepa.t0.c.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7821a[com.crrepa.t0.c.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f7821a[com.crrepa.t0.c.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f7821a[com.crrepa.t0.c.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public h(com.crrepa.n0.f fVar) {
        this.f7820a = fVar;
    }

    @Override // com.crrepa.n0.x
    public Object a(com.crrepa.t0.a aVar) throws IOException {
        switch (b.f7821a[aVar.t().ordinal()]) {
            case 1:
                ArrayList arrayList = new ArrayList();
                aVar.a();
                while (aVar.i()) {
                    arrayList.add(a(aVar));
                }
                aVar.f();
                return arrayList;
            case 2:
                com.crrepa.p0.h hVar = new com.crrepa.p0.h();
                aVar.b();
                while (aVar.i()) {
                    hVar.put(aVar.p(), a(aVar));
                }
                aVar.g();
                return hVar;
            case 3:
                return aVar.r();
            case 4:
                return Double.valueOf(aVar.m());
            case 5:
                return Boolean.valueOf(aVar.l());
            case 6:
                aVar.q();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    @Override // com.crrepa.n0.x
    public void a(com.crrepa.t0.d dVar, Object obj) throws IOException {
        if (obj == null) {
            dVar.k();
            return;
        }
        x a2 = this.f7820a.a((Class) obj.getClass());
        if (!(a2 instanceof h)) {
            a2.a(dVar, (com.crrepa.t0.d) obj);
            return;
        }
        dVar.d();
        dVar.f();
    }
}
