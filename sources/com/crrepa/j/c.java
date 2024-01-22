package com.crrepa.j;

import com.crrepa.ble.conn.bean.CRPBloodPressureInfo;
import com.crrepa.ble.conn.bean.CRPHistoryBloodPressureInfo;
import com.crrepa.ble.conn.listener.CRPBloodPressureChangeListener;
import com.crrepa.f.w0;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/* loaded from: classes9.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    public List<CRPBloodPressureInfo.BpBean> f7732a = new ArrayList();

    public CRPBloodPressureInfo a(List<CRPBloodPressureInfo.BpBean> list) {
        long a2 = com.crrepa.i0.g.a(0);
        int a3 = com.crrepa.i0.g.a() / 1;
        if (list.size() <= a3) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list);
        while (a3 < list.size()) {
            arrayList.set(a3, null);
            a3++;
        }
        return new CRPBloodPressureInfo(a2, CRPBloodPressureInfo.TimeType.TODAY, arrayList, 1);
    }

    public void a(byte[] bArr, CRPBloodPressureChangeListener cRPBloodPressureChangeListener) {
        if (cRPBloodPressureChangeListener == null || com.crrepa.i0.e.e(bArr) || bArr.length != 3) {
            return;
        }
        cRPBloodPressureChangeListener.onBloodPressureChange(com.crrepa.i0.e.a(bArr[1]), com.crrepa.i0.e.a(bArr[2]));
    }

    public CRPBloodPressureInfo b(List<CRPBloodPressureInfo.BpBean> list) {
        long a2 = com.crrepa.i0.g.a(-1);
        int a3 = com.crrepa.i0.g.a() / 1;
        if (list.size() <= a3) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list);
        for (int i = 0; i < a3; i++) {
            arrayList.set(i, null);
        }
        return new CRPBloodPressureInfo(a2, CRPBloodPressureInfo.TimeType.YESTERDAY, arrayList, 1);
    }

    public List<CRPBloodPressureInfo.BpBean> b(byte[] bArr) {
        if (com.crrepa.i0.e.e(bArr)) {
            return null;
        }
        byte b = bArr[0];
        if (b == 0) {
            this.f7732a.clear();
        }
        this.f7732a.addAll(d(bArr));
        if (19 == b) {
            return this.f7732a;
        }
        com.crrepa.m.f.d().a(com.crrepa.f.e.a((byte) (b + 1)));
        return null;
    }

    public List<CRPHistoryBloodPressureInfo> c(byte[] bArr) {
        if (bArr.length < 8) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 2; i < bArr.length; i += 6) {
            byte b = bArr[i];
            byte b2 = bArr[i + 1];
            byte[] bArr2 = new byte[4];
            System.arraycopy(bArr, i + 2, bArr2, 0, 4);
            arrayList.add(new CRPHistoryBloodPressureInfo(new Date(w0.a(com.crrepa.i0.e.d(bArr2) * 1000)), b, b2));
        }
        return arrayList;
    }

    public final List<CRPBloodPressureInfo.BpBean> d(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        if (com.crrepa.i0.e.e(bArr)) {
            return arrayList;
        }
        for (int i = 1; i < bArr.length; i += 2) {
            arrayList.add(new CRPBloodPressureInfo.BpBean(com.crrepa.i0.e.a(bArr[i]), com.crrepa.i0.e.a(bArr[i + 1])));
        }
        return arrayList;
    }
}
