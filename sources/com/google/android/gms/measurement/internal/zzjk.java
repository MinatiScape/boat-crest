package com.google.android.gms.measurement.internal;

import android.util.Pair;
import androidx.annotation.WorkerThread;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
/* loaded from: classes10.dex */
public final class zzjk extends r3 {
    public String b;
    public boolean c;
    public long d;
    public final zzet zza;
    public final zzet zzb;
    public final zzet zzc;
    public final zzet zzd;
    public final zzet zze;

    public zzjk(zzkn zzknVar) {
        super(zzknVar);
        v zzm = this.zzs.zzm();
        zzm.getClass();
        this.zza = new zzet(zzm, "last_delete_stale", 0L);
        v zzm2 = this.zzs.zzm();
        zzm2.getClass();
        this.zzb = new zzet(zzm2, "backoff", 0L);
        v zzm3 = this.zzs.zzm();
        zzm3.getClass();
        this.zzc = new zzet(zzm3, "last_upload", 0L);
        v zzm4 = this.zzs.zzm();
        zzm4.getClass();
        this.zzd = new zzet(zzm4, "last_upload_attempt", 0L);
        v zzm5 = this.zzs.zzm();
        zzm5.getClass();
        this.zze = new zzet(zzm5, "midnight_offset", 0L);
    }

    @WorkerThread
    @Deprecated
    public final Pair<String, Boolean> b(String str) {
        zzg();
        long elapsedRealtime = this.zzs.zzav().elapsedRealtime();
        String str2 = this.b;
        if (str2 != null && elapsedRealtime < this.d) {
            return new Pair<>(str2, Boolean.valueOf(this.c));
        }
        this.d = elapsedRealtime + this.zzs.zzf().zzi(str, zzdw.zza);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.zzs.zzau());
            this.b = "";
            String id = advertisingIdInfo.getId();
            if (id != null) {
                this.b = id;
            }
            this.c = advertisingIdInfo.isLimitAdTrackingEnabled();
        } catch (Exception e) {
            this.zzs.zzay().zzc().zzb("Unable to get advertising id", e);
            this.b = "";
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair<>(this.b, Boolean.valueOf(this.c));
    }

    @WorkerThread
    public final Pair<String, Boolean> c(String str, zzag zzagVar) {
        if (zzagVar.zzj()) {
            return b(str);
        }
        return new Pair<>("", Boolean.FALSE);
    }

    @Override // com.google.android.gms.measurement.internal.r3
    public final boolean zzb() {
        return false;
    }

    @WorkerThread
    @Deprecated
    public final String zzf(String str) {
        zzg();
        String str2 = (String) b(str).first;
        MessageDigest d = zzku.d(MessageDigestAlgorithms.MD5);
        if (d == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new BigInteger(1, d.digest(str2.getBytes())));
    }
}
