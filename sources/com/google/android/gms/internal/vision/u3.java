package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes10.dex */
public final class u3 extends a4 {
    public final /* synthetic */ t3 i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u3(t3 t3Var) {
        super(t3Var, null);
        this.i = t3Var;
    }

    @Override // com.google.android.gms.internal.vision.a4, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator<Map.Entry<K, V>> iterator() {
        return new v3(this.i, null);
    }

    public /* synthetic */ u3(t3 t3Var, s3 s3Var) {
        this(t3Var);
    }
}
