package com.google.android.gms.internal.mlkit_vision_common;

import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes8.dex */
public class v4 extends zzk {

    /* renamed from: a  reason: collision with root package name */
    public Object[] f9759a = new Object[4];
    public int b = 0;
    public boolean c;

    public v4(int i) {
    }

    public final void a(int i) {
        Object[] objArr = this.f9759a;
        int length = objArr.length;
        if (length >= i) {
            if (this.c) {
                this.f9759a = (Object[]) objArr.clone();
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
        this.f9759a = Arrays.copyOf(objArr, i2);
        this.c = false;
    }

    public final v4 zza(Object obj) {
        Objects.requireNonNull(obj);
        a(this.b + 1);
        Object[] objArr = this.f9759a;
        int i = this.b;
        this.b = i + 1;
        objArr[i] = obj;
        return this;
    }
}
