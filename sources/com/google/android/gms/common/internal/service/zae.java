package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
/* loaded from: classes6.dex */
public final class zae {
    public final PendingResult zaa(GoogleApiClient googleApiClient) {
        return googleApiClient.execute(new b(this, googleApiClient));
    }
}
