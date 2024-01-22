package com.google.zxing.common.reedsolomon;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public final class ReedSolomonEncoder {

    /* renamed from: a  reason: collision with root package name */
    public final GenericGF f11796a;
    public final List<a> b;

    public ReedSolomonEncoder(GenericGF genericGF) {
        this.f11796a = genericGF;
        ArrayList arrayList = new ArrayList();
        this.b = arrayList;
        arrayList.add(new a(genericGF, new int[]{1}));
    }

    public final a a(int i) {
        if (i >= this.b.size()) {
            List<a> list = this.b;
            a aVar = list.get(list.size() - 1);
            for (int size = this.b.size(); size <= i; size++) {
                GenericGF genericGF = this.f11796a;
                aVar = aVar.i(new a(genericGF, new int[]{1, genericGF.c((size - 1) + genericGF.getGeneratorBase())}));
                this.b.add(aVar);
            }
        }
        return this.b.get(i);
    }

    public void encode(int[] iArr, int i) {
        if (i != 0) {
            int length = iArr.length - i;
            if (length > 0) {
                a a2 = a(i);
                int[] iArr2 = new int[length];
                System.arraycopy(iArr, 0, iArr2, 0, length);
                int[] e = new a(this.f11796a, iArr2).j(i, 1).b(a2)[1].e();
                int length2 = i - e.length;
                for (int i2 = 0; i2 < length2; i2++) {
                    iArr[length + i2] = 0;
                }
                System.arraycopy(e, 0, iArr, length + length2, e.length);
                return;
            }
            throw new IllegalArgumentException("No data bytes provided");
        }
        throw new IllegalArgumentException("No error correction bytes");
    }
}
