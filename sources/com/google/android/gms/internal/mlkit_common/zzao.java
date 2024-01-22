package com.google.android.gms.internal.mlkit_common;

import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes8.dex */
public final class zzao extends f {
    public zzao() {
        super(4);
    }

    public final zzao zzb(Object obj) {
        Objects.requireNonNull(obj);
        int i = this.b + 1;
        Object[] objArr = this.f9190a;
        int length = objArr.length;
        if (length < i) {
            this.f9190a = Arrays.copyOf(objArr, zzam.a(length, i));
            this.c = false;
        } else if (this.c) {
            this.f9190a = (Object[]) objArr.clone();
            this.c = false;
        }
        Object[] objArr2 = this.f9190a;
        int i2 = this.b;
        this.b = i2 + 1;
        objArr2[i2] = obj;
        return this;
    }

    public final zzar zzc() {
        this.c = true;
        return zzar.zzg(this.f9190a, this.b);
    }
}
