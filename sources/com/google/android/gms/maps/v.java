package com.google.android.gms.maps;

import android.app.Activity;
import android.os.RemoteException;
import androidx.fragment.app.Fragment;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.OnDelegateCreatedListener;
import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import com.google.android.gms.maps.internal.zzca;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;
@VisibleForTesting
/* loaded from: classes10.dex */
public final class v extends DeferredLifecycleHelper<u> {
    public final Fragment e;
    public OnDelegateCreatedListener<u> f;
    public Activity g;
    public final List<OnMapReadyCallback> h = new ArrayList();

    @VisibleForTesting
    public v(Fragment fragment) {
        this.e = fragment;
    }

    public static /* synthetic */ void g(v vVar, Activity activity) {
        vVar.g = activity;
        vVar.i();
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper
    public final void createDelegate(OnDelegateCreatedListener<u> onDelegateCreatedListener) {
        this.f = onDelegateCreatedListener;
        i();
    }

    public final void h(OnMapReadyCallback onMapReadyCallback) {
        if (getDelegate() != null) {
            getDelegate().getMapAsync(onMapReadyCallback);
        } else {
            this.h.add(onMapReadyCallback);
        }
    }

    public final void i() {
        if (this.g == null || this.f == null || getDelegate() != null) {
            return;
        }
        try {
            MapsInitializer.initialize(this.g);
            IMapFragmentDelegate zzf = zzca.zza(this.g, null).zzf(ObjectWrapper.wrap(this.g));
            if (zzf == null) {
                return;
            }
            this.f.onDelegateCreated(new u(this.e, zzf));
            for (OnMapReadyCallback onMapReadyCallback : this.h) {
                getDelegate().getMapAsync(onMapReadyCallback);
            }
            this.h.clear();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        } catch (GooglePlayServicesNotAvailableException unused) {
        }
    }
}
