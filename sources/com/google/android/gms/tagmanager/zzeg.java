package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzsa;
import com.google.android.gms.internal.gtm.zzsd;
import com.google.android.gms.internal.gtm.zzse;
import com.google.android.gms.internal.gtm.zzsf;
import com.google.android.gms.internal.gtm.zzuj;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes10.dex */
public final class zzeg implements Runnable {
    public final Context zza;
    public final zzse zzb;
    public final String zzc;
    public final String zzd;
    public zzdg<com.google.android.gms.internal.gtm.zzai> zze;
    public volatile zzap zzf;
    public volatile String zzg;
    public volatile String zzh;

    public zzeg(Context context, String str, zzap zzapVar) {
        zzse zzseVar = new zzse();
        this.zza = context;
        this.zzb = zzseVar;
        this.zzc = str;
        this.zzf = zzapVar;
        String valueOf = String.valueOf(str);
        String concat = valueOf.length() != 0 ? "/r?id=".concat(valueOf) : new String("/r?id=");
        this.zzd = concat;
        this.zzg = concat;
        this.zzh = null;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzam zzamVar;
        Clock clock;
        long j;
        boolean zzv;
        com.google.android.gms.internal.gtm.zzai zzaiVar;
        com.google.android.gms.internal.gtm.zzai zzaiVar2;
        zzam zzamVar2;
        if (this.zze != null) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.zza.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                zzdh.zzb.zzd("Start loading resource from network ...");
                String zza = this.zzf.zza();
                String str = this.zzg;
                StringBuilder sb = new StringBuilder(zza.length() + 12 + String.valueOf(str).length());
                sb.append(zza);
                sb.append(str);
                sb.append("&v=a65833898");
                String sb2 = sb.toString();
                if (this.zzh != null && !this.zzh.trim().equals("")) {
                    String valueOf = String.valueOf(sb2);
                    String str2 = this.zzh;
                    StringBuilder sb3 = new StringBuilder(valueOf.length() + 4 + String.valueOf(str2).length());
                    sb3.append(valueOf);
                    sb3.append("&pv=");
                    sb3.append(str2);
                    sb2 = sb3.toString();
                }
                int zze = zzea.zza().zze();
                InputStream inputStream = null;
                if (zze != 0) {
                    if (zze == 3) {
                        sb2 = String.valueOf(sb2).concat("&gtm_debug=x");
                    }
                    zzsd zza2 = zzse.zza();
                    try {
                        try {
                            try {
                                inputStream = zza2.zza(sb2);
                            } catch (IOException e) {
                                String message = e.getMessage();
                                StringBuilder sb4 = new StringBuilder(String.valueOf(sb2).length() + 40 + String.valueOf(message).length());
                                sb4.append("Error when loading resources from url: ");
                                sb4.append(sb2);
                                sb4.append(HexStringBuilder.DEFAULT_SEPARATOR);
                                sb4.append(message);
                                zzdh.zzd(sb4.toString(), e);
                                this.zze.zza(2);
                                zza2.zzb();
                                return;
                            }
                        } catch (zzsf unused) {
                            String valueOf2 = String.valueOf(sb2);
                            zzdh.zzc(valueOf2.length() != 0 ? "Error when loading resource for url: ".concat(valueOf2) : new String("Error when loading resource for url: "));
                            this.zze.zza(4);
                        } catch (FileNotFoundException unused2) {
                            String str3 = this.zzc;
                            StringBuilder sb5 = new StringBuilder(String.valueOf(sb2).length() + 79 + String.valueOf(str3).length());
                            sb5.append("No data is retrieved from the given url: ");
                            sb5.append(sb2);
                            sb5.append(". Make sure container_id: ");
                            sb5.append(str3);
                            sb5.append(" is correct.");
                            zzdh.zzc(sb5.toString());
                            this.zze.zza(3);
                            zza2.zzb();
                            return;
                        }
                        try {
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            zzsa.zzc(inputStream, byteArrayOutputStream);
                            com.google.android.gms.internal.gtm.zzai zzg = com.google.android.gms.internal.gtm.zzai.zzg(byteArrayOutputStream.toByteArray(), zzuj.zzb());
                            String valueOf3 = String.valueOf(zzg);
                            StringBuilder sb6 = new StringBuilder(valueOf3.length() + 43);
                            sb6.append("Successfully loaded supplemented resource: ");
                            sb6.append(valueOf3);
                            String sb7 = sb6.toString();
                            zzbg zzbgVar = zzdh.zzb;
                            zzbgVar.zzd(sb7);
                            if (!zzg.zzm() && zzg.zza() == 0) {
                                String valueOf4 = String.valueOf(this.zzc);
                                zzbgVar.zzd(valueOf4.length() != 0 ? "No change for container: ".concat(valueOf4) : new String("No change for container: "));
                            }
                            zzdg<com.google.android.gms.internal.gtm.zzai> zzdgVar = this.zze;
                            zzamVar = ((zzag) zzdgVar).zza.zzi;
                            zzamVar.zzd();
                            synchronized (((zzag) zzdgVar).zza) {
                                if (!zzg.zzm()) {
                                    zzaiVar = ((zzag) zzdgVar).zza.zzn;
                                    if (!zzaiVar.zzm()) {
                                        zzdh.zza("Current resource is null; network resource is also null");
                                        zzamVar2 = ((zzag) zzdgVar).zza.zzi;
                                        ((zzag) zzdgVar).zza.zzr(zzamVar2.zzb());
                                    } else {
                                        com.google.android.gms.internal.gtm.zzah zzZ = zzg.zzZ();
                                        zzaiVar2 = ((zzag) zzdgVar).zza.zzn;
                                        zzZ.zzc(zzaiVar2.zzc());
                                        zzg = zzZ.zzC();
                                    }
                                }
                                zzal zzalVar = ((zzag) zzdgVar).zza;
                                clock = zzalVar.zza;
                                zzalVar.zzu(zzg, clock.currentTimeMillis(), false);
                                j = ((zzag) zzdgVar).zza.zzo;
                                StringBuilder sb8 = new StringBuilder(58);
                                sb8.append("setting refresh time to current time: ");
                                sb8.append(j);
                                zzbgVar.zzd(sb8.toString());
                                zzv = ((zzag) zzdgVar).zza.zzv();
                                if (!zzv) {
                                    ((zzag) zzdgVar).zza.zzt(zzg);
                                }
                            }
                            zza2.zzb();
                            zzbgVar.zzd("Load resource from network finished.");
                            return;
                        } catch (IOException e2) {
                            String message2 = e2.getMessage();
                            StringBuilder sb9 = new StringBuilder(String.valueOf(sb2).length() + 51 + String.valueOf(message2).length());
                            sb9.append("Error when parsing downloaded resources from url: ");
                            sb9.append(sb2);
                            sb9.append(HexStringBuilder.DEFAULT_SEPARATOR);
                            sb9.append(message2);
                            zzdh.zzd(sb9.toString(), e2);
                            this.zze.zza(3);
                            zza2.zzb();
                            return;
                        }
                    } catch (Throwable th) {
                        zza2.zzb();
                        throw th;
                    }
                }
                throw null;
            }
            zzdh.zzb.zzd("...no network connectivity");
            this.zze.zza(1);
            return;
        }
        throw new IllegalStateException("callback must be set before execute");
    }

    @VisibleForTesting
    public final void zza(String str) {
        if (str == null) {
            this.zzg = this.zzd;
            return;
        }
        zzdh.zzb.zza(str.length() != 0 ? "Setting CTFE URL path: ".concat(str) : new String("Setting CTFE URL path: "));
        this.zzg = str;
    }

    public final void zzb(zzdg<com.google.android.gms.internal.gtm.zzai> zzdgVar) {
        this.zze = zzdgVar;
    }

    @VisibleForTesting
    public final void zzc(String str) {
        String valueOf = String.valueOf(str);
        zzdh.zzb.zza(valueOf.length() != 0 ? "Setting previous container version: ".concat(valueOf) : new String("Setting previous container version: "));
        this.zzh = str;
    }
}
