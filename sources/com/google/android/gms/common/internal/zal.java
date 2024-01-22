package com.google.android.gms.common.internal;

import android.content.Context;
import android.util.SparseIntArray;
import androidx.annotation.NonNull;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
/* loaded from: classes6.dex */
public final class zal {

    /* renamed from: a  reason: collision with root package name */
    public final SparseIntArray f8342a;
    public GoogleApiAvailabilityLight b;

    public zal() {
        this(GoogleApiAvailability.getInstance());
    }

    public final int zaa(Context context, int i) {
        return this.f8342a.get(i, -1);
    }

    public final int zab(@NonNull Context context, @NonNull Api.Client client) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(client);
        int i = 0;
        if (client.requiresGooglePlayServices()) {
            int minApkVersion = client.getMinApkVersion();
            int zaa = zaa(context, minApkVersion);
            if (zaa == -1) {
                int i2 = 0;
                while (true) {
                    if (i2 >= this.f8342a.size()) {
                        i = -1;
                        break;
                    }
                    int keyAt = this.f8342a.keyAt(i2);
                    if (keyAt > minApkVersion && this.f8342a.get(keyAt) == 0) {
                        break;
                    }
                    i2++;
                }
                zaa = i == -1 ? this.b.isGooglePlayServicesAvailable(context, minApkVersion) : i;
                this.f8342a.put(minApkVersion, zaa);
            }
            return zaa;
        }
        return 0;
    }

    public final void zac() {
        this.f8342a.clear();
    }

    public zal(@NonNull GoogleApiAvailabilityLight googleApiAvailabilityLight) {
        this.f8342a = new SparseIntArray();
        Preconditions.checkNotNull(googleApiAvailabilityLight);
        this.b = googleApiAvailabilityLight;
    }
}
