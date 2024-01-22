package com.google.android.libraries.places.internal;

import java.io.IOException;
import java.math.RoundingMode;
import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public class zzhe extends zzha {
    public final zzhd zza;
    @NullableDecl
    private final Character zzb;

    public zzhe(String str, String str2, @NullableDecl Character ch) {
        this(new zzhd(str, str2.toCharArray()), ch);
    }

    public boolean equals(@NullableDecl Object obj) {
        if (obj instanceof zzhe) {
            zzhe zzheVar = (zzhe) obj;
            if (this.zza.equals(zzheVar.zza) && zzfn.zza(this.zzb, zzheVar.zzb)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return this.zza.hashCode() ^ Arrays.hashCode(new Object[]{this.zzb});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("BaseEncoding.");
        sb.append(this.zza.toString());
        if (8 % this.zza.zzb != 0) {
            if (this.zzb == null) {
                sb.append(".omitPadding()");
            } else {
                sb.append(".withPadChar('");
                sb.append(this.zzb);
                sb.append("')");
            }
        }
        return sb.toString();
    }

    @Override // com.google.android.libraries.places.internal.zzha
    public final int zza(int i) {
        zzhd zzhdVar = this.zza;
        return zzhdVar.zzc * zzpu.zza(i, zzhdVar.zzd, RoundingMode.CEILING);
    }

    public final void zzb(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        zzft.zza(appendable);
        zzft.zza(i, i + i2, bArr.length);
        int i3 = 0;
        zzft.zza(i2 <= this.zza.zzd);
        long j = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            j = (j | (bArr[i + i4] & 255)) << 8;
        }
        int i5 = ((i2 + 1) << 3) - this.zza.zzb;
        while (i3 < (i2 << 3)) {
            zzhd zzhdVar = this.zza;
            appendable.append(zzhdVar.zza(((int) (j >>> (i5 - i3))) & zzhdVar.zza));
            i3 += this.zza.zzb;
        }
        if (this.zzb != null) {
            while (i3 < (this.zza.zzd << 3)) {
                appendable.append(this.zzb.charValue());
                i3 += this.zza.zzb;
            }
        }
    }

    public zzhe(zzhd zzhdVar, @NullableDecl Character ch) {
        this.zza = (zzhd) zzft.zza(zzhdVar);
        if (ch == null || !zzhdVar.zza(ch.charValue())) {
            this.zzb = ch;
            return;
        }
        throw new IllegalArgumentException(zzfz.zza("Padding character %s was already in alphabet", ch));
    }

    @Override // com.google.android.libraries.places.internal.zzha
    public void zza(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        zzft.zza(appendable);
        int i3 = 0;
        zzft.zza(0, i2, bArr.length);
        while (i3 < i2) {
            zzb(appendable, bArr, i3, Math.min(this.zza.zzd, i2 - i3));
            i3 += this.zza.zzd;
        }
    }
}
