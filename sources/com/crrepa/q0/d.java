package com.crrepa.q0;

import com.crrepa.n0.t;
import com.crrepa.n0.x;
import com.crrepa.n0.y;
/* loaded from: classes9.dex */
public final class d implements y {
    public final com.crrepa.p0.c h;

    public d(com.crrepa.p0.c cVar) {
        this.h = cVar;
    }

    @Override // com.crrepa.n0.y
    public <T> x<T> a(com.crrepa.n0.f fVar, com.crrepa.s0.a<T> aVar) {
        com.crrepa.o0.b bVar = (com.crrepa.o0.b) aVar.a().getAnnotation(com.crrepa.o0.b.class);
        if (bVar == null) {
            return null;
        }
        return (x<T>) b(this.h, fVar, aVar, bVar);
    }

    public x<?> b(com.crrepa.p0.c cVar, com.crrepa.n0.f fVar, com.crrepa.s0.a<?> aVar, com.crrepa.o0.b bVar) {
        x<?> lVar;
        Object a2 = cVar.a(com.crrepa.s0.a.a((Class) bVar.value())).a();
        if (a2 instanceof x) {
            lVar = (x) a2;
        } else if (a2 instanceof y) {
            lVar = ((y) a2).a(fVar, aVar);
        } else {
            boolean z = a2 instanceof t;
            if (!z && !(a2 instanceof com.crrepa.n0.k)) {
                throw new IllegalArgumentException("@JsonAdapter value must be TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer reference.");
            }
            lVar = new l<>(z ? (t) a2 : null, a2 instanceof com.crrepa.n0.k ? (com.crrepa.n0.k) a2 : null, fVar, aVar, null);
        }
        return (lVar == null || !bVar.nullSafe()) ? lVar : lVar.a();
    }
}
