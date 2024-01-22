package com.google.android.gms.internal.gtm;

import com.google.android.gms.common.internal.Preconditions;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
/* loaded from: classes8.dex */
public final class zzfd {
    public final /* synthetic */ zzfe zza;
    public int zzb;
    public final ByteArrayOutputStream zzc = new ByteArrayOutputStream();

    public zzfd(zzfe zzfeVar) {
        this.zza = zzfeVar;
    }

    public final int zza() {
        return this.zzb;
    }

    public final boolean zzb(zzex zzexVar) {
        byte[] bArr;
        Preconditions.checkNotNull(zzexVar);
        int i = this.zzb;
        this.zza.zzw();
        if (i + 1 > zzct.zzg()) {
            return false;
        }
        String zza = this.zza.zza(zzexVar, false);
        if (zza == null) {
            this.zza.zzz().zzb(zzexVar, "Error formatting hit");
            return true;
        }
        byte[] bytes = zza.getBytes();
        int length = bytes.length;
        this.zza.zzw();
        if (length > zzct.zzf()) {
            this.zza.zzz().zzb(zzexVar, "Hit size exceeds the maximum size limit");
            return true;
        }
        if (this.zzc.size() > 0) {
            length++;
        }
        int size = this.zzc.size();
        this.zza.zzw();
        if (size + length > zzeu.zzA.zzb().intValue()) {
            return false;
        }
        try {
            if (this.zzc.size() > 0) {
                ByteArrayOutputStream byteArrayOutputStream = this.zzc;
                bArr = zzfe.zza;
                byteArrayOutputStream.write(bArr);
            }
            this.zzc.write(bytes);
            this.zzb++;
            return true;
        } catch (IOException e) {
            this.zza.zzK("Failed to write payload when batching hits", e);
            return true;
        }
    }

    public final byte[] zzc() {
        return this.zzc.toByteArray();
    }
}
