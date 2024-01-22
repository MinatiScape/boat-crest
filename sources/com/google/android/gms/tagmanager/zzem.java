package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzfz;
import com.google.android.gms.internal.gtm.zzrl;
import com.google.android.gms.internal.gtm.zzuj;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
/* loaded from: classes10.dex */
public final class zzem implements zzak {
    public final Context zza;
    public final String zzb;
    public final ExecutorService zzc = zzfz.zza().zza(2);
    public zzdg<zzrl> zzd;

    public zzem(Context context, String str) {
        this.zza = context;
        this.zzb = str;
    }

    @Override // com.google.android.gms.common.api.Releasable
    public final synchronized void release() {
        this.zzc.shutdown();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x00b5 A[Catch: IOException -> 0x00e2, TryCatch #3 {IOException -> 0x00e2, blocks: (B:5:0x0042, B:6:0x004a, B:7:0x0062, B:9:0x0068, B:10:0x009f, B:16:0x00b5, B:18:0x00bd, B:19:0x00c1, B:21:0x00d6, B:22:0x00dc, B:12:0x00a4, B:14:0x00ab), top: B:28:0x0042, inners: #6, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00bd A[Catch: IOException -> 0x00e2, TRY_LEAVE, TryCatch #3 {IOException -> 0x00e2, blocks: (B:5:0x0042, B:6:0x004a, B:7:0x0062, B:9:0x0068, B:10:0x009f, B:16:0x00b5, B:18:0x00bd, B:19:0x00c1, B:21:0x00d6, B:22:0x00dc, B:12:0x00a4, B:14:0x00ab), top: B:28:0x0042, inners: #6, #5 }] */
    @Override // com.google.android.gms.tagmanager.zzak
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.internal.gtm.zzrs zza(int r11) {
        /*
            Method dump skipped, instructions count: 299
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzem.zza(int):com.google.android.gms.internal.gtm.zzrs");
    }

    @Override // com.google.android.gms.tagmanager.zzak
    public final void zzb() {
        this.zzc.execute(new zzek(this));
    }

    @Override // com.google.android.gms.tagmanager.zzak
    public final void zzc(zzrl zzrlVar) {
        this.zzc.execute(new zzel(this, zzrlVar));
    }

    @Override // com.google.android.gms.tagmanager.zzak
    public final void zzd(zzdg<zzrl> zzdgVar) {
        this.zzd = zzdgVar;
    }

    @VisibleForTesting
    public final File zze() {
        String valueOf = String.valueOf(this.zzb);
        return new File(this.zza.getDir("google_tagmanager", 0), valueOf.length() != 0 ? "resource_".concat(valueOf) : new String("resource_"));
    }

    @VisibleForTesting
    public final void zzf() {
        com.google.android.gms.internal.gtm.zzah zzahVar;
        if (this.zzd != null) {
            zzdh.zzb.zzd("Attempting to load resource from disk");
            if ((zzea.zza().zze() != 2 && zzea.zza().zze() != 3) || !this.zzb.equals(zzea.zza().zzc())) {
                try {
                    FileInputStream fileInputStream = new FileInputStream(zze());
                    try {
                        try {
                            zzrl zzg = zzrl.zzg(fileInputStream, zzuj.zzb());
                            if (!zzg.zzk() && !zzg.zzl()) {
                                throw new IllegalArgumentException("Resource and SupplementedResource are NULL.");
                            }
                            zzdg<zzrl> zzdgVar = this.zzd;
                            if (zzg.zzl()) {
                                zzahVar = zzg.zzd().zzZ();
                            } else {
                                com.google.android.gms.internal.gtm.zzaa zzc = zzg.zzc();
                                com.google.android.gms.internal.gtm.zzah zzd = com.google.android.gms.internal.gtm.zzai.zzd();
                                zzd.zzc(zzc);
                                zzd.zza();
                                zzd.zzb(zzc.zzo());
                                zzahVar = zzd;
                            }
                            ((zzae) zzdgVar).zza.zzu(zzahVar.zzC(), zzg.zza(), true);
                        } catch (Throwable th) {
                            try {
                                fileInputStream.close();
                            } catch (IOException unused) {
                                zzdh.zzc("Error closing stream for reading resource from disk");
                            }
                            throw th;
                        }
                    } catch (IOException unused2) {
                        this.zzd.zza(2);
                        zzdh.zzc("Failed to read the resource from disk");
                    } catch (IllegalArgumentException unused3) {
                        this.zzd.zza(2);
                        zzdh.zzc("Failed to read the resource from disk. The resource is inconsistent");
                    }
                    try {
                        fileInputStream.close();
                    } catch (IOException unused4) {
                        zzdh.zzc("Error closing stream for reading resource from disk");
                    }
                    zzdh.zzb.zzd("The Disk resource was successfully read.");
                    return;
                } catch (FileNotFoundException unused5) {
                    zzdh.zzb.zza("Failed to find the resource in the disk");
                    this.zzd.zza(1);
                    return;
                }
            }
            this.zzd.zza(1);
            return;
        }
        throw new IllegalStateException("Callback must be set before execute");
    }
}
