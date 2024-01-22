package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzgs;
import java.io.IOException;
import java.util.Arrays;
/* loaded from: classes10.dex */
public final class zzjm {
    public static final zzjm f = new zzjm(0, new int[0], new Object[0], false);

    /* renamed from: a  reason: collision with root package name */
    public int f10026a;
    public int[] b;
    public Object[] c;
    public int d;
    public boolean e;

    public zzjm() {
        this(0, new int[8], new Object[8], true);
    }

    public static zzjm a(zzjm zzjmVar, zzjm zzjmVar2) {
        int i = zzjmVar.f10026a + zzjmVar2.f10026a;
        int[] copyOf = Arrays.copyOf(zzjmVar.b, i);
        System.arraycopy(zzjmVar2.b, 0, copyOf, zzjmVar.f10026a, zzjmVar2.f10026a);
        Object[] copyOf2 = Arrays.copyOf(zzjmVar.c, i);
        System.arraycopy(zzjmVar2.c, 0, copyOf2, zzjmVar.f10026a, zzjmVar2.f10026a);
        return new zzjm(i, copyOf, copyOf2, true);
    }

    public static void e(int i, Object obj, x4 x4Var) throws IOException {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 == 0) {
            x4Var.b(i2, ((Long) obj).longValue());
        } else if (i3 == 1) {
            x4Var.zzc(i2, ((Long) obj).longValue());
        } else if (i3 == 2) {
            x4Var.o(i2, (zzfh) obj);
        } else if (i3 != 3) {
            if (i3 == 5) {
                x4Var.d(i2, ((Integer) obj).intValue());
                return;
            }
            throw new RuntimeException(zzhc.zzgr());
        } else if (x4Var.j() == zzgs.zzf.zzxc) {
            x4Var.k(i2);
            ((zzjm) obj).zzb(x4Var);
            x4Var.m(i2);
        } else {
            x4Var.m(i2);
            ((zzjm) obj).zzb(x4Var);
            x4Var.k(i2);
        }
    }

    public static zzjm f() {
        return new zzjm();
    }

    public static zzjm zzig() {
        return f;
    }

    public final void b(x4 x4Var) throws IOException {
        if (x4Var.j() == zzgs.zzf.zzxd) {
            for (int i = this.f10026a - 1; i >= 0; i--) {
                x4Var.zza(this.b[i] >>> 3, this.c[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.f10026a; i2++) {
            x4Var.zza(this.b[i2] >>> 3, this.c[i2]);
        }
    }

    public final void c(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.f10026a; i2++) {
            e3.c(sb, i, String.valueOf(this.b[i2] >>> 3), this.c[i2]);
        }
    }

    public final void d(int i, Object obj) {
        if (this.e) {
            int i2 = this.f10026a;
            int[] iArr = this.b;
            if (i2 == iArr.length) {
                int i3 = i2 + (i2 < 4 ? 8 : i2 >> 1);
                this.b = Arrays.copyOf(iArr, i3);
                this.c = Arrays.copyOf(this.c, i3);
            }
            int[] iArr2 = this.b;
            int i4 = this.f10026a;
            iArr2[i4] = i;
            this.c[i4] = obj;
            this.f10026a = i4 + 1;
            return;
        }
        throw new UnsupportedOperationException();
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof zzjm)) {
            zzjm zzjmVar = (zzjm) obj;
            int i = this.f10026a;
            if (i == zzjmVar.f10026a) {
                int[] iArr = this.b;
                int[] iArr2 = zzjmVar.b;
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
                    Object[] objArr2 = zzjmVar.c;
                    int i3 = this.f10026a;
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
        int i = this.f10026a;
        int i2 = (i + 527) * 31;
        int[] iArr = this.b;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.c;
        int i7 = this.f10026a;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }

    public final void zzb(x4 x4Var) throws IOException {
        if (this.f10026a == 0) {
            return;
        }
        if (x4Var.j() == zzgs.zzf.zzxc) {
            for (int i = 0; i < this.f10026a; i++) {
                e(this.b[i], this.c[i], x4Var);
            }
            return;
        }
        for (int i2 = this.f10026a - 1; i2 >= 0; i2--) {
            e(this.b[i2], this.c[i2], x4Var);
        }
    }

    public final void zzdp() {
        this.e = false;
    }

    public final int zzgf() {
        int zze;
        int i = this.d;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.f10026a; i3++) {
            int i4 = this.b[i3];
            int i5 = i4 >>> 3;
            int i6 = i4 & 7;
            if (i6 == 0) {
                zze = zzga.zze(i5, ((Long) this.c[i3]).longValue());
            } else if (i6 == 1) {
                zze = zzga.zzg(i5, ((Long) this.c[i3]).longValue());
            } else if (i6 == 2) {
                zze = zzga.zzc(i5, (zzfh) this.c[i3]);
            } else if (i6 == 3) {
                zze = (zzga.zzbb(i5) << 1) + ((zzjm) this.c[i3]).zzgf();
            } else if (i6 == 5) {
                zze = zzga.zzn(i5, ((Integer) this.c[i3]).intValue());
            } else {
                throw new IllegalStateException(zzhc.zzgr());
            }
            i2 += zze;
        }
        this.d = i2;
        return i2;
    }

    public final int zzii() {
        int i = this.d;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.f10026a; i3++) {
            i2 += zzga.zzd(this.b[i3] >>> 3, (zzfh) this.c[i3]);
        }
        this.d = i2;
        return i2;
    }

    public zzjm(int i, int[] iArr, Object[] objArr, boolean z) {
        this.d = -1;
        this.f10026a = i;
        this.b = iArr;
        this.c = objArr;
        this.e = z;
    }
}
