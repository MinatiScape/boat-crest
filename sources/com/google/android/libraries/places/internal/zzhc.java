package com.google.android.libraries.places.internal;

import java.io.IOException;
/* loaded from: classes10.dex */
final class zzhc extends zzhe {
    private final char[] zzb;

    public zzhc(String str, String str2) {
        this(new zzhd(str, str2.toCharArray()));
    }

    @Override // com.google.android.libraries.places.internal.zzhe, com.google.android.libraries.places.internal.zzha
    public final void zza(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        zzft.zza(appendable);
        zzft.zza(0, i2, bArr.length);
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = bArr[i3] & 255;
            appendable.append(this.zzb[i4]);
            appendable.append(this.zzb[i4 | 256]);
        }
    }

    private zzhc(zzhd zzhdVar) {
        super(zzhdVar, null);
        this.zzb = new char[512];
        zzft.zza(zzhd.zza(zzhdVar).length == 16);
        for (int i = 0; i < 256; i++) {
            this.zzb[i] = zzhdVar.zza(i >>> 4);
            this.zzb[i | 256] = zzhdVar.zza(i & 15);
        }
    }
}
