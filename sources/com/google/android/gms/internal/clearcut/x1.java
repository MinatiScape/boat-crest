package com.google.android.gms.internal.clearcut;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes7.dex */
public final class x1 extends d2 {
    public final /* synthetic */ u1 i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x1(u1 u1Var) {
        super(u1Var, null);
        this.i = u1Var;
    }

    public /* synthetic */ x1(u1 u1Var, v1 v1Var) {
        this(u1Var);
    }

    @Override // com.google.android.gms.internal.clearcut.d2, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator<Map.Entry<K, V>> iterator() {
        return new w1(this.i, null);
    }
}
