package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.List;
/* loaded from: classes8.dex */
public final class s2 {

    /* renamed from: a  reason: collision with root package name */
    public final zzjg f8926a;

    public s2(zzjg zzjgVar) {
        zzkh.c(zzjgVar, "output");
        this.f8926a = zzjgVar;
        zzjgVar.f8961a = this;
    }

    public static s2 l(zzjg zzjgVar) {
        s2 s2Var = zzjgVar.f8961a;
        return s2Var != null ? s2Var : new s2(zzjgVar);
    }

    public final void A(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8926a.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                list.get(i4).floatValue();
                i3 += 4;
            }
            this.f8926a.zzq(i3);
            while (i2 < list.size()) {
                this.f8926a.zzg(Float.floatToRawIntBits(list.get(i2).floatValue()));
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8926a.zzf(i, Float.floatToRawIntBits(list.get(i2).floatValue()));
            i2++;
        }
    }

    public final void B(int i, Object obj, x3 x3Var) throws IOException {
        zzjg zzjgVar = this.f8926a;
        zzjgVar.zzo(i, 3);
        x3Var.c((zzlg) obj, zzjgVar.f8961a);
        zzjgVar.zzo(i, 4);
    }

    public final void C(int i, int i2) throws IOException {
        this.f8926a.zzj(i, i2);
    }

    public final void D(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8926a.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzjg.zzv(list.get(i4).intValue());
            }
            this.f8926a.zzq(i3);
            while (i2 < list.size()) {
                this.f8926a.zzk(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8926a.zzj(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void E(int i, long j) throws IOException {
        this.f8926a.zzr(i, j);
    }

    public final void F(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8926a.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzjg.zzB(list.get(i4).longValue());
            }
            this.f8926a.zzq(i3);
            while (i2 < list.size()) {
                this.f8926a.zzs(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8926a.zzr(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void G(int i, Object obj, x3 x3Var) throws IOException {
        zzlg zzlgVar = (zzlg) obj;
        r2 r2Var = (r2) this.f8926a;
        r2Var.zzq((i << 3) | 2);
        zzih zzihVar = (zzih) zzlgVar;
        int a2 = zzihVar.a();
        if (a2 == -1) {
            a2 = x3Var.zza(zzihVar);
            zzihVar.b(a2);
        }
        r2Var.zzq(a2);
        x3Var.c(zzlgVar, r2Var.f8961a);
    }

    public final void H(int i, int i2) throws IOException {
        this.f8926a.zzf(i, i2);
    }

    public final void I(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8926a.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                list.get(i4).intValue();
                i3 += 4;
            }
            this.f8926a.zzq(i3);
            while (i2 < list.size()) {
                this.f8926a.zzg(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8926a.zzf(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void J(int i, long j) throws IOException {
        this.f8926a.zzh(i, j);
    }

    public final void K(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8926a.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                list.get(i4).longValue();
                i3 += 8;
            }
            this.f8926a.zzq(i3);
            while (i2 < list.size()) {
                this.f8926a.zzi(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8926a.zzh(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void a(int i, int i2) throws IOException {
        this.f8926a.zzp(i, (i2 >> 31) ^ (i2 + i2));
    }

    public final void b(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8926a.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                int intValue = list.get(i4).intValue();
                i3 += zzjg.zzA((intValue >> 31) ^ (intValue + intValue));
            }
            this.f8926a.zzq(i3);
            while (i2 < list.size()) {
                zzjg zzjgVar = this.f8926a;
                int intValue2 = list.get(i2).intValue();
                zzjgVar.zzq((intValue2 >> 31) ^ (intValue2 + intValue2));
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            zzjg zzjgVar2 = this.f8926a;
            int intValue3 = list.get(i2).intValue();
            zzjgVar2.zzp(i, (intValue3 >> 31) ^ (intValue3 + intValue3));
            i2++;
        }
    }

    public final void c(int i, long j) throws IOException {
        this.f8926a.zzr(i, (j >> 63) ^ (j + j));
    }

    public final void d(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8926a.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                long longValue = list.get(i4).longValue();
                i3 += zzjg.zzB((longValue >> 63) ^ (longValue + longValue));
            }
            this.f8926a.zzq(i3);
            while (i2 < list.size()) {
                zzjg zzjgVar = this.f8926a;
                long longValue2 = list.get(i2).longValue();
                zzjgVar.zzs((longValue2 >> 63) ^ (longValue2 + longValue2));
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            zzjg zzjgVar2 = this.f8926a;
            long longValue3 = list.get(i2).longValue();
            zzjgVar2.zzr(i, (longValue3 >> 63) ^ (longValue3 + longValue3));
            i2++;
        }
    }

    public final void e(int i) throws IOException {
        this.f8926a.zzo(i, 3);
    }

    public final void f(int i, String str) throws IOException {
        this.f8926a.zzm(i, str);
    }

    public final void g(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof zzko) {
            zzko zzkoVar = (zzko) list;
            while (i2 < list.size()) {
                Object zzf = zzkoVar.zzf(i2);
                if (zzf instanceof String) {
                    this.f8926a.zzm(i, (String) zzf);
                } else {
                    this.f8926a.zze(i, (zziy) zzf);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8926a.zzm(i, list.get(i2));
            i2++;
        }
    }

    public final void h(int i, int i2) throws IOException {
        this.f8926a.zzp(i, i2);
    }

    public final void i(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8926a.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzjg.zzA(list.get(i4).intValue());
            }
            this.f8926a.zzq(i3);
            while (i2 < list.size()) {
                this.f8926a.zzq(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8926a.zzp(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void j(int i, long j) throws IOException {
        this.f8926a.zzr(i, j);
    }

    public final void k(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8926a.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzjg.zzB(list.get(i4).longValue());
            }
            this.f8926a.zzq(i3);
            while (i2 < list.size()) {
                this.f8926a.zzs(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8926a.zzr(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void m(int i, boolean z) throws IOException {
        this.f8926a.zzd(i, z);
    }

    public final void n(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8926a.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                list.get(i4).booleanValue();
                i3++;
            }
            this.f8926a.zzq(i3);
            while (i2 < list.size()) {
                this.f8926a.zzb(list.get(i2).booleanValue() ? (byte) 1 : (byte) 0);
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8926a.zzd(i, list.get(i2).booleanValue());
            i2++;
        }
    }

    public final void o(int i, zziy zziyVar) throws IOException {
        this.f8926a.zze(i, zziyVar);
    }

    public final void p(int i, List<zziy> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.f8926a.zze(i, list.get(i2));
        }
    }

    public final void q(int i, double d) throws IOException {
        this.f8926a.zzh(i, Double.doubleToRawLongBits(d));
    }

    public final void r(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8926a.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                list.get(i4).doubleValue();
                i3 += 8;
            }
            this.f8926a.zzq(i3);
            while (i2 < list.size()) {
                this.f8926a.zzi(Double.doubleToRawLongBits(list.get(i2).doubleValue()));
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8926a.zzh(i, Double.doubleToRawLongBits(list.get(i2).doubleValue()));
            i2++;
        }
    }

    public final void s(int i) throws IOException {
        this.f8926a.zzo(i, 4);
    }

    public final void t(int i, int i2) throws IOException {
        this.f8926a.zzj(i, i2);
    }

    public final void u(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8926a.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzjg.zzv(list.get(i4).intValue());
            }
            this.f8926a.zzq(i3);
            while (i2 < list.size()) {
                this.f8926a.zzk(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8926a.zzj(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void v(int i, int i2) throws IOException {
        this.f8926a.zzf(i, i2);
    }

    public final void w(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8926a.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                list.get(i4).intValue();
                i3 += 4;
            }
            this.f8926a.zzq(i3);
            while (i2 < list.size()) {
                this.f8926a.zzg(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8926a.zzf(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void x(int i, long j) throws IOException {
        this.f8926a.zzh(i, j);
    }

    public final void y(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f8926a.zzo(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                list.get(i4).longValue();
                i3 += 8;
            }
            this.f8926a.zzq(i3);
            while (i2 < list.size()) {
                this.f8926a.zzi(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f8926a.zzh(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void z(int i, float f) throws IOException {
        this.f8926a.zzf(i, Float.floatToRawIntBits(f));
    }
}
