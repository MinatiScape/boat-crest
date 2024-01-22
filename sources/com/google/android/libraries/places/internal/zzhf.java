package com.google.android.libraries.places.internal;

import java.io.IOException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes10.dex */
final class zzhf extends zzhe {
    public zzhf(String str, String str2, @NullableDecl Character ch) {
        this(new zzhd(str, str2.toCharArray()), ch);
    }

    @Override // com.google.android.libraries.places.internal.zzhe, com.google.android.libraries.places.internal.zzha
    public final void zza(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        zzft.zza(appendable);
        int i3 = 0;
        zzft.zza(0, i2, bArr.length);
        int i4 = i2;
        while (i4 >= 3) {
            int i5 = i3 + 1;
            int i6 = i5 + 1;
            int i7 = ((bArr[i3] & 255) << 16) | ((bArr[i5] & 255) << 8) | (bArr[i6] & 255);
            appendable.append(this.zza.zza(i7 >>> 18));
            appendable.append(this.zza.zza((i7 >>> 12) & 63));
            appendable.append(this.zza.zza((i7 >>> 6) & 63));
            appendable.append(this.zza.zza(i7 & 63));
            i4 -= 3;
            i3 = i6 + 1;
        }
        if (i3 < i2) {
            zzb(appendable, bArr, i3, i2 - i3);
        }
    }

    private zzhf(zzhd zzhdVar, @NullableDecl Character ch) {
        super(zzhdVar, ch);
        zzft.zza(zzhd.zza(zzhdVar).length == 64);
    }
}
