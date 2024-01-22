package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public final class x1 implements p3 {

    /* renamed from: a  reason: collision with root package name */
    public final zzft f10004a;
    public int b;
    public int c;
    public int d = 0;

    public x1(zzft zzftVar) {
        zzft zzftVar2 = (zzft) zzgt.c(zzftVar, "input");
        this.f10004a = zzftVar2;
        zzftVar2.c = this;
    }

    public static x1 M(zzft zzftVar) {
        x1 x1Var = zzftVar.c;
        return x1Var != null ? x1Var : new x1(zzftVar);
    }

    public static void P(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzhc.zzgs();
        }
    }

    public static void Q(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzhc.zzgs();
        }
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final int A() throws IOException {
        O(0);
        return this.f10004a.zzei();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final long B() throws IOException {
        O(0);
        return this.f10004a.zzdx();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final long C() throws IOException {
        O(0);
        return this.f10004a.zzej();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final <T> T D(Class<T> cls, zzgd zzgdVar) throws IOException {
        O(3);
        return (T) T(k3.b().a(cls), zzgdVar);
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void E(List<Integer> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof m2) {
            m2 m2Var = (m2) list;
            int i = this.b & 7;
            if (i == 0) {
                do {
                    m2Var.c(this.f10004a.zzef());
                    if (this.f10004a.zzdt()) {
                        return;
                    }
                    zzex2 = this.f10004a.zzex();
                } while (zzex2 == this.b);
                this.d = zzex2;
                return;
            } else if (i == 2) {
                int zzez = this.f10004a.zzez() + this.f10004a.zzee();
                do {
                    m2Var.c(this.f10004a.zzef());
                } while (this.f10004a.zzez() < zzez);
                R(zzez);
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int i2 = this.b & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.f10004a.zzef()));
                if (this.f10004a.zzdt()) {
                    return;
                }
                zzex = this.f10004a.zzex();
            } while (zzex == this.b);
            this.d = zzex;
        } else if (i2 == 2) {
            int zzez2 = this.f10004a.zzez() + this.f10004a.zzee();
            do {
                list.add(Integer.valueOf(this.f10004a.zzef()));
            } while (this.f10004a.zzez() < zzez2);
            R(zzez2);
        } else {
            throw zzhc.zzgr();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.vision.p3
    public final <T> void F(List<T> list, o3<T> o3Var, zzgd zzgdVar) throws IOException {
        int zzex;
        int i = this.b;
        if ((i & 7) == 2) {
            do {
                list.add(S(o3Var, zzgdVar));
                if (this.f10004a.zzdt() || this.d != 0) {
                    return;
                }
                zzex = this.f10004a.zzex();
            } while (zzex == i);
            this.d = zzex;
            return;
        }
        throw zzhc.zzgr();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void G(List<Integer> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof m2) {
            m2 m2Var = (m2) list;
            int i = this.b & 7;
            if (i == 0) {
                do {
                    m2Var.c(this.f10004a.zzee());
                    if (this.f10004a.zzdt()) {
                        return;
                    }
                    zzex2 = this.f10004a.zzex();
                } while (zzex2 == this.b);
                this.d = zzex2;
                return;
            } else if (i == 2) {
                int zzez = this.f10004a.zzez() + this.f10004a.zzee();
                do {
                    m2Var.c(this.f10004a.zzee());
                } while (this.f10004a.zzez() < zzez);
                R(zzez);
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int i2 = this.b & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.f10004a.zzee()));
                if (this.f10004a.zzdt()) {
                    return;
                }
                zzex = this.f10004a.zzex();
            } while (zzex == this.b);
            this.d = zzex;
        } else if (i2 == 2) {
            int zzez2 = this.f10004a.zzez() + this.f10004a.zzee();
            do {
                list.add(Integer.valueOf(this.f10004a.zzee()));
            } while (this.f10004a.zzez() < zzez2);
            R(zzez2);
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final <T> T H(o3<T> o3Var, zzgd zzgdVar) throws IOException {
        O(2);
        return (T) S(o3Var, zzgdVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.vision.p3
    public final <T> void I(List<T> list, o3<T> o3Var, zzgd zzgdVar) throws IOException {
        int zzex;
        int i = this.b;
        if ((i & 7) == 3) {
            do {
                list.add(T(o3Var, zzgdVar));
                if (this.f10004a.zzdt() || this.d != 0) {
                    return;
                }
                zzex = this.f10004a.zzex();
            } while (zzex == i);
            this.d = zzex;
            return;
        }
        throw zzhc.zzgr();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void J(List<Long> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof v2) {
            v2 v2Var = (v2) list;
            int i = this.b & 7;
            if (i == 1) {
                do {
                    v2Var.a(this.f10004a.zzeh());
                    if (this.f10004a.zzdt()) {
                        return;
                    }
                    zzex2 = this.f10004a.zzex();
                } while (zzex2 == this.b);
                this.d = zzex2;
                return;
            } else if (i == 2) {
                int zzee = this.f10004a.zzee();
                P(zzee);
                int zzez = this.f10004a.zzez() + zzee;
                do {
                    v2Var.a(this.f10004a.zzeh());
                } while (this.f10004a.zzez() < zzez);
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int i2 = this.b & 7;
        if (i2 == 1) {
            do {
                list.add(Long.valueOf(this.f10004a.zzeh()));
                if (this.f10004a.zzdt()) {
                    return;
                }
                zzex = this.f10004a.zzex();
            } while (zzex == this.b);
            this.d = zzex;
        } else if (i2 == 2) {
            int zzee2 = this.f10004a.zzee();
            P(zzee2);
            int zzez2 = this.f10004a.zzez() + zzee2;
            do {
                list.add(Long.valueOf(this.f10004a.zzeh()));
            } while (this.f10004a.zzez() < zzez2);
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void K(List<Integer> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof m2) {
            m2 m2Var = (m2) list;
            int i = this.b & 7;
            if (i == 2) {
                int zzee = this.f10004a.zzee();
                Q(zzee);
                int zzez = this.f10004a.zzez() + zzee;
                do {
                    m2Var.c(this.f10004a.zzeg());
                } while (this.f10004a.zzez() < zzez);
                return;
            } else if (i == 5) {
                do {
                    m2Var.c(this.f10004a.zzeg());
                    if (this.f10004a.zzdt()) {
                        return;
                    }
                    zzex2 = this.f10004a.zzex();
                } while (zzex2 == this.b);
                this.d = zzex2;
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int i2 = this.b & 7;
        if (i2 == 2) {
            int zzee2 = this.f10004a.zzee();
            Q(zzee2);
            int zzez2 = this.f10004a.zzez() + zzee2;
            do {
                list.add(Integer.valueOf(this.f10004a.zzeg()));
            } while (this.f10004a.zzez() < zzez2);
        } else if (i2 == 5) {
            do {
                list.add(Integer.valueOf(this.f10004a.zzeg()));
                if (this.f10004a.zzdt()) {
                    return;
                }
                zzex = this.f10004a.zzex();
            } while (zzex == this.b);
            this.d = zzex;
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final <T> T L(o3<T> o3Var, zzgd zzgdVar) throws IOException {
        O(3);
        return (T) T(o3Var, zzgdVar);
    }

    public final void N(List<String> list, boolean z) throws IOException {
        int zzex;
        int zzex2;
        if ((this.b & 7) == 2) {
            if ((list instanceof zzhj) && !z) {
                zzhj zzhjVar = (zzhj) list;
                do {
                    zzhjVar.zzc(g());
                    if (this.f10004a.zzdt()) {
                        return;
                    }
                    zzex2 = this.f10004a.zzex();
                } while (zzex2 == this.b);
                this.d = zzex2;
                return;
            }
            do {
                list.add(z ? d() : a());
                if (this.f10004a.zzdt()) {
                    return;
                }
                zzex = this.f10004a.zzex();
            } while (zzex == this.b);
            this.d = zzex;
            return;
        }
        throw zzhc.zzgr();
    }

    public final void O(int i) throws IOException {
        if ((this.b & 7) != i) {
            throw zzhc.zzgr();
        }
    }

    public final void R(int i) throws IOException {
        if (this.f10004a.zzez() != i) {
            throw zzhc.zzgm();
        }
    }

    public final <T> T S(o3<T> o3Var, zzgd zzgdVar) throws IOException {
        zzft zzftVar;
        int zzee = this.f10004a.zzee();
        zzft zzftVar2 = this.f10004a;
        if (zzftVar2.f10018a < zzftVar2.b) {
            int zzat = zzftVar2.zzat(zzee);
            T newInstance = o3Var.newInstance();
            this.f10004a.f10018a++;
            o3Var.i(newInstance, this, zzgdVar);
            o3Var.e(newInstance);
            this.f10004a.zzar(0);
            zzftVar.f10018a--;
            this.f10004a.zzau(zzat);
            return newInstance;
        }
        throw new zzhc("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    public final <T> T T(o3<T> o3Var, zzgd zzgdVar) throws IOException {
        int i = this.c;
        this.c = ((this.b >>> 3) << 3) | 4;
        try {
            T newInstance = o3Var.newInstance();
            o3Var.i(newInstance, this, zzgdVar);
            o3Var.e(newInstance);
            if (this.b == this.c) {
                return newInstance;
            }
            throw zzhc.zzgs();
        } finally {
            this.c = i;
        }
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final String a() throws IOException {
        O(2);
        return this.f10004a.readString();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void b(List<String> list) throws IOException {
        N(list, false);
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final <K, V> void c(Map<K, V> map, y2<K, V> y2Var, zzgd zzgdVar) throws IOException {
        O(2);
        this.f10004a.zzat(this.f10004a.zzee());
        throw null;
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final String d() throws IOException {
        O(2);
        return this.f10004a.zzec();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void e(List<Boolean> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof j1) {
            j1 j1Var = (j1) list;
            int i = this.b & 7;
            if (i == 0) {
                do {
                    j1Var.addBoolean(this.f10004a.zzeb());
                    if (this.f10004a.zzdt()) {
                        return;
                    }
                    zzex2 = this.f10004a.zzex();
                } while (zzex2 == this.b);
                this.d = zzex2;
                return;
            } else if (i == 2) {
                int zzez = this.f10004a.zzez() + this.f10004a.zzee();
                do {
                    j1Var.addBoolean(this.f10004a.zzeb());
                } while (this.f10004a.zzez() < zzez);
                R(zzez);
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int i2 = this.b & 7;
        if (i2 == 0) {
            do {
                list.add(Boolean.valueOf(this.f10004a.zzeb()));
                if (this.f10004a.zzdt()) {
                    return;
                }
                zzex = this.f10004a.zzex();
            } while (zzex == this.b);
            this.d = zzex;
        } else if (i2 == 2) {
            int zzez2 = this.f10004a.zzez() + this.f10004a.zzee();
            do {
                list.add(Boolean.valueOf(this.f10004a.zzeb()));
            } while (this.f10004a.zzez() < zzez2);
            R(zzez2);
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void f(List<Integer> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof m2) {
            m2 m2Var = (m2) list;
            int i = this.b & 7;
            if (i == 2) {
                int zzee = this.f10004a.zzee();
                Q(zzee);
                int zzez = this.f10004a.zzez() + zzee;
                do {
                    m2Var.c(this.f10004a.zzea());
                } while (this.f10004a.zzez() < zzez);
                return;
            } else if (i == 5) {
                do {
                    m2Var.c(this.f10004a.zzea());
                    if (this.f10004a.zzdt()) {
                        return;
                    }
                    zzex2 = this.f10004a.zzex();
                } while (zzex2 == this.b);
                this.d = zzex2;
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int i2 = this.b & 7;
        if (i2 == 2) {
            int zzee2 = this.f10004a.zzee();
            Q(zzee2);
            int zzez2 = this.f10004a.zzez() + zzee2;
            do {
                list.add(Integer.valueOf(this.f10004a.zzea()));
            } while (this.f10004a.zzez() < zzez2);
        } else if (i2 == 5) {
            do {
                list.add(Integer.valueOf(this.f10004a.zzea()));
                if (this.f10004a.zzdt()) {
                    return;
                }
                zzex = this.f10004a.zzex();
            } while (zzex == this.b);
            this.d = zzex;
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final zzfh g() throws IOException {
        O(2);
        return this.f10004a.zzed();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final int getTag() {
        return this.b;
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void h(List<Long> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof v2) {
            v2 v2Var = (v2) list;
            int i = this.b & 7;
            if (i == 1) {
                do {
                    v2Var.a(this.f10004a.zzdz());
                    if (this.f10004a.zzdt()) {
                        return;
                    }
                    zzex2 = this.f10004a.zzex();
                } while (zzex2 == this.b);
                this.d = zzex2;
                return;
            } else if (i == 2) {
                int zzee = this.f10004a.zzee();
                P(zzee);
                int zzez = this.f10004a.zzez() + zzee;
                do {
                    v2Var.a(this.f10004a.zzdz());
                } while (this.f10004a.zzez() < zzez);
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int i2 = this.b & 7;
        if (i2 == 1) {
            do {
                list.add(Long.valueOf(this.f10004a.zzdz()));
                if (this.f10004a.zzdt()) {
                    return;
                }
                zzex = this.f10004a.zzex();
            } while (zzex == this.b);
            this.d = zzex;
        } else if (i2 == 2) {
            int zzee2 = this.f10004a.zzee();
            P(zzee2);
            int zzez2 = this.f10004a.zzez() + zzee2;
            do {
                list.add(Long.valueOf(this.f10004a.zzdz()));
            } while (this.f10004a.zzez() < zzez2);
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final int i() throws IOException {
        O(5);
        return this.f10004a.zzea();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void j(List<zzfh> list) throws IOException {
        int zzex;
        if ((this.b & 7) == 2) {
            do {
                list.add(g());
                if (this.f10004a.zzdt()) {
                    return;
                }
                zzex = this.f10004a.zzex();
            } while (zzex == this.b);
            this.d = zzex;
            return;
        }
        throw zzhc.zzgr();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final boolean k() throws IOException {
        O(0);
        return this.f10004a.zzeb();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void l(List<String> list) throws IOException {
        N(list, true);
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final int m() throws IOException {
        int i = this.d;
        if (i != 0) {
            this.b = i;
            this.d = 0;
        } else {
            this.b = this.f10004a.zzex();
        }
        int i2 = this.b;
        if (i2 == 0 || i2 == this.c) {
            return Integer.MAX_VALUE;
        }
        return i2 >>> 3;
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final int n() throws IOException {
        O(5);
        return this.f10004a.zzeg();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void o(List<Long> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof v2) {
            v2 v2Var = (v2) list;
            int i = this.b & 7;
            if (i == 0) {
                do {
                    v2Var.a(this.f10004a.zzdw());
                    if (this.f10004a.zzdt()) {
                        return;
                    }
                    zzex2 = this.f10004a.zzex();
                } while (zzex2 == this.b);
                this.d = zzex2;
                return;
            } else if (i == 2) {
                int zzez = this.f10004a.zzez() + this.f10004a.zzee();
                do {
                    v2Var.a(this.f10004a.zzdw());
                } while (this.f10004a.zzez() < zzez);
                R(zzez);
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int i2 = this.b & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(this.f10004a.zzdw()));
                if (this.f10004a.zzdt()) {
                    return;
                }
                zzex = this.f10004a.zzex();
            } while (zzex == this.b);
            this.d = zzex;
        } else if (i2 == 2) {
            int zzez2 = this.f10004a.zzez() + this.f10004a.zzee();
            do {
                list.add(Long.valueOf(this.f10004a.zzdw()));
            } while (this.f10004a.zzez() < zzez2);
            R(zzez2);
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final boolean p() throws IOException {
        int i;
        if (this.f10004a.zzdt() || (i = this.b) == this.c) {
            return false;
        }
        return this.f10004a.zzas(i);
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final long q() throws IOException {
        O(1);
        return this.f10004a.zzeh();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final int r() throws IOException {
        O(0);
        return this.f10004a.zzee();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final double readDouble() throws IOException {
        O(1);
        return this.f10004a.readDouble();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final float readFloat() throws IOException {
        O(5);
        return this.f10004a.readFloat();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final int s() throws IOException {
        O(0);
        return this.f10004a.zzef();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void t(List<Long> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof v2) {
            v2 v2Var = (v2) list;
            int i = this.b & 7;
            if (i == 0) {
                do {
                    v2Var.a(this.f10004a.zzdx());
                    if (this.f10004a.zzdt()) {
                        return;
                    }
                    zzex2 = this.f10004a.zzex();
                } while (zzex2 == this.b);
                this.d = zzex2;
                return;
            } else if (i == 2) {
                int zzez = this.f10004a.zzez() + this.f10004a.zzee();
                do {
                    v2Var.a(this.f10004a.zzdx());
                } while (this.f10004a.zzez() < zzez);
                R(zzez);
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int i2 = this.b & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(this.f10004a.zzdx()));
                if (this.f10004a.zzdt()) {
                    return;
                }
                zzex = this.f10004a.zzex();
            } while (zzex == this.b);
            this.d = zzex;
        } else if (i2 == 2) {
            int zzez2 = this.f10004a.zzez() + this.f10004a.zzee();
            do {
                list.add(Long.valueOf(this.f10004a.zzdx()));
            } while (this.f10004a.zzez() < zzez2);
            R(zzez2);
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final int u() throws IOException {
        O(0);
        return this.f10004a.zzdy();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void v(List<Long> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof v2) {
            v2 v2Var = (v2) list;
            int i = this.b & 7;
            if (i == 0) {
                do {
                    v2Var.a(this.f10004a.zzej());
                    if (this.f10004a.zzdt()) {
                        return;
                    }
                    zzex2 = this.f10004a.zzex();
                } while (zzex2 == this.b);
                this.d = zzex2;
                return;
            } else if (i == 2) {
                int zzez = this.f10004a.zzez() + this.f10004a.zzee();
                do {
                    v2Var.a(this.f10004a.zzej());
                } while (this.f10004a.zzez() < zzez);
                R(zzez);
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int i2 = this.b & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(this.f10004a.zzej()));
                if (this.f10004a.zzdt()) {
                    return;
                }
                zzex = this.f10004a.zzex();
            } while (zzex == this.b);
            this.d = zzex;
        } else if (i2 == 2) {
            int zzez2 = this.f10004a.zzez() + this.f10004a.zzee();
            do {
                list.add(Long.valueOf(this.f10004a.zzej()));
            } while (this.f10004a.zzez() < zzez2);
            R(zzez2);
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final <T> T w(Class<T> cls, zzgd zzgdVar) throws IOException {
        O(2);
        return (T) S(k3.b().a(cls), zzgdVar);
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void x(List<Integer> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof m2) {
            m2 m2Var = (m2) list;
            int i = this.b & 7;
            if (i == 0) {
                do {
                    m2Var.c(this.f10004a.zzei());
                    if (this.f10004a.zzdt()) {
                        return;
                    }
                    zzex2 = this.f10004a.zzex();
                } while (zzex2 == this.b);
                this.d = zzex2;
                return;
            } else if (i == 2) {
                int zzez = this.f10004a.zzez() + this.f10004a.zzee();
                do {
                    m2Var.c(this.f10004a.zzei());
                } while (this.f10004a.zzez() < zzez);
                R(zzez);
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int i2 = this.b & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.f10004a.zzei()));
                if (this.f10004a.zzdt()) {
                    return;
                }
                zzex = this.f10004a.zzex();
            } while (zzex == this.b);
            this.d = zzex;
        } else if (i2 == 2) {
            int zzez2 = this.f10004a.zzez() + this.f10004a.zzee();
            do {
                list.add(Integer.valueOf(this.f10004a.zzei()));
            } while (this.f10004a.zzez() < zzez2);
            R(zzez2);
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final long y() throws IOException {
        O(1);
        return this.f10004a.zzdz();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final long z() throws IOException {
        O(0);
        return this.f10004a.zzdw();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void zza(List<Double> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof z1) {
            z1 z1Var = (z1) list;
            int i = this.b & 7;
            if (i == 1) {
                do {
                    z1Var.c(this.f10004a.readDouble());
                    if (this.f10004a.zzdt()) {
                        return;
                    }
                    zzex2 = this.f10004a.zzex();
                } while (zzex2 == this.b);
                this.d = zzex2;
                return;
            } else if (i == 2) {
                int zzee = this.f10004a.zzee();
                P(zzee);
                int zzez = this.f10004a.zzez() + zzee;
                do {
                    z1Var.c(this.f10004a.readDouble());
                } while (this.f10004a.zzez() < zzez);
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int i2 = this.b & 7;
        if (i2 == 1) {
            do {
                list.add(Double.valueOf(this.f10004a.readDouble()));
                if (this.f10004a.zzdt()) {
                    return;
                }
                zzex = this.f10004a.zzex();
            } while (zzex == this.b);
            this.d = zzex;
        } else if (i2 == 2) {
            int zzee2 = this.f10004a.zzee();
            P(zzee2);
            int zzez2 = this.f10004a.zzez() + zzee2;
            do {
                list.add(Double.valueOf(this.f10004a.readDouble()));
            } while (this.f10004a.zzez() < zzez2);
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void zzb(List<Float> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof i2) {
            i2 i2Var = (i2) list;
            int i = this.b & 7;
            if (i == 2) {
                int zzee = this.f10004a.zzee();
                Q(zzee);
                int zzez = this.f10004a.zzez() + zzee;
                do {
                    i2Var.c(this.f10004a.readFloat());
                } while (this.f10004a.zzez() < zzez);
                return;
            } else if (i == 5) {
                do {
                    i2Var.c(this.f10004a.readFloat());
                    if (this.f10004a.zzdt()) {
                        return;
                    }
                    zzex2 = this.f10004a.zzex();
                } while (zzex2 == this.b);
                this.d = zzex2;
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int i2 = this.b & 7;
        if (i2 == 2) {
            int zzee2 = this.f10004a.zzee();
            Q(zzee2);
            int zzez2 = this.f10004a.zzez() + zzee2;
            do {
                list.add(Float.valueOf(this.f10004a.readFloat()));
            } while (this.f10004a.zzez() < zzez2);
        } else if (i2 == 5) {
            do {
                list.add(Float.valueOf(this.f10004a.readFloat()));
                if (this.f10004a.zzdt()) {
                    return;
                }
                zzex = this.f10004a.zzex();
            } while (zzex == this.b);
            this.d = zzex;
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void zze(List<Integer> list) throws IOException {
        int zzex;
        int zzex2;
        if (list instanceof m2) {
            m2 m2Var = (m2) list;
            int i = this.b & 7;
            if (i == 0) {
                do {
                    m2Var.c(this.f10004a.zzdy());
                    if (this.f10004a.zzdt()) {
                        return;
                    }
                    zzex2 = this.f10004a.zzex();
                } while (zzex2 == this.b);
                this.d = zzex2;
                return;
            } else if (i == 2) {
                int zzez = this.f10004a.zzez() + this.f10004a.zzee();
                do {
                    m2Var.c(this.f10004a.zzdy());
                } while (this.f10004a.zzez() < zzez);
                R(zzez);
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int i2 = this.b & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.f10004a.zzdy()));
                if (this.f10004a.zzdt()) {
                    return;
                }
                zzex = this.f10004a.zzex();
            } while (zzex == this.b);
            this.d = zzex;
        } else if (i2 == 2) {
            int zzez2 = this.f10004a.zzez() + this.f10004a.zzee();
            do {
                list.add(Integer.valueOf(this.f10004a.zzdy()));
            } while (this.f10004a.zzez() < zzez2);
            R(zzez2);
        } else {
            throw zzhc.zzgr();
        }
    }
}
