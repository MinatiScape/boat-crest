package com.google.android.gms.internal.mlkit_code_scanner;

import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes8.dex */
public final class zzm extends r5 {
    public zzm() {
        super(4);
    }

    public final zzm zza(Object obj) {
        Objects.requireNonNull(obj);
        int i = this.b + 1;
        Object[] objArr = this.f9087a;
        int length = objArr.length;
        if (length < i) {
            int i2 = length + (length >> 1) + 1;
            if (i2 < i) {
                int highestOneBit = Integer.highestOneBit(i - 1);
                i2 = highestOneBit + highestOneBit;
            }
            if (i2 < 0) {
                i2 = Integer.MAX_VALUE;
            }
            this.f9087a = Arrays.copyOf(objArr, i2);
            this.c = false;
        } else if (this.c) {
            this.f9087a = (Object[]) objArr.clone();
            this.c = false;
        }
        Object[] objArr2 = this.f9087a;
        int i3 = this.b;
        this.b = i3 + 1;
        objArr2[i3] = obj;
        return this;
    }

    public final zzp zzb() {
        this.c = true;
        return zzp.zzg(this.f9087a, this.b);
    }
}
