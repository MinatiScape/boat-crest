package com.google.android.gms.internal.common;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes7.dex */
public class a extends zzab {

    /* renamed from: a  reason: collision with root package name */
    public Object[] f8625a = new Object[4];
    public int b = 0;
    public boolean c;

    public a(int i) {
    }

    @CanIgnoreReturnValue
    public final a zza(Object obj) {
        Objects.requireNonNull(obj);
        int i = this.b + 1;
        Object[] objArr = this.f8625a;
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
            this.f8625a = Arrays.copyOf(objArr, i2);
            this.c = false;
        } else if (this.c) {
            this.f8625a = (Object[]) objArr.clone();
            this.c = false;
        }
        Object[] objArr2 = this.f8625a;
        int i3 = this.b;
        this.b = i3 + 1;
        objArr2[i3] = obj;
        return this;
    }
}
