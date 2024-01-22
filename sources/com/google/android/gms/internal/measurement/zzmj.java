package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Arrays;
/* loaded from: classes8.dex */
public final class zzmj {
    public static final zzmj f = new zzmj(0, new int[0], new Object[0], false);

    /* renamed from: a  reason: collision with root package name */
    public int f8965a;
    public int[] b;
    public Object[] c;
    public int d;
    public boolean e;

    public zzmj() {
        this(0, new int[8], new Object[8], true);
    }

    public zzmj(int i, int[] iArr, Object[] objArr, boolean z) {
        this.d = -1;
        this.f8965a = i;
        this.b = iArr;
        this.c = objArr;
        this.e = z;
    }

    public static zzmj a(zzmj zzmjVar, zzmj zzmjVar2) {
        int i = zzmjVar.f8965a + zzmjVar2.f8965a;
        int[] copyOf = Arrays.copyOf(zzmjVar.b, i);
        System.arraycopy(zzmjVar2.b, 0, copyOf, zzmjVar.f8965a, zzmjVar2.f8965a);
        Object[] copyOf2 = Arrays.copyOf(zzmjVar.c, i);
        System.arraycopy(zzmjVar2.c, 0, copyOf2, zzmjVar.f8965a, zzmjVar2.f8965a);
        return new zzmj(i, copyOf, copyOf2, true);
    }

    public static zzmj b() {
        return new zzmj(0, new int[8], new Object[8], true);
    }

    public static zzmj zzc() {
        return f;
    }

    public final void c(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.f8965a; i2++) {
            o3.b(sb, i, String.valueOf(this.b[i2] >>> 3), this.c[i2]);
        }
    }

    public final void d(int i, Object obj) {
        if (this.e) {
            int i2 = this.f8965a;
            int[] iArr = this.b;
            if (i2 == iArr.length) {
                int i3 = i2 + (i2 < 4 ? 8 : i2 >> 1);
                this.b = Arrays.copyOf(iArr, i3);
                this.c = Arrays.copyOf(this.c, i3);
            }
            int[] iArr2 = this.b;
            int i4 = this.f8965a;
            iArr2[i4] = i;
            this.c[i4] = obj;
            this.f8965a = i4 + 1;
            return;
        }
        throw new UnsupportedOperationException();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof zzmj)) {
            zzmj zzmjVar = (zzmj) obj;
            int i = this.f8965a;
            if (i == zzmjVar.f8965a) {
                int[] iArr = this.b;
                int[] iArr2 = zzmjVar.b;
                int i2 = 0;
                while (true) {
                    if (i2 >= i) {
                        Object[] objArr = this.c;
                        Object[] objArr2 = zzmjVar.c;
                        int i3 = this.f8965a;
                        for (int i4 = 0; i4 < i3; i4++) {
                            if (objArr[i4].equals(objArr2[i4])) {
                            }
                        }
                        return true;
                    } else if (iArr[i2] != iArr2[i2]) {
                        break;
                    } else {
                        i2++;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        int i = this.f8965a;
        int i2 = (i + 527) * 31;
        int[] iArr = this.b;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.c;
        int i7 = this.f8965a;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }

    public final int zza() {
        int zzA;
        int zzB;
        int i;
        int i2 = this.d;
        if (i2 == -1) {
            int i3 = 0;
            for (int i4 = 0; i4 < this.f8965a; i4++) {
                int i5 = this.b[i4];
                int i6 = i5 >>> 3;
                int i7 = i5 & 7;
                if (i7 != 0) {
                    if (i7 == 1) {
                        ((Long) this.c[i4]).longValue();
                        i = zzjg.zzA(i6 << 3) + 8;
                    } else if (i7 == 2) {
                        int zzA2 = zzjg.zzA(i6 << 3);
                        int zzd = ((zziy) this.c[i4]).zzd();
                        i3 += zzA2 + zzjg.zzA(zzd) + zzd;
                    } else if (i7 == 3) {
                        int zzz = zzjg.zzz(i6);
                        zzA = zzz + zzz;
                        zzB = ((zzmj) this.c[i4]).zza();
                    } else if (i7 == 5) {
                        ((Integer) this.c[i4]).intValue();
                        i = zzjg.zzA(i6 << 3) + 4;
                    } else {
                        throw new IllegalStateException(zzkj.zza());
                    }
                    i3 += i;
                } else {
                    long longValue = ((Long) this.c[i4]).longValue();
                    zzA = zzjg.zzA(i6 << 3);
                    zzB = zzjg.zzB(longValue);
                }
                i = zzA + zzB;
                i3 += i;
            }
            this.d = i3;
            return i3;
        }
        return i2;
    }

    public final int zzb() {
        int i = this.d;
        if (i == -1) {
            int i2 = 0;
            for (int i3 = 0; i3 < this.f8965a; i3++) {
                int i4 = this.b[i3];
                int zzA = zzjg.zzA(8);
                int zzd = ((zziy) this.c[i3]).zzd();
                i2 += zzA + zzA + zzjg.zzA(16) + zzjg.zzA(i4 >>> 3) + zzjg.zzA(24) + zzjg.zzA(zzd) + zzd;
            }
            this.d = i2;
            return i2;
        }
        return i;
    }

    public final void zzf() {
        this.e = false;
    }

    public final void zzi(s2 s2Var) throws IOException {
        if (this.f8965a != 0) {
            for (int i = 0; i < this.f8965a; i++) {
                int i2 = this.b[i];
                Object obj = this.c[i];
                int i3 = i2 >>> 3;
                int i4 = i2 & 7;
                if (i4 == 0) {
                    s2Var.E(i3, ((Long) obj).longValue());
                } else if (i4 == 1) {
                    s2Var.x(i3, ((Long) obj).longValue());
                } else if (i4 == 2) {
                    s2Var.o(i3, (zziy) obj);
                } else if (i4 == 3) {
                    s2Var.e(i3);
                    ((zzmj) obj).zzi(s2Var);
                    s2Var.s(i3);
                } else if (i4 == 5) {
                    s2Var.v(i3, ((Integer) obj).intValue());
                } else {
                    throw new RuntimeException(zzkj.zza());
                }
            }
        }
    }
}
