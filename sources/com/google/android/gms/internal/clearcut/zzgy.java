package com.google.android.gms.internal.clearcut;

import java.io.IOException;
/* loaded from: classes7.dex */
public final class zzgy extends zzfu<zzgy> implements Cloneable {
    public String[] h;
    public String[] i;
    public int[] j;
    public long[] k;
    public long[] l;

    public zzgy() {
        String[] strArr = zzgb.zzsc;
        this.h = strArr;
        this.i = strArr;
        this.j = zzgb.zzrx;
        long[] jArr = zzgb.zzry;
        this.k = jArr;
        this.l = jArr;
        this.zzrj = null;
        this.zzrs = -1;
    }

    @Override // com.google.android.gms.internal.clearcut.zzfu, com.google.android.gms.internal.clearcut.zzfz
    /* renamed from: a */
    public final zzgy clone() {
        try {
            zzgy zzgyVar = (zzgy) super.clone();
            String[] strArr = this.h;
            if (strArr != null && strArr.length > 0) {
                zzgyVar.h = (String[]) strArr.clone();
            }
            String[] strArr2 = this.i;
            if (strArr2 != null && strArr2.length > 0) {
                zzgyVar.i = (String[]) strArr2.clone();
            }
            int[] iArr = this.j;
            if (iArr != null && iArr.length > 0) {
                zzgyVar.j = (int[]) iArr.clone();
            }
            long[] jArr = this.k;
            if (jArr != null && jArr.length > 0) {
                zzgyVar.k = (long[]) jArr.clone();
            }
            long[] jArr2 = this.l;
            if (jArr2 != null && jArr2.length > 0) {
                zzgyVar.l = (long[]) jArr2.clone();
            }
            return zzgyVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzgy) {
            zzgy zzgyVar = (zzgy) obj;
            if (zzfy.equals(this.h, zzgyVar.h) && zzfy.equals(this.i, zzgyVar.i) && zzfy.equals(this.j, zzgyVar.j) && zzfy.equals(this.k, zzgyVar.k) && zzfy.equals(this.l, zzgyVar.l)) {
                zzfw zzfwVar = this.zzrj;
                if (zzfwVar == null || zzfwVar.isEmpty()) {
                    zzfw zzfwVar2 = zzgyVar.zzrj;
                    return zzfwVar2 == null || zzfwVar2.isEmpty();
                }
                return this.zzrj.equals(zzgyVar.zzrj);
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (((((((((((zzgy.class.getName().hashCode() + 527) * 31) + zzfy.hashCode(this.h)) * 31) + zzfy.hashCode(this.i)) * 31) + zzfy.hashCode(this.j)) * 31) + zzfy.hashCode(this.k)) * 31) + zzfy.hashCode(this.l)) * 31;
        zzfw zzfwVar = this.zzrj;
        return hashCode + ((zzfwVar == null || zzfwVar.isEmpty()) ? 0 : this.zzrj.hashCode());
    }

    @Override // com.google.android.gms.internal.clearcut.zzfu, com.google.android.gms.internal.clearcut.zzfz
    public final void zza(zzfs zzfsVar) throws IOException {
        String[] strArr = this.h;
        int i = 0;
        if (strArr != null && strArr.length > 0) {
            int i2 = 0;
            while (true) {
                String[] strArr2 = this.h;
                if (i2 >= strArr2.length) {
                    break;
                }
                String str = strArr2[i2];
                if (str != null) {
                    zzfsVar.zza(1, str);
                }
                i2++;
            }
        }
        String[] strArr3 = this.i;
        if (strArr3 != null && strArr3.length > 0) {
            int i3 = 0;
            while (true) {
                String[] strArr4 = this.i;
                if (i3 >= strArr4.length) {
                    break;
                }
                String str2 = strArr4[i3];
                if (str2 != null) {
                    zzfsVar.zza(2, str2);
                }
                i3++;
            }
        }
        int[] iArr = this.j;
        if (iArr != null && iArr.length > 0) {
            int i4 = 0;
            while (true) {
                int[] iArr2 = this.j;
                if (i4 >= iArr2.length) {
                    break;
                }
                zzfsVar.zzc(3, iArr2[i4]);
                i4++;
            }
        }
        long[] jArr = this.k;
        if (jArr != null && jArr.length > 0) {
            int i5 = 0;
            while (true) {
                long[] jArr2 = this.k;
                if (i5 >= jArr2.length) {
                    break;
                }
                zzfsVar.zzi(4, jArr2[i5]);
                i5++;
            }
        }
        long[] jArr3 = this.l;
        if (jArr3 != null && jArr3.length > 0) {
            while (true) {
                long[] jArr4 = this.l;
                if (i >= jArr4.length) {
                    break;
                }
                zzfsVar.zzi(5, jArr4[i]);
                i++;
            }
        }
        super.zza(zzfsVar);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfu, com.google.android.gms.internal.clearcut.zzfz
    public final int zzen() {
        long[] jArr;
        int[] iArr;
        int zzen = super.zzen();
        String[] strArr = this.h;
        int i = 0;
        if (strArr != null && strArr.length > 0) {
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            while (true) {
                String[] strArr2 = this.h;
                if (i2 >= strArr2.length) {
                    break;
                }
                String str = strArr2[i2];
                if (str != null) {
                    i4++;
                    i3 += zzfs.zzh(str);
                }
                i2++;
            }
            zzen = zzen + i3 + (i4 * 1);
        }
        String[] strArr3 = this.i;
        if (strArr3 != null && strArr3.length > 0) {
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            while (true) {
                String[] strArr4 = this.i;
                if (i5 >= strArr4.length) {
                    break;
                }
                String str2 = strArr4[i5];
                if (str2 != null) {
                    i7++;
                    i6 += zzfs.zzh(str2);
                }
                i5++;
            }
            zzen = zzen + i6 + (i7 * 1);
        }
        int[] iArr2 = this.j;
        if (iArr2 != null && iArr2.length > 0) {
            int i8 = 0;
            int i9 = 0;
            while (true) {
                iArr = this.j;
                if (i8 >= iArr.length) {
                    break;
                }
                i9 += zzfs.zzs(iArr[i8]);
                i8++;
            }
            zzen = zzen + i9 + (iArr.length * 1);
        }
        long[] jArr2 = this.k;
        if (jArr2 != null && jArr2.length > 0) {
            int i10 = 0;
            int i11 = 0;
            while (true) {
                jArr = this.k;
                if (i10 >= jArr.length) {
                    break;
                }
                i11 += zzfs.zzo(jArr[i10]);
                i10++;
            }
            zzen = zzen + i11 + (jArr.length * 1);
        }
        long[] jArr3 = this.l;
        if (jArr3 == null || jArr3.length <= 0) {
            return zzen;
        }
        int i12 = 0;
        while (true) {
            long[] jArr4 = this.l;
            if (i >= jArr4.length) {
                return zzen + i12 + (jArr4.length * 1);
            }
            i12 += zzfs.zzo(jArr4[i]);
            i++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.zzfu
    public final /* synthetic */ zzgy zzeo() throws CloneNotSupportedException {
        return (zzgy) clone();
    }

    @Override // com.google.android.gms.internal.clearcut.zzfu, com.google.android.gms.internal.clearcut.zzfz
    public final /* synthetic */ zzfz zzep() throws CloneNotSupportedException {
        return (zzgy) clone();
    }
}
