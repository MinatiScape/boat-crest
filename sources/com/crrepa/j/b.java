package com.crrepa.j;

import com.crrepa.ble.conn.bean.CRPBloodOxygenInfo;
import com.crrepa.ble.conn.bean.CRPHistoryBloodOxygenInfo;
import com.crrepa.ble.conn.type.CRPBloodOxygenTimeType;
import com.crrepa.f.w0;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/* loaded from: classes9.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public List<Integer> f7731a = new ArrayList();
    public List<Integer> b = new ArrayList();
    public List<Integer> c = new ArrayList();

    public CRPBloodOxygenInfo a(List<Integer> list) {
        return new CRPBloodOxygenInfo(com.crrepa.i0.g.a(0), CRPBloodOxygenTimeType.TODAY, b(list, 1), 1);
    }

    public CRPBloodOxygenInfo b(List<Integer> list) {
        long a2 = com.crrepa.i0.g.a(-1);
        int a3 = com.crrepa.i0.g.a() / 1;
        if (list.size() <= a3) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list);
        for (int i = 0; i < a3; i++) {
            arrayList.set(i, 0);
        }
        return new CRPBloodOxygenInfo(a2, CRPBloodOxygenTimeType.YESTERDAY, arrayList, 1);
    }

    public final List<Integer> b(List<Integer> list, int i) {
        int a2 = com.crrepa.i0.g.a() / i;
        if (list.size() <= a2) {
            return list;
        }
        ArrayList arrayList = new ArrayList(list);
        while (a2 < list.size()) {
            arrayList.set(a2, 0);
            a2++;
        }
        return arrayList;
    }

    public List<Integer> b(byte[] bArr) {
        if (com.crrepa.i0.e.e(bArr)) {
            return null;
        }
        byte b = bArr[0];
        if (b == 0) {
            this.c.clear();
        }
        this.c.addAll(e(bArr));
        if (19 == b) {
            return this.c;
        }
        com.crrepa.m.f.d().a(com.crrepa.f.d.c((byte) (b + 1)));
        return null;
    }

    public List<CRPHistoryBloodOxygenInfo> c(byte[] bArr) {
        if (bArr.length < 7) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 2; i < bArr.length; i += 5) {
            byte b = bArr[i];
            byte[] bArr2 = new byte[4];
            System.arraycopy(bArr, i + 1, bArr2, 0, 4);
            arrayList.add(new CRPHistoryBloodOxygenInfo(new Date(w0.a(com.crrepa.i0.e.d(bArr2) * 1000)), b));
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.crrepa.ble.conn.bean.CRPBloodOxygenInfo d(byte[] r10) {
        /*
            r9 = this;
            boolean r0 = com.crrepa.i0.e.e(r10)
            r1 = 0
            if (r0 == 0) goto L8
            return r1
        L8:
            r0 = 0
            r2 = r10[r0]
            com.crrepa.ble.conn.type.CRPBloodOxygenTimeType r6 = com.crrepa.ble.conn.type.CRPBloodOxygenTimeType.TODAY
            int r3 = r6.getValue()
            if (r3 != r2) goto L19
            java.util.List<java.lang.Integer> r3 = r9.f7731a
        L15:
            r3.clear()
            goto L24
        L19:
            com.crrepa.ble.conn.type.CRPBloodOxygenTimeType r3 = com.crrepa.ble.conn.type.CRPBloodOxygenTimeType.YESTERDAY
            int r3 = r3.getValue()
            if (r3 != r2) goto L24
            java.util.List<java.lang.Integer> r3 = r9.b
            goto L15
        L24:
            java.util.List r10 = r9.e(r10)
            r3 = 7
            r4 = 3
            if (r2 > r4) goto L32
            java.util.List<java.lang.Integer> r5 = r9.f7731a
        L2e:
            r5.addAll(r10)
            goto L37
        L32:
            if (r2 > r3) goto L37
            java.util.List<java.lang.Integer> r5 = r9.b
            goto L2e
        L37:
            if (r4 != r2) goto L4e
            long r4 = com.crrepa.i0.g.a(r0)
            java.util.List<java.lang.Integer> r10 = r9.f7731a
            r0 = 5
            java.util.List r7 = r9.b(r10, r0)
            r9.f7731a = r7
            com.crrepa.ble.conn.bean.CRPBloodOxygenInfo r10 = new com.crrepa.ble.conn.bean.CRPBloodOxygenInfo
            r8 = 5
            r3 = r10
            r3.<init>(r4, r6, r7, r8)
            return r10
        L4e:
            if (r3 != r2) goto L61
            r10 = -1
            long r1 = com.crrepa.i0.g.a(r10)
            com.crrepa.ble.conn.bean.CRPBloodOxygenInfo r10 = new com.crrepa.ble.conn.bean.CRPBloodOxygenInfo
            com.crrepa.ble.conn.type.CRPBloodOxygenTimeType r3 = com.crrepa.ble.conn.type.CRPBloodOxygenTimeType.YESTERDAY
            java.util.List<java.lang.Integer> r4 = r9.b
            r5 = 5
            r0 = r10
            r0.<init>(r1, r3, r4, r5)
            return r10
        L61:
            int r2 = r2 + 1
            byte r10 = (byte) r2
            byte[] r10 = com.crrepa.f.d.b(r10)
            com.crrepa.m.f r0 = com.crrepa.m.f.d()
            r0.a(r10)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crrepa.j.b.d(byte[]):com.crrepa.ble.conn.bean.CRPBloodOxygenInfo");
    }

    public final List<Integer> e(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        if (com.crrepa.i0.e.e(bArr)) {
            return arrayList;
        }
        for (byte b : bArr) {
            int a2 = com.crrepa.i0.e.a(b);
            if (a2 < 90 || a2 > 100) {
                a2 = 0;
            }
            arrayList.add(Integer.valueOf(a2));
        }
        arrayList.remove(0);
        return arrayList;
    }
}
