package com.google.android.libraries.places.internal;

import android.content.Context;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.android.libraries.places.internal.zzhh;
/* loaded from: classes10.dex */
public final class zzdl implements zzdn {
    private final Transport<zzhh.zza> zza;

    public zzdl(Context context) {
        TransportRuntime.initialize(context.getApplicationContext());
        this.zza = TransportRuntime.getInstance().newFactory("cct").getTransport("LE", zzhh.zza.class, zzdo.zza);
    }

    @Override // com.google.android.libraries.places.internal.zzdn
    public final void zza(zzhh.zza zzaVar) {
        this.zza.send(Event.ofData(zzaVar));
    }
}
