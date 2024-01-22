package com.google.android.gms.internal.vision;
/* loaded from: classes10.dex */
public final class e0 extends zzdf<Object> {
    private final transient int offset;
    private final transient int size;
    private final transient Object[] zzmb;

    public e0(Object[] objArr, int i, int i2) {
        this.zzmb = objArr;
        this.offset = i;
        this.size = i2;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzct.zzc(i, this.size);
        return this.zzmb[(i * 2) + this.offset];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }
}
