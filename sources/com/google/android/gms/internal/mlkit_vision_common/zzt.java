package com.google.android.gms.internal.mlkit_vision_common;
/* loaded from: classes8.dex */
public final class zzt {
    public static Object[] a(Object[] objArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (objArr[i2] == null) {
                throw new NullPointerException("at index " + i2);
            }
        }
        return objArr;
    }
}
