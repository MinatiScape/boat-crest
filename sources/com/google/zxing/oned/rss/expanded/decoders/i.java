package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;
/* loaded from: classes11.dex */
public abstract class i extends h {
    public i(BitArray bitArray) {
        super(bitArray);
    }

    public abstract void d(StringBuilder sb, int i);

    public abstract int e(int i);

    public final void f(StringBuilder sb, int i, int i2) {
        int f = getGeneralDecoder().f(i, i2);
        d(sb, f);
        int e = e(f);
        int i3 = 100000;
        for (int i4 = 0; i4 < 5; i4++) {
            if (e / i3 == 0) {
                sb.append('0');
            }
            i3 /= 10;
        }
        sb.append(e);
    }
}
