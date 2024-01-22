package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.internal.firebase_ml.zzwz;
import java.io.IOException;
import java.util.Arrays;
/* loaded from: classes7.dex */
public final class zzzz {
    public static final zzzz f = new zzzz(0, new int[0], new Object[0], false);

    /* renamed from: a  reason: collision with root package name */
    public int f8817a;
    public int[] b;
    public Object[] c;
    public int d;
    public boolean e;

    public zzzz() {
        this(0, new int[8], new Object[8], true);
    }

    public static zzzz a(zzzz zzzzVar, zzzz zzzzVar2) {
        int i = zzzzVar.f8817a + zzzzVar2.f8817a;
        int[] copyOf = Arrays.copyOf(zzzzVar.b, i);
        System.arraycopy(zzzzVar2.b, 0, copyOf, zzzzVar.f8817a, zzzzVar2.f8817a);
        Object[] copyOf2 = Arrays.copyOf(zzzzVar.c, i);
        System.arraycopy(zzzzVar2.c, 0, copyOf2, zzzzVar.f8817a, zzzzVar2.f8817a);
        return new zzzz(i, copyOf, copyOf2, true);
    }

    public static void d(int i, Object obj, p pVar) throws IOException {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 == 0) {
            pVar.b(i2, ((Long) obj).longValue());
        } else if (i3 == 1) {
            pVar.zzc(i2, ((Long) obj).longValue());
        } else if (i3 == 2) {
            pVar.n(i2, (zzvv) obj);
        } else if (i3 != 3) {
            if (i3 == 5) {
                pVar.zzk(i2, ((Integer) obj).intValue());
                return;
            }
            throw new RuntimeException(zzxk.zzvh());
        } else if (pVar.e() == zzwz.zzg.zzcme) {
            pVar.m(i2);
            ((zzzz) obj).zzb(pVar);
            pVar.k(i2);
        } else {
            pVar.k(i2);
            ((zzzz) obj).zzb(pVar);
            pVar.m(i2);
        }
    }

    public static zzzz f() {
        return new zzzz();
    }

    public static zzzz zzwz() {
        return f;
    }

    public final void b(p pVar) throws IOException {
        if (pVar.e() == zzwz.zzg.zzcmf) {
            for (int i = this.f8817a - 1; i >= 0; i--) {
                pVar.zza(this.b[i] >>> 3, this.c[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.f8817a; i2++) {
            pVar.zza(this.b[i2] >>> 3, this.c[i2]);
        }
    }

    public final void c(int i, Object obj) {
        if (this.e) {
            int i2 = this.f8817a;
            int[] iArr = this.b;
            if (i2 == iArr.length) {
                int i3 = i2 + (i2 < 4 ? 8 : i2 >> 1);
                this.b = Arrays.copyOf(iArr, i3);
                this.c = Arrays.copyOf(this.c, i3);
            }
            int[] iArr2 = this.b;
            int i4 = this.f8817a;
            iArr2[i4] = i;
            this.c[i4] = obj;
            this.f8817a = i4 + 1;
            return;
        }
        throw new UnsupportedOperationException();
    }

    public final void e(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.f8817a; i2++) {
            p7.c(sb, i, String.valueOf(this.b[i2] >>> 3), this.c[i2]);
        }
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof zzzz)) {
            zzzz zzzzVar = (zzzz) obj;
            int i = this.f8817a;
            if (i == zzzzVar.f8817a) {
                int[] iArr = this.b;
                int[] iArr2 = zzzzVar.b;
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
                    Object[] objArr2 = zzzzVar.c;
                    int i3 = this.f8817a;
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
        int i = this.f8817a;
        int i2 = (i + 527) * 31;
        int[] iArr = this.b;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.c;
        int i7 = this.f8817a;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }

    public final void zzb(p pVar) throws IOException {
        if (this.f8817a == 0) {
            return;
        }
        if (pVar.e() == zzwz.zzg.zzcme) {
            for (int i = 0; i < this.f8817a; i++) {
                d(this.b[i], this.c[i], pVar);
            }
            return;
        }
        for (int i2 = this.f8817a - 1; i2 >= 0; i2--) {
            d(this.b[i2], this.c[i2], pVar);
        }
    }

    public final void zztm() {
        this.e = false;
    }

    public final int zzuo() {
        int zze;
        int i = this.d;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.f8817a; i3++) {
            int i4 = this.b[i3];
            int i5 = i4 >>> 3;
            int i6 = i4 & 7;
            if (i6 == 0) {
                zze = zzwi.zze(i5, ((Long) this.c[i3]).longValue());
            } else if (i6 == 1) {
                zze = zzwi.zzg(i5, ((Long) this.c[i3]).longValue());
            } else if (i6 == 2) {
                zze = zzwi.zzc(i5, (zzvv) this.c[i3]);
            } else if (i6 == 3) {
                zze = (zzwi.zzdf(i5) << 1) + ((zzzz) this.c[i3]).zzuo();
            } else if (i6 == 5) {
                zze = zzwi.zzo(i5, ((Integer) this.c[i3]).intValue());
            } else {
                throw new IllegalStateException(zzxk.zzvh());
            }
            i2 += zze;
        }
        this.d = i2;
        return i2;
    }

    public final int zzxb() {
        int i = this.d;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.f8817a; i3++) {
            i2 += zzwi.zzd(this.b[i3] >>> 3, (zzvv) this.c[i3]);
        }
        this.d = i2;
        return i2;
    }

    public zzzz(int i, int[] iArr, Object[] objArr, boolean z) {
        this.d = -1;
        this.f8817a = i;
        this.b = iArr;
        this.c = objArr;
        this.e = z;
    }
}
