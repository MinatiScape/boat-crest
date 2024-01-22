package com.google.android.libraries.places.internal;

import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.tasks.OnTokenCanceledListener;
/* loaded from: classes10.dex */
final /* synthetic */ class zzac implements OnTokenCanceledListener {
    private final JsonObjectRequest zza;

    private zzac(JsonObjectRequest jsonObjectRequest) {
        this.zza = jsonObjectRequest;
    }

    public static OnTokenCanceledListener zza(JsonObjectRequest jsonObjectRequest) {
        return new zzac(jsonObjectRequest);
    }

    @Override // com.google.android.gms.tasks.OnTokenCanceledListener
    public final void onCanceled() {
        this.zza.cancel();
    }
}
