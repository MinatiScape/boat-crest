package com.google.android.gms.tagmanager;

import android.content.Context;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzrs;
import com.google.android.gms.internal.gtm.zzrz;
import com.google.android.gms.internal.gtm.zzsa;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
@VisibleForTesting
/* loaded from: classes10.dex */
public class Container {
    public final Context zza;
    public final String zzb;
    public final DataLayer zzc;
    public zzeu zzd;
    public Map<String, FunctionCallMacroCallback> zze;
    public Map<String, FunctionCallTagCallback> zzf;
    public volatile long zzg;
    public volatile String zzh;

    /* loaded from: classes10.dex */
    public interface FunctionCallMacroCallback {
        @RecentlyNonNull
        Object getValue(@RecentlyNonNull String str, @RecentlyNonNull Map<String, Object> map);
    }

    /* loaded from: classes10.dex */
    public interface FunctionCallTagCallback {
        void execute(@RecentlyNonNull String str, @RecentlyNonNull Map<String, Object> map);
    }

    public Container(Context context, DataLayer dataLayer, String str, long j, com.google.android.gms.internal.gtm.zzai zzaiVar) {
        this.zze = new HashMap();
        this.zzf = new HashMap();
        this.zzh = "";
        this.zza = context;
        this.zzc = dataLayer;
        this.zzb = str;
        this.zzg = j;
        com.google.android.gms.internal.gtm.zzaa zzc = zzaiVar.zzc();
        Objects.requireNonNull(zzc);
        try {
            zzg(zzsa.zzb(zzc));
        } catch (zzrz e) {
            String valueOf = String.valueOf(zzc);
            String exc = e.toString();
            StringBuilder sb = new StringBuilder(valueOf.length() + 46 + String.valueOf(exc).length());
            sb.append("Not loading resource: ");
            sb.append(valueOf);
            sb.append(" because it is invalid: ");
            sb.append(exc);
            zzdh.zza(sb.toString());
        }
        if (zzaiVar.zza() != 0) {
            com.google.android.gms.internal.gtm.zzag[] zzagVarArr = (com.google.android.gms.internal.gtm.zzag[]) zzaiVar.zzi().toArray(new com.google.android.gms.internal.gtm.zzag[0]);
            zzeu zzf = zzf();
            if (zzf == null) {
                zzdh.zza("evaluateTags called for closed container.");
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (com.google.android.gms.internal.gtm.zzag zzagVar : zzagVarArr) {
                arrayList.add(zzagVar);
            }
            zzf.zze(arrayList);
        }
    }

    public boolean getBoolean(@RecentlyNonNull String str) {
        zzeu zzf = zzf();
        if (zzf == null) {
            zzdh.zza("getBoolean called for closed container.");
            return zzfv.zzf().booleanValue();
        }
        try {
            return zzfv.zzg(zzfv.zzl(zzf.zza(str).zza())).booleanValue();
        } catch (Exception e) {
            String message = e.getMessage();
            StringBuilder sb = new StringBuilder(String.valueOf(message).length() + 66);
            sb.append("Calling getBoolean() threw an exception: ");
            sb.append(message);
            sb.append(" Returning default value.");
            zzdh.zza(sb.toString());
            return zzfv.zzf().booleanValue();
        }
    }

    @RecentlyNonNull
    public String getContainerId() {
        return this.zzb;
    }

    public double getDouble(@RecentlyNonNull String str) {
        zzeu zzf = zzf();
        if (zzf == null) {
            zzdh.zza("getDouble called for closed container.");
            return zzfv.zzh().doubleValue();
        }
        try {
            return zzfv.zzi(zzfv.zzl(zzf.zza(str).zza())).doubleValue();
        } catch (Exception e) {
            String message = e.getMessage();
            StringBuilder sb = new StringBuilder(String.valueOf(message).length() + 65);
            sb.append("Calling getDouble() threw an exception: ");
            sb.append(message);
            sb.append(" Returning default value.");
            zzdh.zza(sb.toString());
            return zzfv.zzh().doubleValue();
        }
    }

    public long getLastRefreshTime() {
        return this.zzg;
    }

    public long getLong(@RecentlyNonNull String str) {
        zzeu zzf = zzf();
        if (zzf == null) {
            zzdh.zza("getLong called for closed container.");
            return zzfv.zzj().longValue();
        }
        try {
            return zzfv.zzk(zzfv.zzl(zzf.zza(str).zza())).longValue();
        } catch (Exception e) {
            String message = e.getMessage();
            StringBuilder sb = new StringBuilder(String.valueOf(message).length() + 63);
            sb.append("Calling getLong() threw an exception: ");
            sb.append(message);
            sb.append(" Returning default value.");
            zzdh.zza(sb.toString());
            return zzfv.zzj().longValue();
        }
    }

    @RecentlyNonNull
    public String getString(@RecentlyNonNull String str) {
        zzeu zzf = zzf();
        if (zzf == null) {
            zzdh.zza("getString called for closed container.");
            return zzfv.zzm();
        }
        try {
            return zzfv.zzn(zzfv.zzl(zzf.zza(str).zza()));
        } catch (Exception e) {
            String message = e.getMessage();
            StringBuilder sb = new StringBuilder(String.valueOf(message).length() + 65);
            sb.append("Calling getString() threw an exception: ");
            sb.append(message);
            sb.append(" Returning default value.");
            zzdh.zza(sb.toString());
            return zzfv.zzm();
        }
    }

    public boolean isDefault() {
        return getLastRefreshTime() == 0;
    }

    public void registerFunctionCallMacroCallback(@RecentlyNonNull String str, @RecentlyNonNull FunctionCallMacroCallback functionCallMacroCallback) {
        Objects.requireNonNull(functionCallMacroCallback, "Macro handler must be non-null");
        synchronized (this.zze) {
            this.zze.put(str, functionCallMacroCallback);
        }
    }

    public void registerFunctionCallTagCallback(@RecentlyNonNull String str, @RecentlyNonNull FunctionCallTagCallback functionCallTagCallback) {
        Objects.requireNonNull(functionCallTagCallback, "Tag callback must be non-null");
        synchronized (this.zzf) {
            this.zzf.put(str, functionCallTagCallback);
        }
    }

    public void unregisterFunctionCallMacroCallback(@RecentlyNonNull String str) {
        synchronized (this.zze) {
            this.zze.remove(str);
        }
    }

    public void unregisterFunctionCallTagCallback(@RecentlyNonNull String str) {
        synchronized (this.zzf) {
            this.zzf.remove(str);
        }
    }

    @VisibleForTesting
    public final FunctionCallMacroCallback zza(String str) {
        FunctionCallMacroCallback functionCallMacroCallback;
        synchronized (this.zze) {
            functionCallMacroCallback = this.zze.get(str);
        }
        return functionCallMacroCallback;
    }

    @RecentlyNonNull
    @VisibleForTesting
    public final FunctionCallTagCallback zzb(@RecentlyNonNull String str) {
        FunctionCallTagCallback functionCallTagCallback;
        synchronized (this.zzf) {
            functionCallTagCallback = this.zzf.get(str);
        }
        return functionCallTagCallback;
    }

    @RecentlyNonNull
    @VisibleForTesting
    public final String zzc() {
        return this.zzh;
    }

    @VisibleForTesting
    public final void zzd(@RecentlyNonNull String str) {
        zzeu zzf = zzf();
        if (zzf == null) {
            zzdh.zza("evaluateTags called for closed container.");
        } else {
            zzf.zzc(str);
        }
    }

    public final void zze() {
        this.zzd = null;
    }

    public final synchronized zzeu zzf() {
        return this.zzd;
    }

    public final void zzg(zzrs zzrsVar) {
        this.zzh = zzrsVar.zzb();
        zzh(new zzeu((Context) Preconditions.checkNotNull(this.zza), zzrsVar, (DataLayer) Preconditions.checkNotNull(this.zzc), new zzv(this, null), new zzx(this, null), new zzdl(), null));
        if (getBoolean("_gtm.loadEventEnabled")) {
            this.zzc.pushEvent("gtm.load", DataLayer.mapOf("gtm.id", Preconditions.checkNotNull(this.zzb)));
        }
    }

    public final synchronized void zzh(zzeu zzeuVar) {
        this.zzd = zzeuVar;
    }

    public Container(Context context, DataLayer dataLayer, String str, long j, zzrs zzrsVar) {
        this.zze = new HashMap();
        this.zzf = new HashMap();
        this.zzh = "";
        this.zza = context;
        this.zzc = dataLayer;
        this.zzb = str;
        this.zzg = 0L;
        zzg(zzrsVar);
    }
}
