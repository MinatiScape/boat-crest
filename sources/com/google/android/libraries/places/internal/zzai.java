package com.google.android.libraries.places.internal;

import com.android.volley.toolbox.ImageRequest;
import com.google.android.gms.tasks.OnTokenCanceledListener;
/* loaded from: classes10.dex */
final /* synthetic */ class zzai implements OnTokenCanceledListener {
    private final ImageRequest zza;

    private zzai(ImageRequest imageRequest) {
        this.zza = imageRequest;
    }

    public static OnTokenCanceledListener zza(ImageRequest imageRequest) {
        return new zzai(imageRequest);
    }

    @Override // com.google.android.gms.tasks.OnTokenCanceledListener
    public final void onCanceled() {
        this.zza.cancel();
    }
}
