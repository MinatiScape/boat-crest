package com.google.android.gms.internal.vision;

import java.util.AbstractMap;
import java.util.Map;
/* JADX INFO: Add missing generic type declarations: [V, K] */
/* loaded from: classes10.dex */
public final class d0<K, V> extends zzdf<Map.Entry<K, V>> {
    private final /* synthetic */ a0 zzmf;

    public d0(a0 a0Var) {
        this.zzmf = a0Var;
    }

    @Override // java.util.List
    public final /* synthetic */ Object get(int i) {
        int i2;
        Object[] objArr;
        Object[] objArr2;
        i2 = this.zzmf.size;
        zzct.zzc(i, i2);
        objArr = this.zzmf.zzmb;
        int i3 = i * 2;
        a0 a0Var = this.zzmf;
        Object obj = objArr[i3];
        objArr2 = a0Var.zzmb;
        return new AbstractMap.SimpleImmutableEntry(obj, objArr2[i3 + 1]);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        int i;
        i = this.zzmf.size;
        return i;
    }
}
