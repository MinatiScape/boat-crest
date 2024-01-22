package com.apex.bluetooth.core;

import androidx.annotation.NonNull;
/* loaded from: classes.dex */
public final class i {

    /* renamed from: a  reason: collision with root package name */
    public static int f2187a;

    public static int a(@NonNull byte[] bArr, int i, boolean z) {
        int i2;
        if (z) {
            i2 = 65535;
            for (int i3 = 4; i3 < i; i3++) {
                int i4 = i2 & 65535;
                int i5 = ((i4 << 8) | (i4 >> 8)) ^ (bArr[i3] & 255);
                int i6 = i5 ^ ((i5 & 255) >> 4);
                int i7 = i6 ^ (((i6 & 65535) << 8) << 4);
                i2 = i7 ^ (((i7 & 255) << 4) << 1);
            }
        } else {
            i2 = 65535;
            for (int i8 = 0; i8 < i; i8++) {
                int i9 = i2 & 65535;
                int i10 = ((i9 << 8) | (i9 >> 8)) ^ (bArr[i8] & 255);
                int i11 = i10 ^ ((i10 & 255) >> 4);
                int i12 = i11 ^ (((i11 & 65535) << 8) << 4);
                i2 = i12 ^ (((i12 & 255) << 4) << 1);
            }
        }
        return i2;
    }
}
