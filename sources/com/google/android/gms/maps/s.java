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
import com.google.android.gms.maps.internal.zzca;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;
@VisibleForTesting
/* loaded from: classes10.dex */
public final class s extends DeferredLifecycleHelper<q> {
    public final ViewGroup e;
    public final Context f;
    public OnDelegateCreatedListener<q> g;
    @Nullable
    public final StreetViewPanoramaOptions h;
    public final List<OnStreetViewPanoramaReadyCallback> i = new ArrayList();

    @VisibleForTesting
    public s(ViewGroup viewGroup, Context context, @Nullable StreetViewPanoramaOptions streetViewPanoramaOptions) {
        this.e = viewGroup;
        this.f = context;
        this.h = streetViewPanoramaOptions;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper
    public final void createDelegate(OnDelegateCreatedListener<q> onDelegateCreatedListener) {
        this.g = onDelegateCreatedListener;
        h();
    }

    public final void g(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        if (getDelegate() != null) {
            getDelegate().getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
        } else {
            this.i.add(onStreetViewPanoramaReadyCallback);
        }
    }

    public final void h() {
        if (this.g == null || getDelegate() != null) {
            return;
        }
        try {
            MapsInitializer.initialize(this.f);
            this.g.onDelegateCreated(new q(this.e, zzca.zza(this.f, null).zzi(ObjectWrapper.wrap(this.f), this.h)));
            for (OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback : this.i) {
                getDelegate().getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
            }
            this.i.clear();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        } catch (GooglePlayServicesNotAvailableException unused) {
        }
    }
}
