package com.google.android.gms.maps;

import android.app.Activity;
import android.app.Fragment;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.OnDelegateCreatedListener;
import com.google.android.gms.maps.internal.zzca;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;
@VisibleForTesting
/* loaded from: classes10.dex */
public final class o extends DeferredLifecycleHelper<n> {
    public final Fragment e;
    public OnDelegateCreatedListener<n> f;
    public Activity g;
    public final List<OnStreetViewPanoramaReadyCallback> h = new ArrayList();

    @VisibleForTesting
    public o(Fragment fragment) {
        this.e = fragment;
    }

    public static /* synthetic */ void g(o oVar, Activity activity) {
        oVar.g = activity;
        oVar.i();
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper
    public final void createDelegate(OnDelegateCreatedListener<n> onDelegateCreatedListener) {
        this.f = onDelegateCreatedListener;
        i();
    }

    public final void h(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        if (getDelegate() != null) {
            getDelegate().getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
        } else {
            this.h.add(onStreetViewPanoramaReadyCallback);
        }
    }

    public final void i() {
        if (this.g == null || this.f == null || getDelegate() != null) {
            return;
        }
        try {
            MapsInitializer.initialize(this.g);
            this.f.onDelegateCreated(new n(this.e, zzca.zza(this.g, null).zzh(ObjectWrapper.wrap(this.g))));
            for (OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback : this.h) {
                getDelegate().getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
            }
            this.h.clear();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        } catch (GooglePlayServicesNotAvailableException unused) {
        }
    }
}
