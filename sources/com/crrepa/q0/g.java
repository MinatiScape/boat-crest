package com.crrepa.q0;

import com.crrepa.n0.r;
import com.crrepa.n0.v;
import com.crrepa.n0.x;
import com.crrepa.n0.y;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
/* loaded from: classes9.dex */
public final class g implements y {
    public final com.crrepa.p0.c h;
    public final boolean i;

    /* loaded from: classes9.dex */
    public final class a<K, V> extends x<Map<K, V>> {

        /* renamed from: a  reason: collision with root package name */
        public final x<K> f7819a;
        public final x<V> b;
        public final com.crrepa.p0.i<? extends Map<K, V>> c;

        public a(com.crrepa.n0.f fVar, Type type, x<K> xVar, Type type2, x<V> xVar2, com.crrepa.p0.i<? extends Map<K, V>> iVar) {
            this.f7819a = new m(fVar, xVar, type);
            this.b = new m(fVar, xVar2, type2);
            this.c = iVar;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.crrepa.n0.x
        /* renamed from: c */
        public void a(com.crrepa.t0.d dVar, Map<K, V> map) throws IOException {
            if (map == null) {
                dVar.k();
            } else if (!g.this.i) {
                dVar.d();
                for (Map.Entry<K, V> entry : map.entrySet()) {
                    dVar.b(String.valueOf(entry.getKey()));
                    this.b.a(dVar, (com.crrepa.t0.d) entry.getValue());
                }
                dVar.f();
            } else {
                ArrayList arrayList = new ArrayList(map.size());
                ArrayList arrayList2 = new ArrayList(map.size());
                int i = 0;
                boolean z = false;
                for (Map.Entry<K, V> entry2 : map.entrySet()) {
                    com.crrepa.n0.l b = this.f7819a.b(entry2.getKey());
                    arrayList.add(b);
                    arrayList2.add(entry2.getValue());
                    z |= b.s() || b.u();
                }
                if (!z) {
                    dVar.d();
                    while (i < arrayList.size()) {
                        dVar.b(d((com.crrepa.n0.l) arrayList.get(i)));
                        this.b.a(dVar, (com.crrepa.t0.d) arrayList2.get(i));
                        i++;
                    }
                    dVar.f();
                    return;
                }
                dVar.c();
                while (i < arrayList.size()) {
                    dVar.c();
                    com.crrepa.p0.k.a((com.crrepa.n0.l) arrayList.get(i), dVar);
                    this.b.a(dVar, (com.crrepa.t0.d) arrayList2.get(i));
                    dVar.e();
                    i++;
                }
                dVar.e();
            }
        }

        public final String d(com.crrepa.n0.l lVar) {
            if (!lVar.v()) {
                if (lVar.t()) {
                    return "null";
                }
                throw new AssertionError();
            }
            r n = lVar.n();
            if (n.y()) {
                return String.valueOf(n.p());
            }
            if (n.x()) {
                return Boolean.toString(n.d());
            }
            if (n.z()) {
                return n.r();
            }
            throw new AssertionError();
        }

        @Override // com.crrepa.n0.x
        /* renamed from: e */
        public Map<K, V> a(com.crrepa.t0.a aVar) throws IOException {
            com.crrepa.t0.c t = aVar.t();
            if (t == com.crrepa.t0.c.NULL) {
                aVar.q();
                return null;
            }
            Map<K, V> a2 = this.c.a();
            if (t == com.crrepa.t0.c.BEGIN_ARRAY) {
                aVar.a();
                while (aVar.i()) {
                    aVar.a();
                    K a3 = this.f7819a.a(aVar);
                    if (a2.put(a3, this.b.a(aVar)) != null) {
                        throw new v("duplicate key: " + a3);
                    }
                    aVar.f();
                }
                aVar.f();
            } else {
                aVar.b();
                while (aVar.i()) {
                    com.crrepa.p0.e.f7803a.a(aVar);
                    K a4 = this.f7819a.a(aVar);
                    if (a2.put(a4, this.b.a(aVar)) != null) {
                        throw new v("duplicate key: " + a4);
                    }
                }
                aVar.g();
            }
            return a2;
        }
    }

    public g(com.crrepa.p0.c cVar, boolean z) {
        this.h = cVar;
        this.i = z;
    }

    @Override // com.crrepa.n0.y
    public <T> x<T> a(com.crrepa.n0.f fVar, com.crrepa.s0.a<T> aVar) {
        Type b = aVar.b();
        if (Map.class.isAssignableFrom(aVar.a())) {
            Type[] b2 = com.crrepa.p0.b.b(b, com.crrepa.p0.b.e(b));
            return new a(fVar, b2[0], b(fVar, b2[0]), b2[1], fVar.a((com.crrepa.s0.a) com.crrepa.s0.a.a(b2[1])), this.h.a(aVar));
        }
        return null;
    }

    public final x<?> b(com.crrepa.n0.f fVar, Type type) {
        return (type == Boolean.TYPE || type == Boolean.class) ? n.f : fVar.a((com.crrepa.s0.a) com.crrepa.s0.a.a(type));
    }
}
