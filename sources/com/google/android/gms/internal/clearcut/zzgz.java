package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.util.Arrays;
/* loaded from: classes7.dex */
public final class zzgz extends zzfu<zzgz> implements Cloneable {
    public byte[] h = zzgb.zzse;
    public String i = "";
    public byte[][] j = zzgb.zzsd;

    public zzgz() {
        this.zzrj = null;
        this.zzrs = -1;
    }

    @Override // com.google.android.gms.internal.clearcut.zzfu, com.google.android.gms.internal.clearcut.zzfz
    /* renamed from: a */
    public final zzgz clone() {
        try {
            zzgz zzgzVar = (zzgz) super.clone();
            byte[][] bArr = this.j;
            if (bArr != null && bArr.length > 0) {
                zzgzVar.j = (byte[][]) bArr.clone();
            }
            return zzgzVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzgz) {
            zzgz zzgzVar = (zzgz) obj;
            if (Arrays.equals(this.h, zzgzVar.h)) {
                String str = this.i;
                if (str == null) {
                    if (zzgzVar.i != null) {
                        return false;
                    }
                } else if (!str.equals(zzgzVar.i)) {
                    return false;
                }
                if (zzfy.zza(this.j, zzgzVar.j)) {
                    zzfw zzfwVar = this.zzrj;
                    if (zzfwVar == null || zzfwVar.isEmpty()) {
                        zzfw zzfwVar2 = zzgzVar.zzrj;
                        return zzfwVar2 == null || zzfwVar2.isEmpty();
                    }
                    return this.zzrj.equals(zzgzVar.zzrj);
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (((zzgz.class.getName().hashCode() + 527) * 31) + Arrays.hashCode(this.h)) * 31;
        String str = this.i;
        int i = 0;
        int hashCode2 = (((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + zzfy.zza(this.j)) * 31) + 1237) * 31;
        zzfw zzfwVar = this.zzrj;
        if (zzfwVar != null && !zzfwVar.isEmpty()) {
            i = this.zzrj.hashCode();
        }
        return hashCode2 + i;
    }

    @Override // com.google.android.gms.internal.clearcut.zzfu, com.google.android.gms.internal.clearcut.zzfz
    public final void zza(zzfs zzfsVar) throws IOException {
        if (!Arrays.equals(this.h, zzgb.zzse)) {
            zzfsVar.zza(1, this.h);
        }
        byte[][] bArr = this.j;
        if (bArr != null && bArr.length > 0) {
            int i = 0;
            while (true) {
                byte[][] bArr2 = this.j;
                if (i >= bArr2.length) {
                    break;
                }
                byte[] bArr3 = bArr2[i];
                if (bArr3 != null) {
                    zzfsVar.zza(2, bArr3);
                }
                i++;
            }
        }
        String str = this.i;
        if (str != null && !str.equals("")) {
            zzfsVar.zza(4, this.i);
        }
        super.zza(zzfsVar);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfu, com.google.android.gms.internal.clearcut.zzfz
    public final int zzen() {
        int zzen = super.zzen();
        if (!Arrays.equals(this.h, zzgb.zzse)) {
            zzen += zzfs.zzb(1, this.h);
        }
        byte[][] bArr = this.j;
        if (bArr != null && bArr.length > 0) {
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (true) {
                byte[][] bArr2 = this.j;
                if (i >= bArr2.length) {
                    break;
                }
                byte[] bArr3 = bArr2[i];
                if (bArr3 != null) {
                    i3++;
                    i2 += zzfs.zzh(bArr3);
                }
                i++;
            }
            zzen = zzen + i2 + (i3 * 1);
        }
        String str = this.i;
        return (str == null || str.equals("")) ? zzen : zzen + zzfs.zzb(4, this.i);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfu
    public final /* synthetic */ zzgz zzeo() throws CloneNotSupportedException {
        return (zzgz) clone();
    }

    @Override // com.google.android.gms.internal.clearcut.zzfu, com.google.android.gms.internal.clearcut.zzfz
    public final /* synthetic */ zzfz zzep() throws CloneNotSupportedException {
        return (zzgz) clone();
    }
}
