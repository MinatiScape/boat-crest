package com.google.android.gms.internal.mlkit_common;

import java.util.Arrays;
/* loaded from: classes8.dex */
public final class zzat {

    /* renamed from: a  reason: collision with root package name */
    public Object[] f9332a = new Object[8];
    public int b = 0;
    public i c;

    public final zzat zza(Object obj, Object obj2) {
        int i = this.b + 1;
        Object[] objArr = this.f9332a;
        int length = objArr.length;
        int i2 = i + i;
        if (i2 > length) {
            this.f9332a = Arrays.copyOf(objArr, zzam.a(length, i2));
        }
        d.a(obj, obj2);
        Object[] objArr2 = this.f9332a;
        int i3 = this.b;
        int i4 = i3 + i3;
        objArr2[i4] = obj;
        objArr2[i4 + 1] = obj2;
        this.b = i3 + 1;
        return this;
    }

    public final zzau zzb() {
        i iVar = this.c;
        if (iVar == null) {
            o zzg = o.zzg(this.b, this.f9332a, this);
            i iVar2 = this.c;
            if (iVar2 == null) {
                return zzg;
            }
            throw iVar2.a();
        }
        throw iVar.a();
    }
}
