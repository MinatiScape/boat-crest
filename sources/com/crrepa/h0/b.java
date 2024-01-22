package com.crrepa.h0;

import com.crrepa.i0.e;
import java.util.List;
/* loaded from: classes9.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public List<Integer> f7726a;
    public List<Integer> b;
    public List<Integer> c;
    public int d;
    public int e;

    public b(List<Integer> list, List<Integer> list2, List<Integer> list3) {
        this.f7726a = list;
        this.b = list2;
        this.c = list3;
    }

    public int a() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }

    public final int b() {
        int a2 = a() * e() * 2;
        if (a2 <= 0) {
            return 115200;
        }
        return a2;
    }

    public void b(int i) {
        this.d = i;
    }

    public final byte[] c() {
        byte[] bArr = new byte[this.c.size() * 2];
        for (int i = 0; i < this.c.size(); i++) {
            byte[] a2 = e.a(this.c.get(i).intValue());
            int i2 = i * 2;
            bArr[i2] = a2[0];
            bArr[i2 + 1] = a2[1];
        }
        return bArr;
    }

    public final byte[] d() {
        List<Integer> list = this.f7726a;
        if (list == null || list.isEmpty()) {
            return null;
        }
        byte[] bArr = new byte[(this.f7726a.size() * 3) + 2];
        bArr[0] = 8;
        bArr[1] = 33;
        for (int i = 0; i < this.f7726a.size(); i++) {
            byte[] a2 = e.a(this.f7726a.get(i).intValue());
            int i2 = i * 3;
            bArr[i2 + 2] = a2[0];
            bArr[i2 + 3] = a2[1];
            bArr[i2 + 4] = (byte) this.b.get(i).intValue();
        }
        return bArr;
    }

    public int e() {
        return this.d;
    }

    public byte[] f() {
        byte[] d = d();
        return (d != null && b() >= d.length) ? d : c();
    }
}
