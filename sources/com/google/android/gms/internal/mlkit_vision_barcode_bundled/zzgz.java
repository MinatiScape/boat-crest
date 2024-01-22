package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
import java.util.Arrays;
/* loaded from: classes8.dex */
public final class zzgz {
    public static final zzgz f = new zzgz(0, new int[0], new Object[0], false);

    /* renamed from: a  reason: collision with root package name */
    public int f9634a;
    public int[] b;
    public Object[] c;
    public int d;
    public boolean e;

    public zzgz() {
        this(0, new int[8], new Object[8], true);
    }

    public zzgz(int i, int[] iArr, Object[] objArr, boolean z) {
        this.d = -1;
        this.f9634a = i;
        this.b = iArr;
        this.c = objArr;
        this.e = z;
    }

    public static zzgz b(zzgz zzgzVar, zzgz zzgzVar2) {
        int i = zzgzVar.f9634a + zzgzVar2.f9634a;
        int[] copyOf = Arrays.copyOf(zzgzVar.b, i);
        System.arraycopy(zzgzVar2.b, 0, copyOf, zzgzVar.f9634a, zzgzVar2.f9634a);
        Object[] copyOf2 = Arrays.copyOf(zzgzVar.c, i);
        System.arraycopy(zzgzVar2.c, 0, copyOf2, zzgzVar.f9634a, zzgzVar2.f9634a);
        return new zzgz(i, copyOf, copyOf2, true);
    }

    public static zzgz c() {
        return new zzgz(0, new int[8], new Object[8], true);
    }

    public static zzgz zzc() {
        return f;
    }

    public final zzgz a(zzgz zzgzVar) {
        if (zzgzVar.equals(f)) {
            return this;
        }
        d();
        int i = this.f9634a + zzgzVar.f9634a;
        h(i);
        System.arraycopy(zzgzVar.b, 0, this.b, this.f9634a, zzgzVar.f9634a);
        System.arraycopy(zzgzVar.c, 0, this.c, this.f9634a, zzgzVar.f9634a);
        this.f9634a = i;
        return this;
    }

    public final void d() {
        if (!this.e) {
            throw new UnsupportedOperationException();
        }
    }

    public final void e(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.f9634a; i2++) {
            x0.b(sb, i, String.valueOf(this.b[i2] >>> 3), this.c[i2]);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof zzgz)) {
            zzgz zzgzVar = (zzgz) obj;
            int i = this.f9634a;
            if (i == zzgzVar.f9634a) {
                int[] iArr = this.b;
                int[] iArr2 = zzgzVar.b;
                int i2 = 0;
                while (true) {
                    if (i2 >= i) {
                        Object[] objArr = this.c;
                        Object[] objArr2 = zzgzVar.c;
                        int i3 = this.f9634a;
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

    public final void f(int i, Object obj) {
        d();
        h(this.f9634a + 1);
        int[] iArr = this.b;
        int i2 = this.f9634a;
        iArr[i2] = i;
        this.c[i2] = obj;
        this.f9634a = i2 + 1;
    }

    public final void g(m2 m2Var) throws IOException {
        for (int i = 0; i < this.f9634a; i++) {
            m2Var.zzw(this.b[i] >>> 3, this.c[i]);
        }
    }

    public final void h(int i) {
        int[] iArr = this.b;
        if (i > iArr.length) {
            int i2 = this.f9634a;
            int i3 = i2 + (i2 / 2);
            if (i3 >= i) {
                i = i3;
            }
            if (i < 8) {
                i = 8;
            }
            this.b = Arrays.copyOf(iArr, i);
            this.c = Arrays.copyOf(this.c, i);
        }
    }

    public final int hashCode() {
        int i = this.f9634a;
        int i2 = i + 527;
        int[] iArr = this.b;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 * 31) + i4;
        Object[] objArr = this.c;
        int i7 = this.f9634a;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return (i6 * 31) + i3;
    }

    public final int zza() {
        int zzz;
        int zzy;
        int i;
        int i2 = this.d;
        if (i2 == -1) {
            int i3 = 0;
            for (int i4 = 0; i4 < this.f9634a; i4++) {
                int i5 = this.b[i4];
                int i6 = i5 >>> 3;
                int i7 = i5 & 7;
                if (i7 != 0) {
                    if (i7 == 1) {
                        ((Long) this.c[i4]).longValue();
                        i = zzdj.zzy(i6 << 3) + 8;
                    } else if (i7 == 2) {
                        int i8 = zzdj.zzb;
                        int zzd = ((zzdb) this.c[i4]).zzd();
                        i = zzdj.zzy(i6 << 3) + zzdj.zzy(zzd) + zzd;
                    } else if (i7 == 3) {
                        int i9 = i6 << 3;
                        int i10 = zzdj.zzb;
                        zzz = ((zzgz) this.c[i4]).zza();
                        int zzy2 = zzdj.zzy(i9);
                        zzy = zzy2 + zzy2;
                    } else if (i7 == 5) {
                        ((Integer) this.c[i4]).intValue();
                        i = zzdj.zzy(i6 << 3) + 4;
                    } else {
                        throw new IllegalStateException(zzeo.zza());
                    }
                    i3 += i;
                } else {
                    int i11 = i6 << 3;
                    zzz = zzdj.zzz(((Long) this.c[i4]).longValue());
                    zzy = zzdj.zzy(i11);
                }
                i = zzy + zzz;
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
            for (int i3 = 0; i3 < this.f9634a; i3++) {
                int i4 = zzdj.zzb;
                int zzd = ((zzdb) this.c[i3]).zzd();
                int zzy = zzdj.zzy(zzd) + zzd;
                int zzy2 = zzdj.zzy(16);
                int zzy3 = zzdj.zzy(this.b[i3] >>> 3);
                int zzy4 = zzdj.zzy(8);
                i2 += zzy4 + zzy4 + zzy2 + zzy3 + zzdj.zzy(24) + zzy;
            }
            this.d = i2;
            return i2;
        }
        return i;
    }

    public final void zzh() {
        this.e = false;
    }

    public final void zzl(m2 m2Var) throws IOException {
        if (this.f9634a != 0) {
            for (int i = 0; i < this.f9634a; i++) {
                int i2 = this.b[i];
                Object obj = this.c[i];
                int i3 = i2 & 7;
                int i4 = i2 >>> 3;
                if (i3 == 0) {
                    m2Var.zzt(i4, ((Long) obj).longValue());
                } else if (i3 == 1) {
                    m2Var.zzm(i4, ((Long) obj).longValue());
                } else if (i3 == 2) {
                    m2Var.c(i4, (zzdb) obj);
                } else if (i3 == 3) {
                    m2Var.zzF(i4);
                    ((zzgz) obj).zzl(m2Var);
                    m2Var.zzh(i4);
                } else if (i3 == 5) {
                    m2Var.zzk(i4, ((Integer) obj).intValue());
                } else {
                    throw new RuntimeException(zzeo.zza());
                }
            }
        }
    }
}
