package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;
import java.io.IOException;
import java.util.Arrays;
/* loaded from: classes7.dex */
public final class zzey {
    public static final zzey f = new zzey(0, new int[0], new Object[0], false);

    /* renamed from: a  reason: collision with root package name */
    public int f8620a;
    public int[] b;
    public Object[] c;
    public int d;
    public boolean e;

    public zzey() {
        this(0, new int[8], new Object[8], true);
    }

    public zzey(int i, int[] iArr, Object[] objArr, boolean z) {
        this.d = -1;
        this.f8620a = i;
        this.b = iArr;
        this.c = objArr;
        this.e = z;
    }

    public static zzey a(zzey zzeyVar, zzey zzeyVar2) {
        int i = zzeyVar.f8620a + zzeyVar2.f8620a;
        int[] copyOf = Arrays.copyOf(zzeyVar.b, i);
        System.arraycopy(zzeyVar2.b, 0, copyOf, zzeyVar.f8620a, zzeyVar2.f8620a);
        Object[] copyOf2 = Arrays.copyOf(zzeyVar.c, i);
        System.arraycopy(zzeyVar2.c, 0, copyOf2, zzeyVar.f8620a, zzeyVar2.f8620a);
        return new zzey(i, copyOf, copyOf2, true);
    }

    public static void e(int i, Object obj, z2 z2Var) throws IOException {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 == 0) {
            z2Var.b(i2, ((Long) obj).longValue());
        } else if (i3 == 1) {
            z2Var.zzc(i2, ((Long) obj).longValue());
        } else if (i3 == 2) {
            z2Var.i(i2, (zzbb) obj);
        } else if (i3 != 3) {
            if (i3 != 5) {
                throw new RuntimeException(zzco.zzbn());
            }
            z2Var.zzf(i2, ((Integer) obj).intValue());
        } else if (z2Var.l() == zzcg.zzg.zzko) {
            z2Var.f(i2);
            ((zzey) obj).zzb(z2Var);
            z2Var.d(i2);
        } else {
            z2Var.d(i2);
            ((zzey) obj).zzb(z2Var);
            z2Var.f(i2);
        }
    }

    public static zzey f() {
        return new zzey();
    }

    public static zzey zzea() {
        return f;
    }

    public final void b(z2 z2Var) throws IOException {
        if (z2Var.l() == zzcg.zzg.zzkp) {
            for (int i = this.f8620a - 1; i >= 0; i--) {
                z2Var.zza(this.b[i] >>> 3, this.c[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.f8620a; i2++) {
            z2Var.zza(this.b[i2] >>> 3, this.c[i2]);
        }
    }

    public final void c(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.f8620a; i2++) {
            g1.c(sb, i, String.valueOf(this.b[i2] >>> 3), this.c[i2]);
        }
    }

    public final void d(int i, Object obj) {
        if (!this.e) {
            throw new UnsupportedOperationException();
        }
        int i2 = this.f8620a;
        int[] iArr = this.b;
        if (i2 == iArr.length) {
            int i3 = i2 + (i2 < 4 ? 8 : i2 >> 1);
            this.b = Arrays.copyOf(iArr, i3);
            this.c = Arrays.copyOf(this.c, i3);
        }
        int[] iArr2 = this.b;
        int i4 = this.f8620a;
        iArr2[i4] = i;
        this.c[i4] = obj;
        this.f8620a = i4 + 1;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof zzey)) {
            zzey zzeyVar = (zzey) obj;
            int i = this.f8620a;
            if (i == zzeyVar.f8620a) {
                int[] iArr = this.b;
                int[] iArr2 = zzeyVar.b;
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
                    Object[] objArr2 = zzeyVar.c;
                    int i3 = this.f8620a;
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
        int i = this.f8620a;
        int i2 = (i + 527) * 31;
        int[] iArr = this.b;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.c;
        int i7 = this.f8620a;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }

    public final int zzas() {
        int zze;
        int i = this.d;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.f8620a; i3++) {
            int i4 = this.b[i3];
            int i5 = i4 >>> 3;
            int i6 = i4 & 7;
            if (i6 == 0) {
                zze = zzbn.zze(i5, ((Long) this.c[i3]).longValue());
            } else if (i6 == 1) {
                zze = zzbn.zzg(i5, ((Long) this.c[i3]).longValue());
            } else if (i6 == 2) {
                zze = zzbn.zzc(i5, (zzbb) this.c[i3]);
            } else if (i6 == 3) {
                zze = (zzbn.zzr(i5) << 1) + ((zzey) this.c[i3]).zzas();
            } else if (i6 != 5) {
                throw new IllegalStateException(zzco.zzbn());
            } else {
                zze = zzbn.zzj(i5, ((Integer) this.c[i3]).intValue());
            }
            i2 += zze;
        }
        this.d = i2;
        return i2;
    }

    public final void zzb(z2 z2Var) throws IOException {
        if (this.f8620a == 0) {
            return;
        }
        if (z2Var.l() == zzcg.zzg.zzko) {
            for (int i = 0; i < this.f8620a; i++) {
                e(this.b[i], this.c[i], z2Var);
            }
            return;
        }
        for (int i2 = this.f8620a - 1; i2 >= 0; i2--) {
            e(this.b[i2], this.c[i2], z2Var);
        }
    }

    public final int zzec() {
        int i = this.d;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.f8620a; i3++) {
            i2 += zzbn.zzd(this.b[i3] >>> 3, (zzbb) this.c[i3]);
        }
        this.d = i2;
        return i2;
    }

    public final void zzv() {
        this.e = false;
    }
}
