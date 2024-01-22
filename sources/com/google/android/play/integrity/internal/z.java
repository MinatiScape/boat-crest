package com.google.android.play.integrity.internal;

import java.util.Objects;
/* loaded from: classes10.dex */
public final class z implements ac {
    public static final Object c = new Object();

    /* renamed from: a  reason: collision with root package name */
    public volatile ac f10476a;
    public volatile Object b = c;

    public z(ac acVar) {
        this.f10476a = acVar;
    }

    public static ac b(ac acVar) {
        Objects.requireNonNull(acVar);
        return acVar instanceof z ? acVar : new z(acVar);
    }

    @Override // com.google.android.play.integrity.internal.ac
    public final Object a() {
        Object obj = this.b;
        Object obj2 = c;
        if (obj == obj2) {
            synchronized (this) {
                obj = this.b;
                if (obj == obj2) {
                    obj = this.f10476a.a();
                    Object obj3 = this.b;
                    if (obj3 != obj2 && obj3 != obj) {
                        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj3 + " & " + obj + ". This is likely due to a circular dependency.");
                    }
                    this.b = obj;
                    this.f10476a = null;
                }
            }
        }
        return obj;
    }
}
