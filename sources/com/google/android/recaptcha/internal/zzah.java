package com.google.android.recaptcha.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.google.firebase.crashlytics.BuildConfig;
import java.util.Locale;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes10.dex */
public final class zzah {
    @NotNull
    public static final zzag zza = new zzag(null);
    @Nullable
    private static zzkx zzb;
    @NotNull
    private final String zzc;
    @NotNull
    private final zzr zzd;
    @NotNull
    private final zzll zze;
    private final long zzf;

    public zzah(@NotNull zzaf zzafVar, @NotNull String str, @NotNull zzr zzrVar) {
        this.zzc = str;
        this.zzd = zzrVar;
        zzll zzi = zzlo.zzi();
        this.zze = zzi;
        this.zzf = System.currentTimeMillis();
        zzi.zzq(zzafVar.zza());
        zzi.zze(zzafVar.zzb());
        zzi.zzs(zzafVar.zzc());
        zzi.zzv(zzafVar.zzd());
        zzi.zzu(zzkr.zzc(zzkr.zzb(System.currentTimeMillis())));
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0047 -> B:15:0x0047). Please submit an issue!!! */
    private static final zzkx zzd(Context context) {
        String str;
        String str2 = "unknown";
        if (Build.VERSION.SDK_INT >= 33) {
            int i = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.ApplicationInfoFlags.of(128L)).metaData.getInt("com.google.android.gms.version", -1);
            str = i == -1 ? "unknown" : String.valueOf(i);
        } else {
            int i2 = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getInt("com.google.android.gms.version", -1);
            if (i2 != -1) {
                str = String.valueOf(i2);
            }
        }
        try {
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 33) {
                str2 = String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.PackageInfoFlags.of(0L)).getLongVersionCode());
            } else if (i3 >= 28) {
                str2 = String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).getLongVersionCode());
            } else {
                str2 = String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        zzkw zzf = zzkx.zzf();
        zzf.zzd(Build.VERSION.SDK_INT);
        zzf.zzq(str);
        zzf.zzs(BuildConfig.VERSION_NAME);
        zzf.zzp(Build.MODEL);
        zzf.zzr(Build.MANUFACTURER);
        zzf.zze(str2);
        return (zzkx) zzf.zzj();
    }

    public final long zza() {
        return this.zzf;
    }

    @NotNull
    public final zzll zzb() {
        return this.zze;
    }

    @NotNull
    public final zzlo zzc(@NotNull int i, @Nullable zzla zzlaVar, @NotNull Context context) {
        zzll zzllVar = this.zze;
        zzllVar.zzp(System.currentTimeMillis() - this.zzf);
        zzllVar.zzw(i);
        if (zzlaVar != null) {
            this.zze.zzr(zzlaVar);
        }
        if (zzb == null) {
            zzb = zzd(context);
        }
        zzll zzllVar2 = this.zze;
        zzlz zzf = zzma.zzf();
        zzf.zzq(this.zzc);
        zzkx zzkxVar = zzb;
        if (zzkxVar == null) {
            zzkxVar = zzd(context);
        }
        zzf.zzd(zzkxVar);
        zzf.zzp(Locale.getDefault().getISO3Language());
        zzf.zze(Locale.getDefault().getISO3Country());
        zzllVar2.zzt((zzma) zzf.zzj());
        return (zzlo) this.zze.zzj();
    }
}
