package com.google.android.libraries.places.internal;

import android.content.Context;
import android.net.wifi.WifiManager;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.libraries.places.api.net.PlacesClient;
/* loaded from: classes10.dex */
public final class zzcv implements zzcw {
    private final zzdb zza;
    private final Context zzb;
    private final zzdj zzc;

    private zzcv(Context context, zzdb zzdbVar, zzdj zzdjVar) {
        this.zza = zzdbVar;
        this.zzb = context;
        this.zzc = zzdjVar;
    }

    public static zzcz zza() {
        return new zzcx();
    }

    private final RequestQueue zzc() {
        return (RequestQueue) zzvs.zza(Volley.newRequestQueue(zzdg.zza(this.zzb)), "Cannot return null from a non-@Nullable @Provides method");
    }

    private final zzt zzd() {
        return zzw.zza(new zzdl(this.zzb), this.zzc, this.zza);
    }

    @Override // com.google.android.libraries.places.internal.zzcw
    public final PlacesClient zzb() {
        return zzba.zza(zzcg.zza(this.zza, new zzdr(this.zzb), zzae.zza(zzc(), new zzbu()), zzak.zza(zzc()), zzd(), zza.zza(), zzbf.zza(), zzbj.zza(zzch.zza()), zzbn.zza(), zzbr.zza(zzch.zza())), zzn.zza(zza.zza(), (FusedLocationProviderClient) zzvs.zza(LocationServices.getFusedLocationProviderClient(zzdg.zza(this.zzb)), "Cannot return null from a non-@Nullable @Provides method"), new zzcr(new zzco())), zzu.zza((WifiManager) zzdg.zza(this.zzb).getSystemService("wifi"), zza.zza()), zzd(), zza.zza());
    }
}
