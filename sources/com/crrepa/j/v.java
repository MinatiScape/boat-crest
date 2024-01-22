package com.crrepa.j;

import com.crrepa.ble.conn.bean.CRPTempInfo;
import com.crrepa.ble.conn.type.CRPTempTimeType;
import com.crrepa.ble.conn.type.CRPTimingTempState;
import com.crrepa.f.u0;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class v {
    public static final byte e = 0;
    public static final byte f = 1;
    public static final byte g = 3;
    public static final byte h = 4;

    /* renamed from: a  reason: collision with root package name */
    public final float[] f7737a = new float[48];
    public List<Float> b = new ArrayList();

    public CRPTempInfo a(List<Float> list) {
        CRPTempInfo cRPTempInfo = new CRPTempInfo(CRPTempTimeType.TODAY, com.crrepa.i0.g.a(0), a(list, 1));
        cRPTempInfo.setMeasureInterval(1);
        return cRPTempInfo;
    }

    public CRPTempTimeType a(int i) {
        return i <= 1 ? CRPTempTimeType.TODAY : CRPTempTimeType.YESTERDAY;
    }

    public List<Float> a(List<Float> list, int i) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        int a2 = com.crrepa.i0.g.a() / i;
        if (list.size() <= a2) {
            return list;
        }
        ArrayList arrayList = new ArrayList(list);
        while (a2 < list.size()) {
            arrayList.set(a2, Float.valueOf(0.0f));
            a2++;
        }
        return arrayList;
    }

    public CRPTempInfo b(List<Float> list) {
        long a2 = com.crrepa.i0.g.a(-1);
        int a3 = com.crrepa.i0.g.a() / 1;
        if (list.size() <= a3) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list);
        for (int i = 0; i < a3; i++) {
            arrayList.set(i, Float.valueOf(0.0f));
        }
        CRPTempInfo cRPTempInfo = new CRPTempInfo(CRPTempTimeType.YESTERDAY, a2, arrayList);
        cRPTempInfo.setMeasureInterval(1);
        return cRPTempInfo;
    }

    public List<Float> b(byte[] bArr) {
        if (com.crrepa.i0.e.e(bArr)) {
            return null;
        }
        byte b = bArr[0];
        if (b == 0) {
            this.b.clear();
        }
        this.b.addAll(f(bArr));
        if (19 == b) {
            return this.b;
        }
        com.crrepa.m.f.d().a(u0.a((int) ((byte) (b + 1))));
        return null;
    }

    public float c(byte[] bArr) {
        float f2 = (((bArr[1] << 8) & 65280) | (bArr[0] & 255)) / 10.0f;
        if (30.0f > f2 || f2 > 45.0f) {
            return 0.0f;
        }
        return f2;
    }

    public float[] d(byte[] bArr) {
        byte b = bArr[0];
        boolean h2 = h(b);
        com.crrepa.i0.c.a("parseTempTimingMeasureResult index: " + ((int) b));
        com.crrepa.i0.c.a("parseTempTimingMeasureResult end: " + h2);
        int i = !h2 ? 24 : 0;
        int length = bArr.length - 1;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 1, bArr2, 0, length);
        for (int i2 = 0; i2 < length - 1; i2 += 2) {
            byte[] bArr3 = new byte[2];
            System.arraycopy(bArr2, i2, bArr3, 0, 2);
            this.f7737a[(i2 / 2) + i] = c(bArr3);
        }
        if (h2) {
            return this.f7737a;
        }
        g(b);
        return null;
    }

    public CRPTimingTempState e(byte[] bArr) {
        return bArr[0] == 1 ? CRPTimingTempState.ENABLE : com.crrepa.i0.e.a(bArr[0]) == 255 ? CRPTimingTempState.NONE : CRPTimingTempState.DISABLE;
    }

    public final List<Float> f(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 1; i < bArr.length - 1; i += 2) {
            byte[] bArr2 = new byte[2];
            System.arraycopy(bArr, i, bArr2, 0, 2);
            arrayList.add(Float.valueOf(c(bArr2)));
        }
        return arrayList;
    }

    public final void g(byte b) {
        com.crrepa.m.f.d().a(u0.a((byte) (b + 1)));
    }

    public final boolean h(int i) {
        int i2 = i % 2;
        return 12 <= com.crrepa.i0.h.a() ? i2 == 1 : i2 == 0;
    }
}
