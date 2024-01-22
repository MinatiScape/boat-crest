package com.google.android.gms.internal.fitness;

import java.io.IOException;
import java.util.Arrays;
/* loaded from: classes8.dex */
public final class zzjr {
    public static final zzjr e = new zzjr(0, new int[0], new Object[0], false);

    /* renamed from: a  reason: collision with root package name */
    public int f8866a;
    public int[] b;
    public Object[] c;
    public int d;

    public zzjr() {
        this(0, new int[8], new Object[8], true);
    }

    public static zzjr a(zzjr zzjrVar, zzjr zzjrVar2) {
        int i = zzjrVar.f8866a + zzjrVar2.f8866a;
        int[] copyOf = Arrays.copyOf(zzjrVar.b, i);
        System.arraycopy(zzjrVar2.b, 0, copyOf, zzjrVar.f8866a, zzjrVar2.f8866a);
        Object[] copyOf2 = Arrays.copyOf(zzjrVar.c, i);
        System.arraycopy(zzjrVar2.c, 0, copyOf2, zzjrVar.f8866a, zzjrVar2.f8866a);
        return new zzjr(i, copyOf, copyOf2, true);
    }

    public static void d(int i, Object obj, d5 d5Var) throws IOException {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 == 0) {
            d5Var.b(i2, ((Long) obj).longValue());
        } else if (i3 == 1) {
            d5Var.zzc(i2, ((Long) obj).longValue());
        } else if (i3 == 2) {
            d5Var.j(i2, (zzfx) obj);
        } else if (i3 != 3) {
            if (i3 == 5) {
                d5Var.c(i2, ((Integer) obj).intValue());
                return;
            }
            throw new RuntimeException(zzhk.zzcd());
        } else if (d5Var.zzbe() == zzkl.zzaea) {
            d5Var.f(i2);
            ((zzjr) obj).zzb(d5Var);
            d5Var.d(i2);
        } else {
            d5Var.d(i2);
            ((zzjr) obj).zzb(d5Var);
            d5Var.f(i2);
        }
    }

    public static zzjr zzdp() {
        return e;
    }

    public final void b(d5 d5Var) throws IOException {
        if (d5Var.zzbe() == zzkl.zzaeb) {
            for (int i = this.f8866a - 1; i >= 0; i--) {
                d5Var.zza(this.b[i] >>> 3, this.c[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.f8866a; i2++) {
            d5Var.zza(this.b[i2] >>> 3, this.c[i2]);
        }
    }

    public final void c(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.f8866a; i2++) {
            m3.c(sb, i, String.valueOf(this.b[i2] >>> 3), this.c[i2]);
        }
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof zzjr)) {
            zzjr zzjrVar = (zzjr) obj;
            int i = this.f8866a;
            if (i == zzjrVar.f8866a) {
                int[] iArr = this.b;
                int[] iArr2 = zzjrVar.b;
                int i2 = 0;
                while (true) {
                    if (i2 >= i) {
                        z = true;
                        break;
                    } else if (iArr[i2] != iArr2[i2]) {
                        z = false;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (z) {
                    Object[] objArr = this.c;
                    Object[] objArr2 = zzjrVar.c;
                    int i3 = this.f8866a;
                    int i4 = 0;
                    while (true) {
                        if (i4 >= i3) {
                            z2 = true;
                            break;
                        } else if (!objArr[i4].equals(objArr2[i4])) {
                            z2 = false;
                            break;
                        } else {
                            i4++;
                        }
                    }
                    if (z2) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        int i = this.f8866a;
        int i2 = (i + 527) * 31;
        int[] iArr = this.b;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.c;
        int i7 = this.f8866a;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }

    public final void zzar() {
    }

    public final void zzb(d5 d5Var) throws IOException {
        if (this.f8866a == 0) {
            return;
        }
        if (d5Var.zzbe() == zzkl.zzaea) {
            for (int i = 0; i < this.f8866a; i++) {
                d(this.b[i], this.c[i], d5Var);
            }
            return;
        }
        for (int i2 = this.f8866a - 1; i2 >= 0; i2--) {
            d(this.b[i2], this.c[i2], d5Var);
        }
    }

    public final int zzbp() {
        int zze;
        int i = this.d;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.f8866a; i3++) {
            int i4 = this.b[i3];
            int i5 = i4 >>> 3;
            int i6 = i4 & 7;
            if (i6 == 0) {
                zze = zzgk.zze(i5, ((Long) this.c[i3]).longValue());
            } else if (i6 == 1) {
                zze = zzgk.zzg(i5, ((Long) this.c[i3]).longValue());
            } else if (i6 == 2) {
                zze = zzgk.zzc(i5, (zzfx) this.c[i3]);
            } else if (i6 == 3) {
                zze = (zzgk.zzr(i5) << 1) + ((zzjr) this.c[i3]).zzbp();
            } else if (i6 == 5) {
                zze = zzgk.zzl(i5, ((Integer) this.c[i3]).intValue());
            } else {
                throw new IllegalStateException(zzhk.zzcd());
            }
            i2 += zze;
        }
        this.d = i2;
        return i2;
    }

    public final int zzdq() {
        int i = this.d;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.f8866a; i3++) {
            i2 += zzgk.zzd(this.b[i3] >>> 3, (zzfx) this.c[i3]);
        }
        this.d = i2;
        return i2;
    }

    public zzjr(int i, int[] iArr, Object[] objArr, boolean z) {
        this.d = -1;
        this.f8866a = i;
        this.b = iArr;
        this.c = objArr;
    }
}
