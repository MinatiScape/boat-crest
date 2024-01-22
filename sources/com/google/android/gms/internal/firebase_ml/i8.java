package com.google.android.gms.internal.firebase_ml;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes7.dex */
public final class i8 extends o8 {
    public final /* synthetic */ h8 i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i8(h8 h8Var) {
        super(h8Var, null);
        this.i = h8Var;
    }

    @Override // com.google.android.gms.internal.firebase_ml.o8, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator<Map.Entry<K, V>> iterator() {
        return new j8(this.i, null);
    }

    public /* synthetic */ i8(h8 h8Var, g8 g8Var) {
        this(h8Var);
    }
}
