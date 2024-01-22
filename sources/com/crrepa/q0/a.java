package com.crrepa.q0;

import com.crrepa.n0.x;
import com.crrepa.n0.y;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public final class a<E> extends x<Object> {
    public static final y c = new C0354a();

    /* renamed from: a  reason: collision with root package name */
    public final Class<E> f7816a;
    public final x<E> b;

    /* renamed from: com.crrepa.q0.a$a  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public static class C0354a implements y {
        @Override // com.crrepa.n0.y
        public <T> x<T> a(com.crrepa.n0.f fVar, com.crrepa.s0.a<T> aVar) {
            Type b = aVar.b();
            if ((b instanceof GenericArrayType) || ((b instanceof Class) && ((Class) b).isArray())) {
                Type d = com.crrepa.p0.b.d(b);
                return new a(fVar, fVar.a((com.crrepa.s0.a) com.crrepa.s0.a.a(d)), com.crrepa.p0.b.e(d));
            }
            return null;
        }
    }

    public a(com.crrepa.n0.f fVar, x<E> xVar, Class<E> cls) {
        this.b = new m(fVar, xVar, cls);
        this.f7816a = cls;
    }

    @Override // com.crrepa.n0.x
    public Object a(com.crrepa.t0.a aVar) throws IOException {
        if (aVar.t() == com.crrepa.t0.c.NULL) {
            aVar.q();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        aVar.a();
        while (aVar.i()) {
            arrayList.add(this.b.a(aVar));
        }
        aVar.f();
        Object newInstance = Array.newInstance((Class<?>) this.f7816a, arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            Array.set(newInstance, i, arrayList.get(i));
        }
        return newInstance;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.crrepa.n0.x
    public void a(com.crrepa.t0.d dVar, Object obj) throws IOException {
        if (obj == null) {
            dVar.k();
            return;
        }
        dVar.c();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.b.a(dVar, (com.crrepa.t0.d) Array.get(obj, i));
        }
        dVar.e();
    }
}
