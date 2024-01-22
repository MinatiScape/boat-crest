package com.google.android.gms.maps;

import android.app.Activity;
import android.app.Fragment;
import android.os.RemoteException;
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
public final class e extends DeferredLifecycleHelper<d> {
    public final Fragment e;
    public OnDelegateCreatedListener<d> f;
    public Activity g;
    public final List<OnMapReadyCallback> h = new ArrayList();

    @VisibleForTesting
    public e(Fragment fragment) {
        this.e = fragment;
    }

    public static /* synthetic */ void g(e eVar, Activity activity) {
        eVar.g = activity;
        eVar.i();
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper
    public final void createDelegate(OnDelegateCreatedListener<d> onDelegateCreatedListener) {
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
            this.f.onDelegateCreated(new d(this.e, zzf));
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
