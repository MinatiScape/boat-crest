package com.crrepa.j;

import androidx.annotation.NonNull;
import com.crrepa.ble.conn.bean.CRPStepsCategoryInfo;
import com.crrepa.f.s0;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
/* loaded from: classes9.dex */
public class u {

    /* renamed from: a  reason: collision with root package name */
    public List<Integer> f7736a = new ArrayList();
    public List<Integer> b = new ArrayList();

    @NonNull
    public final CRPStepsCategoryInfo a(int i, List<Integer> list) {
        return new CRPStepsCategoryInfo(i, 30, list);
    }

    public CRPStepsCategoryInfo b(byte[] bArr) {
        List<Integer> list;
        if (com.crrepa.i0.e.e(bArr)) {
            return null;
        }
        int i = 0;
        byte b = bArr[0];
        d(b);
        e(b, c(bArr));
        if (1 == b) {
            list = b(this.f7736a);
        } else if (3 != b) {
            f((byte) (b + 1));
            return null;
        } else {
            list = this.b;
            i = 2;
        }
        return a(i, list);
    }

    public final List<Integer> b(List<Integer> list) {
        Calendar calendar = Calendar.getInstance();
        int i = ((calendar.get(11) * 60) + calendar.get(12)) / 30;
        com.crrepa.i0.c.a("filterTodayStepsCategory index: " + i);
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 < list.size()) {
            arrayList.add(i2 < i ? list.get(i2) : 0);
            i2++;
        }
        return arrayList;
    }

    public final List<Integer> c(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        for (int i = 1; i < bArr.length; i += 2) {
            arrayList.add(Integer.valueOf(com.crrepa.i0.e.b(bArr[i + 1], bArr[i])));
        }
        return arrayList;
    }

    public final void d(byte b) {
        List<Integer> list;
        if (b == 0) {
            list = this.f7736a;
        } else if (2 != b) {
            return;
        } else {
            list = this.b;
        }
        list.clear();
    }

    public final void e(byte b, List<Integer> list) {
        List<Integer> list2;
        if (b <= 1) {
            list2 = this.f7736a;
        } else if (b > 3) {
            return;
        } else {
            list2 = this.b;
        }
        list2.addAll(list);
    }

    public final void f(int i) {
        com.crrepa.m.f.d().a(s0.a(i));
    }
}
