package com.crrepa.q0;

import com.crrepa.n0.x;
import com.crrepa.q0.i;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
/* loaded from: classes9.dex */
public final class m<T> extends x<T> {

    /* renamed from: a  reason: collision with root package name */
    public final com.crrepa.n0.f f7828a;
    public final x<T> b;
    public final Type c;

    public m(com.crrepa.n0.f fVar, x<T> xVar, Type type) {
        this.f7828a = fVar;
        this.b = xVar;
        this.c = type;
    }

    @Override // com.crrepa.n0.x
    public T a(com.crrepa.t0.a aVar) throws IOException {
        return this.b.a(aVar);
    }

    @Override // com.crrepa.n0.x
    public void a(com.crrepa.t0.d dVar, T t) throws IOException {
        x<T> xVar = this.b;
        Type c = c(this.c, t);
        if (c != this.c) {
            xVar = this.f7828a.a((com.crrepa.s0.a) com.crrepa.s0.a.a(c));
            if (xVar instanceof i.b) {
                x<T> xVar2 = this.b;
                if (!(xVar2 instanceof i.b)) {
                    xVar = xVar2;
                }
            }
        }
        xVar.a(dVar, (com.crrepa.t0.d) t);
    }

    public final Type c(Type type, Object obj) {
        return obj != null ? (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) ? obj.getClass() : type : type;
    }
}
