package com.crrepa.j;

import com.crrepa.ble.conn.bean.CRPHeartRateInfo;
import com.crrepa.ble.conn.bean.CRPHistoryHeartRateInfo;
import com.crrepa.ble.conn.type.CRPHistoryDynamicRateType;
import com.crrepa.f.w0;
import com.crrepa.f.z;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes9.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    public Map<Integer, List<Integer>> f7733a = new HashMap();
    public Map<Integer, Long> b = new HashMap();
    public List<Integer> c = new ArrayList();
    public List<Integer> d = new ArrayList();
    public List<Integer> e = new ArrayList();

    public CRPHeartRateInfo a(List<Integer> list) {
        return new CRPHeartRateInfo(com.crrepa.i0.g.a(0), c(list, 1), 1, CRPHeartRateInfo.HeartRateType.TODAY_HEART_RATE);
    }

    public CRPHeartRateInfo b(List<Integer> list) {
        long a2 = com.crrepa.i0.g.a(-1);
        List<Integer> m = m(list, 1);
        if (m == null) {
            return null;
        }
        return new CRPHeartRateInfo(a2, m, 1, CRPHeartRateInfo.HeartRateType.YESTERDAY_HEART_RATE);
    }

    public final CRPHeartRateInfo b(List<Integer> list, long j) {
        if (list == null || list.size() < 1) {
            return null;
        }
        if (list.get(0).intValue() <= 0) {
            list.remove(0);
        }
        return new CRPHeartRateInfo(w0.a(j), list, 1, CRPHeartRateInfo.HeartRateType.PART_HEART_RATE);
    }

    public CRPHistoryDynamicRateType c(byte[] bArr) {
        int a2 = com.crrepa.i0.e.a(bArr[0]);
        return 32 <= a2 ? CRPHistoryDynamicRateType.THIRD_HEART_RATE : 16 <= a2 ? CRPHistoryDynamicRateType.SECOND_HEART_RATE : CRPHistoryDynamicRateType.FIRST_HEART_RATE;
    }

    public final List<Integer> c(List<Integer> list, int i) {
        int a2 = com.crrepa.i0.g.a() / i;
        if (list.size() <= a2) {
            return list;
        }
        ArrayList arrayList = new ArrayList(list);
        while (a2 < arrayList.size()) {
            arrayList.set(a2, 0);
            a2++;
        }
        return arrayList;
    }

    public List<Integer> d(byte[] bArr) {
        if (com.crrepa.i0.e.e(bArr)) {
            return null;
        }
        byte b = bArr[0];
        if (b == 0) {
            this.e.clear();
        }
        this.e.addAll(j(bArr));
        if (19 == b) {
            return this.e;
        }
        k((byte) (b + 1));
        return null;
    }

    public CRPHeartRateInfo e(byte[] bArr) {
        byte[] bArr2;
        if (com.crrepa.i0.e.e(bArr)) {
            return null;
        }
        int a2 = com.crrepa.i0.e.a(bArr[0]);
        CRPHistoryDynamicRateType c = c(bArr);
        byte value = c.getValue();
        List<Integer> list = this.f7733a.get(Integer.valueOf(value));
        if (list == null) {
            list = new ArrayList<>();
        }
        if (a2 != 0) {
            if (a2 != 1) {
                if (a2 != 2) {
                    switch (a2) {
                        case 16:
                            break;
                        case 17:
                            break;
                        case 18:
                            break;
                        default:
                            switch (a2) {
                                case 32:
                                    break;
                                case 33:
                                    break;
                                case 34:
                                    break;
                                default:
                                    bArr2 = null;
                                    list.addAll(n(bArr2));
                                    this.f7733a.put(Integer.valueOf(value), list);
                                    l(c);
                                    return null;
                            }
                    }
                }
                Long l = this.b.get(Integer.valueOf(value));
                if (l == null) {
                    return null;
                }
                return b(list, l.longValue());
            }
            int length = bArr.length - 1;
            bArr2 = new byte[length];
            System.arraycopy(bArr, 1, bArr2, 0, length);
            list.addAll(n(bArr2));
            this.f7733a.put(Integer.valueOf(value), list);
            l(c);
            return null;
        }
        if (bArr.length < 5) {
            return null;
        }
        list.clear();
        byte[] bArr3 = new byte[4];
        System.arraycopy(bArr, 1, bArr3, 0, 4);
        this.b.put(Integer.valueOf(value), Long.valueOf(com.crrepa.i0.e.d(bArr3) * 1000));
        int length2 = bArr.length - 5;
        byte[] bArr4 = new byte[length2];
        System.arraycopy(bArr, 5, bArr4, 0, length2);
        bArr2 = bArr4;
        list.addAll(n(bArr2));
        this.f7733a.put(Integer.valueOf(value), list);
        l(c);
        return null;
    }

    public List<CRPHistoryHeartRateInfo> f(byte[] bArr) {
        if (bArr.length < 7) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 2; i < bArr.length; i += 5) {
            byte b = bArr[i];
            byte[] bArr2 = new byte[4];
            System.arraycopy(bArr, i + 1, bArr2, 0, 4);
            arrayList.add(new CRPHistoryHeartRateInfo(new Date(w0.a(com.crrepa.i0.e.d(bArr2) * 1000)), b));
        }
        return arrayList;
    }

    public int g(byte[] bArr) {
        byte b = 0;
        if (bArr != null && bArr.length > 0) {
            b = bArr[0];
        }
        return com.crrepa.i0.e.a(b);
    }

    public int h(byte[] bArr) {
        if (com.crrepa.i0.e.e(bArr)) {
            return 0;
        }
        return bArr[0];
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.crrepa.ble.conn.bean.CRPHeartRateInfo i(byte[] r13) {
        /*
            r12 = this;
            boolean r0 = com.crrepa.i0.e.e(r13)
            r1 = 0
            if (r0 == 0) goto L8
            return r1
        L8:
            r0 = 0
            r2 = r13[r0]
            if (r2 != 0) goto L13
            java.util.List<java.lang.Integer> r3 = r12.c
        Lf:
            r3.clear()
            goto L19
        L13:
            r3 = 4
            if (r3 != r2) goto L19
            java.util.List<java.lang.Integer> r3 = r12.d
            goto Lf
        L19:
            java.util.List r13 = r12.j(r13)
            r3 = 7
            r4 = 3
            if (r2 > r4) goto L27
            java.util.List<java.lang.Integer> r5 = r12.c
        L23:
            r5.addAll(r13)
            goto L2c
        L27:
            if (r2 > r3) goto L2c
            java.util.List<java.lang.Integer> r5 = r12.d
            goto L23
        L2c:
            if (r4 != r2) goto L45
            long r7 = com.crrepa.i0.g.a(r0)
            java.util.List<java.lang.Integer> r13 = r12.c
            r0 = 5
            java.util.List r9 = r12.c(r13, r0)
            r12.c = r9
            com.crrepa.ble.conn.bean.CRPHeartRateInfo r13 = new com.crrepa.ble.conn.bean.CRPHeartRateInfo
            com.crrepa.ble.conn.bean.CRPHeartRateInfo$HeartRateType r11 = com.crrepa.ble.conn.bean.CRPHeartRateInfo.HeartRateType.TODAY_HEART_RATE
            r10 = 5
            r6 = r13
            r6.<init>(r7, r9, r10, r11)
            return r13
        L45:
            if (r3 != r2) goto L58
            r13 = -1
            long r1 = com.crrepa.i0.g.a(r13)
            com.crrepa.ble.conn.bean.CRPHeartRateInfo r13 = new com.crrepa.ble.conn.bean.CRPHeartRateInfo
            java.util.List<java.lang.Integer> r3 = r12.d
            com.crrepa.ble.conn.bean.CRPHeartRateInfo$HeartRateType r5 = com.crrepa.ble.conn.bean.CRPHeartRateInfo.HeartRateType.YESTERDAY_HEART_RATE
            r4 = 5
            r0 = r13
            r0.<init>(r1, r3, r4, r5)
            return r13
        L58:
            int r2 = r2 + 1
            byte r13 = (byte) r2
            r12.o(r13)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crrepa.j.g.i(byte[]):com.crrepa.ble.conn.bean.CRPHeartRateInfo");
    }

    public final List<Integer> j(byte[] bArr) {
        List<Integer> n = n(bArr);
        n.remove(0);
        return n;
    }

    public final void k(byte b) {
        com.crrepa.m.f.d().a(z.c(b));
    }

    public final void l(CRPHistoryDynamicRateType cRPHistoryDynamicRateType) {
        com.crrepa.m.f.d().a(z.b(cRPHistoryDynamicRateType.getValue()));
    }

    public final List<Integer> m(List<Integer> list, int i) {
        int a2 = com.crrepa.i0.g.a() / i;
        if (list.size() <= a2) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list);
        for (int i2 = 0; i2 < a2; i2++) {
            arrayList.set(i2, 0);
        }
        return arrayList;
    }

    public final List<Integer> n(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        if (bArr != null && bArr.length >= 1) {
            for (byte b : bArr) {
                int a2 = com.crrepa.i0.e.a(b);
                if (a2 < 40 || a2 > 200) {
                    a2 = 0;
                }
                arrayList.add(Integer.valueOf(a2));
            }
        }
        return arrayList;
    }

    public final void o(byte b) {
        com.crrepa.m.f.d().a(z.d(b));
    }
}
