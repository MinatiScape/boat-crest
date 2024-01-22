package com.google.android.gms.internal.vision;

import com.htsmart.wristband2.WristbandManager;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public final class k1 extends i1 {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f9986a;
    public final byte[] b;
    public int c;
    public int d;
    public int e;
    public int f;

    public k1(ByteBuffer byteBuffer, boolean z) {
        super();
        this.f9986a = true;
        this.b = byteBuffer.array();
        this.c = byteBuffer.arrayOffset() + byteBuffer.position();
        this.d = byteBuffer.arrayOffset() + byteBuffer.limit();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final int A() throws IOException {
        Q(0);
        return zzft.zzav(X());
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final long B() throws IOException {
        Q(0);
        return Y();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final long C() throws IOException {
        Q(0);
        return zzft.zzr(Y());
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final <T> T D(Class<T> cls, zzgd zzgdVar) throws IOException {
        Q(3);
        return (T) V(k3.b().a(cls), zzgdVar);
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void E(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof m2) {
            m2 m2Var = (m2) list;
            int i3 = this.e & 7;
            if (i3 != 0) {
                if (i3 == 2) {
                    int X = this.c + X();
                    while (this.c < X) {
                        m2Var.c(X());
                    }
                    return;
                }
                throw zzhc.zzgr();
            }
            do {
                m2Var.c(s());
                if (W()) {
                    return;
                }
                i2 = this.c;
            } while (X() == this.e);
            this.c = i2;
            return;
        }
        int i4 = this.e & 7;
        if (i4 != 0) {
            if (i4 == 2) {
                int X2 = this.c + X();
                while (this.c < X2) {
                    list.add(Integer.valueOf(X()));
                }
                return;
            }
            throw zzhc.zzgr();
        }
        do {
            list.add(Integer.valueOf(s()));
            if (W()) {
                return;
            }
            i = this.c;
        } while (X() == this.e);
        this.c = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.vision.p3
    public final <T> void F(List<T> list, o3<T> o3Var, zzgd zzgdVar) throws IOException {
        int i;
        int i2 = this.e;
        if ((i2 & 7) == 2) {
            do {
                list.add(U(o3Var, zzgdVar));
                if (W()) {
                    return;
                }
                i = this.c;
            } while (X() == i2);
            this.c = i;
            return;
        }
        throw zzhc.zzgr();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void G(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof m2) {
            m2 m2Var = (m2) list;
            int i3 = this.e & 7;
            if (i3 != 0) {
                if (i3 == 2) {
                    int X = this.c + X();
                    while (this.c < X) {
                        m2Var.c(X());
                    }
                    return;
                }
                throw zzhc.zzgr();
            }
            do {
                m2Var.c(r());
                if (W()) {
                    return;
                }
                i2 = this.c;
            } while (X() == this.e);
            this.c = i2;
            return;
        }
        int i4 = this.e & 7;
        if (i4 != 0) {
            if (i4 == 2) {
                int X2 = this.c + X();
                while (this.c < X2) {
                    list.add(Integer.valueOf(X()));
                }
                return;
            }
            throw zzhc.zzgr();
        }
        do {
            list.add(Integer.valueOf(r()));
            if (W()) {
                return;
            }
            i = this.c;
        } while (X() == this.e);
        this.c = i;
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final <T> T H(o3<T> o3Var, zzgd zzgdVar) throws IOException {
        Q(2);
        return (T) U(o3Var, zzgdVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.vision.p3
    public final <T> void I(List<T> list, o3<T> o3Var, zzgd zzgdVar) throws IOException {
        int i;
        int i2 = this.e;
        if ((i2 & 7) == 3) {
            do {
                list.add(V(o3Var, zzgdVar));
                if (W()) {
                    return;
                }
                i = this.c;
            } while (X() == i2);
            this.c = i;
            return;
        }
        throw zzhc.zzgr();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void J(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof v2) {
            v2 v2Var = (v2) list;
            int i3 = this.e & 7;
            if (i3 == 1) {
                do {
                    v2Var.a(q());
                    if (W()) {
                        return;
                    }
                    i2 = this.c;
                } while (X() == this.e);
                this.c = i2;
                return;
            } else if (i3 == 2) {
                int X = X();
                R(X);
                int i4 = this.c + X;
                while (this.c < i4) {
                    v2Var.a(d0());
                }
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int i5 = this.e & 7;
        if (i5 == 1) {
            do {
                list.add(Long.valueOf(q()));
                if (W()) {
                    return;
                }
                i = this.c;
            } while (X() == this.e);
            this.c = i;
        } else if (i5 == 2) {
            int X2 = X();
            R(X2);
            int i6 = this.c + X2;
            while (this.c < i6) {
                list.add(Long.valueOf(d0()));
            }
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void K(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof m2) {
            m2 m2Var = (m2) list;
            int i3 = this.e & 7;
            if (i3 == 2) {
                int X = X();
                S(X);
                int i4 = this.c + X;
                while (this.c < i4) {
                    m2Var.c(c0());
                }
                return;
            } else if (i3 == 5) {
                do {
                    m2Var.c(n());
                    if (W()) {
                        return;
                    }
                    i2 = this.c;
                } while (X() == this.e);
                this.c = i2;
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int i5 = this.e & 7;
        if (i5 == 2) {
            int X2 = X();
            S(X2);
            int i6 = this.c + X2;
            while (this.c < i6) {
                list.add(Integer.valueOf(c0()));
            }
        } else if (i5 == 5) {
            do {
                list.add(Integer.valueOf(n()));
                if (W()) {
                    return;
                }
                i = this.c;
            } while (X() == this.e);
            this.c = i;
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final <T> T L(o3<T> o3Var, zzgd zzgdVar) throws IOException {
        Q(3);
        return (T) V(o3Var, zzgdVar);
    }

    public final byte M() throws IOException {
        int i = this.c;
        if (i != this.d) {
            byte[] bArr = this.b;
            this.c = i + 1;
            return bArr[i];
        }
        throw zzhc.zzgm();
    }

    public final void N(List<String> list, boolean z) throws IOException {
        int i;
        int i2;
        if ((this.e & 7) == 2) {
            if ((list instanceof zzhj) && !z) {
                zzhj zzhjVar = (zzhj) list;
                do {
                    zzhjVar.zzc(g());
                    if (W()) {
                        return;
                    }
                    i2 = this.c;
                } while (X() == this.e);
                this.c = i2;
                return;
            }
            do {
                list.add(e0(z));
                if (W()) {
                    return;
                }
                i = this.c;
            } while (X() == this.e);
            this.c = i;
            return;
        }
        throw zzhc.zzgr();
    }

    public final void O(int i) throws IOException {
        P(i);
        this.c += i;
    }

    public final void P(int i) throws IOException {
        if (i < 0 || i > this.d - this.c) {
            throw zzhc.zzgm();
        }
    }

    public final void Q(int i) throws IOException {
        if ((this.e & 7) != i) {
            throw zzhc.zzgr();
        }
    }

    public final void R(int i) throws IOException {
        P(i);
        if ((i & 7) != 0) {
            throw zzhc.zzgs();
        }
    }

    public final void S(int i) throws IOException {
        P(i);
        if ((i & 3) != 0) {
            throw zzhc.zzgs();
        }
    }

    public final void T(int i) throws IOException {
        if (this.c != i) {
            throw zzhc.zzgm();
        }
    }

    public final <T> T U(o3<T> o3Var, zzgd zzgdVar) throws IOException {
        int X = X();
        P(X);
        int i = this.d;
        int i2 = this.c + X;
        this.d = i2;
        try {
            T newInstance = o3Var.newInstance();
            o3Var.i(newInstance, this, zzgdVar);
            o3Var.e(newInstance);
            if (this.c == i2) {
                return newInstance;
            }
            throw zzhc.zzgs();
        } finally {
            this.d = i;
        }
    }

    public final <T> T V(o3<T> o3Var, zzgd zzgdVar) throws IOException {
        int i = this.f;
        this.f = ((this.e >>> 3) << 3) | 4;
        try {
            T newInstance = o3Var.newInstance();
            o3Var.i(newInstance, this, zzgdVar);
            o3Var.e(newInstance);
            if (this.e == this.f) {
                return newInstance;
            }
            throw zzhc.zzgs();
        } finally {
            this.f = i;
        }
    }

    public final boolean W() {
        return this.c == this.d;
    }

    public final int X() throws IOException {
        int i;
        int i2 = this.c;
        int i3 = this.d;
        if (i3 != i2) {
            byte[] bArr = this.b;
            int i4 = i2 + 1;
            byte b = bArr[i2];
            if (b >= 0) {
                this.c = i4;
                return b;
            } else if (i3 - i4 < 9) {
                return (int) Z();
            } else {
                int i5 = i4 + 1;
                int i6 = b ^ (bArr[i4] << 7);
                if (i6 < 0) {
                    i = i6 ^ WristbandManager.SYNC_STATE_FAILED_UNKNOWN;
                } else {
                    int i7 = i5 + 1;
                    int i8 = i6 ^ (bArr[i5] << 14);
                    if (i8 >= 0) {
                        i = i8 ^ 16256;
                    } else {
                        i5 = i7 + 1;
                        int i9 = i8 ^ (bArr[i7] << 21);
                        if (i9 < 0) {
                            i = i9 ^ (-2080896);
                        } else {
                            i7 = i5 + 1;
                            byte b2 = bArr[i5];
                            i = (i9 ^ (b2 << 28)) ^ 266354560;
                            if (b2 < 0) {
                                i5 = i7 + 1;
                                if (bArr[i7] < 0) {
                                    i7 = i5 + 1;
                                    if (bArr[i5] < 0) {
                                        i5 = i7 + 1;
                                        if (bArr[i7] < 0) {
                                            i7 = i5 + 1;
                                            if (bArr[i5] < 0) {
                                                i5 = i7 + 1;
                                                if (bArr[i7] < 0) {
                                                    throw zzhc.zzgo();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    i5 = i7;
                }
                this.c = i5;
                return i;
            }
        }
        throw zzhc.zzgm();
    }

    public final long Y() throws IOException {
        long j;
        long j2;
        long j3;
        int i;
        int i2 = this.c;
        int i3 = this.d;
        if (i3 != i2) {
            byte[] bArr = this.b;
            int i4 = i2 + 1;
            byte b = bArr[i2];
            if (b >= 0) {
                this.c = i4;
                return b;
            } else if (i3 - i4 < 9) {
                return Z();
            } else {
                int i5 = i4 + 1;
                int i6 = b ^ (bArr[i4] << 7);
                if (i6 >= 0) {
                    int i7 = i5 + 1;
                    int i8 = i6 ^ (bArr[i5] << 14);
                    if (i8 >= 0) {
                        i5 = i7;
                        j = i8 ^ 16256;
                    } else {
                        i5 = i7 + 1;
                        int i9 = i8 ^ (bArr[i7] << 21);
                        if (i9 < 0) {
                            i = i9 ^ (-2080896);
                        } else {
                            long j4 = i9;
                            int i10 = i5 + 1;
                            long j5 = j4 ^ (bArr[i5] << 28);
                            if (j5 >= 0) {
                                j3 = 266354560;
                            } else {
                                i5 = i10 + 1;
                                long j6 = j5 ^ (bArr[i10] << 35);
                                if (j6 < 0) {
                                    j2 = -34093383808L;
                                } else {
                                    i10 = i5 + 1;
                                    j5 = j6 ^ (bArr[i5] << 42);
                                    if (j5 >= 0) {
                                        j3 = 4363953127296L;
                                    } else {
                                        i5 = i10 + 1;
                                        j6 = j5 ^ (bArr[i10] << 49);
                                        if (j6 < 0) {
                                            j2 = -558586000294016L;
                                        } else {
                                            int i11 = i5 + 1;
                                            long j7 = (j6 ^ (bArr[i5] << 56)) ^ 71499008037633920L;
                                            if (j7 < 0) {
                                                i5 = i11 + 1;
                                                if (bArr[i11] < 0) {
                                                    throw zzhc.zzgo();
                                                }
                                            } else {
                                                i5 = i11;
                                            }
                                            j = j7;
                                        }
                                    }
                                }
                                j = j6 ^ j2;
                            }
                            j = j5 ^ j3;
                            i5 = i10;
                        }
                    }
                    this.c = i5;
                    return j;
                }
                i = i6 ^ WristbandManager.SYNC_STATE_FAILED_UNKNOWN;
                j = i;
                this.c = i5;
                return j;
            }
        }
        throw zzhc.zzgm();
    }

    public final long Z() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte M = M();
            j |= (M & Byte.MAX_VALUE) << i;
            if ((M & 128) == 0) {
                return j;
            }
        }
        throw zzhc.zzgo();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final String a() throws IOException {
        return e0(false);
    }

    public final int a0() throws IOException {
        P(4);
        return c0();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void b(List<String> list) throws IOException {
        N(list, false);
    }

    public final long b0() throws IOException {
        P(8);
        return d0();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final <K, V> void c(Map<K, V> map, y2<K, V> y2Var, zzgd zzgdVar) throws IOException {
        Q(2);
        int X = X();
        P(X);
        int i = this.d;
        this.d = this.c + X;
        try {
            throw null;
        } catch (Throwable th) {
            this.d = i;
            throw th;
        }
    }

    public final int c0() {
        int i = this.c;
        byte[] bArr = this.b;
        this.c = i + 4;
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final String d() throws IOException {
        return e0(true);
    }

    public final long d0() {
        int i = this.c;
        byte[] bArr = this.b;
        this.c = i + 8;
        return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void e(List<Boolean> list) throws IOException {
        int i;
        int i2;
        if (list instanceof j1) {
            j1 j1Var = (j1) list;
            int i3 = this.e & 7;
            if (i3 != 0) {
                if (i3 == 2) {
                    int X = this.c + X();
                    while (this.c < X) {
                        j1Var.addBoolean(X() != 0);
                    }
                    T(X);
                    return;
                }
                throw zzhc.zzgr();
            }
            do {
                j1Var.addBoolean(k());
                if (W()) {
                    return;
                }
                i2 = this.c;
            } while (X() == this.e);
            this.c = i2;
            return;
        }
        int i4 = this.e & 7;
        if (i4 != 0) {
            if (i4 == 2) {
                int X2 = this.c + X();
                while (this.c < X2) {
                    list.add(Boolean.valueOf(X() != 0));
                }
                T(X2);
                return;
            }
            throw zzhc.zzgr();
        }
        do {
            list.add(Boolean.valueOf(k()));
            if (W()) {
                return;
            }
            i = this.c;
        } while (X() == this.e);
        this.c = i;
    }

    public final String e0(boolean z) throws IOException {
        Q(2);
        int X = X();
        if (X == 0) {
            return "";
        }
        P(X);
        if (z) {
            byte[] bArr = this.b;
            int i = this.c;
            if (!m4.g(bArr, i, i + X)) {
                throw zzhc.zzgt();
            }
        }
        String str = new String(this.b, this.c, X, zzgt.f10024a);
        this.c += X;
        return str;
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void f(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof m2) {
            m2 m2Var = (m2) list;
            int i3 = this.e & 7;
            if (i3 == 2) {
                int X = X();
                S(X);
                int i4 = this.c + X;
                while (this.c < i4) {
                    m2Var.c(c0());
                }
                return;
            } else if (i3 == 5) {
                do {
                    m2Var.c(i());
                    if (W()) {
                        return;
                    }
                    i2 = this.c;
                } while (X() == this.e);
                this.c = i2;
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int i5 = this.e & 7;
        if (i5 == 2) {
            int X2 = X();
            S(X2);
            int i6 = this.c + X2;
            while (this.c < i6) {
                list.add(Integer.valueOf(c0()));
            }
        } else if (i5 == 5) {
            do {
                list.add(Integer.valueOf(i()));
                if (W()) {
                    return;
                }
                i = this.c;
            } while (X() == this.e);
            this.c = i;
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final zzfh g() throws IOException {
        zzfh zza;
        Q(2);
        int X = X();
        if (X == 0) {
            return zzfh.zzsd;
        }
        P(X);
        if (this.f9986a) {
            zza = zzfh.zzb(this.b, this.c, X);
        } else {
            zza = zzfh.zza(this.b, this.c, X);
        }
        this.c += X;
        return zza;
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final int getTag() {
        return this.e;
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void h(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof v2) {
            v2 v2Var = (v2) list;
            int i3 = this.e & 7;
            if (i3 == 1) {
                do {
                    v2Var.a(y());
                    if (W()) {
                        return;
                    }
                    i2 = this.c;
                } while (X() == this.e);
                this.c = i2;
                return;
            } else if (i3 == 2) {
                int X = X();
                R(X);
                int i4 = this.c + X;
                while (this.c < i4) {
                    v2Var.a(d0());
                }
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int i5 = this.e & 7;
        if (i5 == 1) {
            do {
                list.add(Long.valueOf(y()));
                if (W()) {
                    return;
                }
                i = this.c;
            } while (X() == this.e);
            this.c = i;
        } else if (i5 == 2) {
            int X2 = X();
            R(X2);
            int i6 = this.c + X2;
            while (this.c < i6) {
                list.add(Long.valueOf(d0()));
            }
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final int i() throws IOException {
        Q(5);
        return a0();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void j(List<zzfh> list) throws IOException {
        int i;
        if ((this.e & 7) == 2) {
            do {
                list.add(g());
                if (W()) {
                    return;
                }
                i = this.c;
            } while (X() == this.e);
            this.c = i;
            return;
        }
        throw zzhc.zzgr();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final boolean k() throws IOException {
        Q(0);
        return X() != 0;
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void l(List<String> list) throws IOException {
        N(list, true);
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final int m() throws IOException {
        if (W()) {
            return Integer.MAX_VALUE;
        }
        int X = X();
        this.e = X;
        if (X == this.f) {
            return Integer.MAX_VALUE;
        }
        return X >>> 3;
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final int n() throws IOException {
        Q(5);
        return a0();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void o(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof v2) {
            v2 v2Var = (v2) list;
            int i3 = this.e & 7;
            if (i3 == 0) {
                do {
                    v2Var.a(z());
                    if (W()) {
                        return;
                    }
                    i2 = this.c;
                } while (X() == this.e);
                this.c = i2;
                return;
            } else if (i3 == 2) {
                int X = this.c + X();
                while (this.c < X) {
                    v2Var.a(Y());
                }
                T(X);
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int i4 = this.e & 7;
        if (i4 == 0) {
            do {
                list.add(Long.valueOf(z()));
                if (W()) {
                    return;
                }
                i = this.c;
            } while (X() == this.e);
            this.c = i;
        } else if (i4 == 2) {
            int X2 = this.c + X();
            while (this.c < X2) {
                list.add(Long.valueOf(Y()));
            }
            T(X2);
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final boolean p() throws IOException {
        int i;
        int i2;
        if (W() || (i = this.e) == (i2 = this.f)) {
            return false;
        }
        int i3 = i & 7;
        if (i3 == 0) {
            int i4 = this.d;
            int i5 = this.c;
            if (i4 - i5 >= 10) {
                byte[] bArr = this.b;
                int i6 = 0;
                while (i6 < 10) {
                    int i7 = i5 + 1;
                    if (bArr[i5] >= 0) {
                        this.c = i7;
                        break;
                    }
                    i6++;
                    i5 = i7;
                }
            }
            for (int i8 = 0; i8 < 10; i8++) {
                if (M() >= 0) {
                    return true;
                }
            }
            throw zzhc.zzgo();
        } else if (i3 == 1) {
            O(8);
            return true;
        } else if (i3 == 2) {
            O(X());
            return true;
        } else if (i3 != 3) {
            if (i3 == 5) {
                O(4);
                return true;
            }
            throw zzhc.zzgr();
        } else {
            this.f = ((i >>> 3) << 3) | 4;
            while (m() != Integer.MAX_VALUE && p()) {
            }
            if (this.e == this.f) {
                this.f = i2;
                return true;
            }
            throw zzhc.zzgs();
        }
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final long q() throws IOException {
        Q(1);
        return b0();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final int r() throws IOException {
        Q(0);
        return X();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final double readDouble() throws IOException {
        Q(1);
        return Double.longBitsToDouble(b0());
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final float readFloat() throws IOException {
        Q(5);
        return Float.intBitsToFloat(a0());
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final int s() throws IOException {
        Q(0);
        return X();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void t(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof v2) {
            v2 v2Var = (v2) list;
            int i3 = this.e & 7;
            if (i3 == 0) {
                do {
                    v2Var.a(B());
                    if (W()) {
                        return;
                    }
                    i2 = this.c;
                } while (X() == this.e);
                this.c = i2;
                return;
            } else if (i3 == 2) {
                int X = this.c + X();
                while (this.c < X) {
                    v2Var.a(Y());
                }
                T(X);
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int i4 = this.e & 7;
        if (i4 == 0) {
            do {
                list.add(Long.valueOf(B()));
                if (W()) {
                    return;
                }
                i = this.c;
            } while (X() == this.e);
            this.c = i;
        } else if (i4 == 2) {
            int X2 = this.c + X();
            while (this.c < X2) {
                list.add(Long.valueOf(Y()));
            }
            T(X2);
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final int u() throws IOException {
        Q(0);
        return X();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void v(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof v2) {
            v2 v2Var = (v2) list;
            int i3 = this.e & 7;
            if (i3 != 0) {
                if (i3 == 2) {
                    int X = this.c + X();
                    while (this.c < X) {
                        v2Var.a(zzft.zzr(Y()));
                    }
                    return;
                }
                throw zzhc.zzgr();
            }
            do {
                v2Var.a(C());
                if (W()) {
                    return;
                }
                i2 = this.c;
            } while (X() == this.e);
            this.c = i2;
            return;
        }
        int i4 = this.e & 7;
        if (i4 != 0) {
            if (i4 == 2) {
                int X2 = this.c + X();
                while (this.c < X2) {
                    list.add(Long.valueOf(zzft.zzr(Y())));
                }
                return;
            }
            throw zzhc.zzgr();
        }
        do {
            list.add(Long.valueOf(C()));
            if (W()) {
                return;
            }
            i = this.c;
        } while (X() == this.e);
        this.c = i;
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final <T> T w(Class<T> cls, zzgd zzgdVar) throws IOException {
        Q(2);
        return (T) U(k3.b().a(cls), zzgdVar);
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void x(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof m2) {
            m2 m2Var = (m2) list;
            int i3 = this.e & 7;
            if (i3 != 0) {
                if (i3 == 2) {
                    int X = this.c + X();
                    while (this.c < X) {
                        m2Var.c(zzft.zzav(X()));
                    }
                    return;
                }
                throw zzhc.zzgr();
            }
            do {
                m2Var.c(A());
                if (W()) {
                    return;
                }
                i2 = this.c;
            } while (X() == this.e);
            this.c = i2;
            return;
        }
        int i4 = this.e & 7;
        if (i4 != 0) {
            if (i4 == 2) {
                int X2 = this.c + X();
                while (this.c < X2) {
                    list.add(Integer.valueOf(zzft.zzav(X())));
                }
                return;
            }
            throw zzhc.zzgr();
        }
        do {
            list.add(Integer.valueOf(A()));
            if (W()) {
                return;
            }
            i = this.c;
        } while (X() == this.e);
        this.c = i;
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final long y() throws IOException {
        Q(1);
        return b0();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final long z() throws IOException {
        Q(0);
        return Y();
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void zza(List<Double> list) throws IOException {
        int i;
        int i2;
        if (list instanceof z1) {
            z1 z1Var = (z1) list;
            int i3 = this.e & 7;
            if (i3 == 1) {
                do {
                    z1Var.c(readDouble());
                    if (W()) {
                        return;
                    }
                    i2 = this.c;
                } while (X() == this.e);
                this.c = i2;
                return;
            } else if (i3 == 2) {
                int X = X();
                R(X);
                int i4 = this.c + X;
                while (this.c < i4) {
                    z1Var.c(Double.longBitsToDouble(d0()));
                }
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int i5 = this.e & 7;
        if (i5 == 1) {
            do {
                list.add(Double.valueOf(readDouble()));
                if (W()) {
                    return;
                }
                i = this.c;
            } while (X() == this.e);
            this.c = i;
        } else if (i5 == 2) {
            int X2 = X();
            R(X2);
            int i6 = this.c + X2;
            while (this.c < i6) {
                list.add(Double.valueOf(Double.longBitsToDouble(d0())));
            }
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void zzb(List<Float> list) throws IOException {
        int i;
        int i2;
        if (list instanceof i2) {
            i2 i2Var = (i2) list;
            int i3 = this.e & 7;
            if (i3 == 2) {
                int X = X();
                S(X);
                int i4 = this.c + X;
                while (this.c < i4) {
                    i2Var.c(Float.intBitsToFloat(c0()));
                }
                return;
            } else if (i3 == 5) {
                do {
                    i2Var.c(readFloat());
                    if (W()) {
                        return;
                    }
                    i2 = this.c;
                } while (X() == this.e);
                this.c = i2;
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int i5 = this.e & 7;
        if (i5 == 2) {
            int X2 = X();
            S(X2);
            int i6 = this.c + X2;
            while (this.c < i6) {
                list.add(Float.valueOf(Float.intBitsToFloat(c0())));
            }
        } else if (i5 == 5) {
            do {
                list.add(Float.valueOf(readFloat()));
                if (W()) {
                    return;
                }
                i = this.c;
            } while (X() == this.e);
            this.c = i;
        } else {
            throw zzhc.zzgr();
        }
    }

    @Override // com.google.android.gms.internal.vision.p3
    public final void zze(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof m2) {
            m2 m2Var = (m2) list;
            int i3 = this.e & 7;
            if (i3 == 0) {
                do {
                    m2Var.c(u());
                    if (W()) {
                        return;
                    }
                    i2 = this.c;
                } while (X() == this.e);
                this.c = i2;
                return;
            } else if (i3 == 2) {
                int X = this.c + X();
                while (this.c < X) {
                    m2Var.c(X());
                }
                T(X);
                return;
            } else {
                throw zzhc.zzgr();
            }
        }
        int i4 = this.e & 7;
        if (i4 == 0) {
            do {
                list.add(Integer.valueOf(u()));
                if (W()) {
                    return;
                }
                i = this.c;
            } while (X() == this.e);
            this.c = i;
        } else if (i4 == 2) {
            int X2 = this.c + X();
            while (this.c < X2) {
                list.add(Integer.valueOf(X()));
            }
            T(X2);
        } else {
            throw zzhc.zzgr();
        }
    }
}
