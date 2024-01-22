package com.google.android.gms.maps;

import android.content.Context;
import android.os.RemoteException;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.OnDelegateCreatedListener;
import com.google.android.gms.maps.internal.IMapViewDelegate;
import com.google.android.gms.maps.internal.zzca;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;
@VisibleForTesting
/* loaded from: classes10.dex */
public final class h extends DeferredLifecycleHelper<g> {
    public final ViewGroup e;
    public final Context f;
    public OnDelegateCreatedListener<g> g;
    @Nullable
    public final GoogleMapOptions h;
    public final List<OnMapReadyCallback> i = new ArrayList();

    @VisibleForTesting
    public h(ViewGroup viewGroup, Context context, @Nullable GoogleMapOptions googleMapOptions) {
        this.e = viewGroup;
        this.f = context;
        this.h = googleMapOptions;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper
    public final void createDelegate(OnDelegateCreatedListener<g> onDelegateCreatedListener) {
        this.g = onDelegateCreatedListener;
        h();
    }

    public final void g(OnMapReadyCallback onMapReadyCallback) {
        if (getDelegate() != null) {
            getDelegate().getMapAsync(onMapReadyCallback);
        } else {
            this.i.add(onMapReadyCallback);
        }
    }

    public final void h() {
        if (this.g == null || getDelegate() != null) {
            return;
        }
        try {
            MapsInitializer.initialize(this.f);
            IMapViewDelegate zzg = zzca.zza(this.f, null).zzg(ObjectWrapper.wrap(this.f), this.h);
            if (zzg == null) {
                return;
            }
            this.g.onDelegateCreated(new g(this.e, zzg));
            for (OnMapReadyCallback onMapReadyCallback : this.i) {
                getDelegate().getMapAsync(onMapReadyCallback);
            }
            this.i.clear();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        } catch (GooglePlayServicesNotAvailableException unused) {
        }
    }
}
