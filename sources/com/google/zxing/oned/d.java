package com.google.zxing.oned;

import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.BitArray;
/* loaded from: classes11.dex */
public final class d {
    public static final int[] c = {1, 1, 2};

    /* renamed from: a  reason: collision with root package name */
    public final b f11831a = new b();
    public final c b = new c();

    public Result a(int i, BitArray bitArray, int i2) throws NotFoundException {
        int[] d = UPCEANReader.d(bitArray, i2, false, c);
        try {
            return this.b.b(i, bitArray, d);
        } catch (ReaderException unused) {
            return this.f11831a.b(i, bitArray, d);
        }
    }
}
