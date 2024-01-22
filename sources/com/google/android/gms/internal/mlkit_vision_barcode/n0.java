package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
/* loaded from: classes9.dex */
public class n0 extends zzcp {

    /* renamed from: a  reason: collision with root package name */
    public Object[] f9455a = new Object[4];
    public int b = 0;
    public boolean c;

    public n0(int i) {
    }

    public final void a(int i) {
        Object[] objArr = this.f9455a;
        int length = objArr.length;
        if (length >= i) {
            if (this.c) {
                this.f9455a = (Object[]) objArr.clone();
                this.c = false;
                return;
            }
            return;
        }
        int i2 = length + (length >> 1) + 1;
        if (i2 < i) {
            int highestOneBit = Integer.highestOneBit(i - 1);
            i2 = highestOneBit + highestOneBit;
        }
        if (i2 < 0) {
            i2 = Integer.MAX_VALUE;
        }
        this.f9455a = Arrays.copyOf(objArr, i2);
        this.c = false;
    }

    public final n0 zza(Object obj) {
        Objects.requireNonNull(obj);
        a(this.b + 1);
        Object[] objArr = this.f9455a;
        int i = this.b;
        this.b = i + 1;
        objArr[i] = obj;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzcp
    public /* bridge */ /* synthetic */ zzcp zzb(Object obj) {
        throw null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final zzcp zzc(Iterable iterable) {
        if (iterable instanceof Collection) {
            a(this.b + iterable.size());
            if (iterable instanceof zzcq) {
                this.b = ((zzcq) iterable).zza(this.f9455a, this.b);
                return this;
            }
        }
        for (Object obj : iterable) {
            zzb(obj);
        }
        return this;
    }
}
