package com.google.android.gms.maps;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
/* loaded from: classes10.dex */
public class MapView extends FrameLayout {
    public final h h;

    public MapView(@NonNull Context context) {
        super(context);
        this.h = new h(this, context, null);
        setClickable(true);
    }

    public void getMapAsync(@NonNull OnMapReadyCallback onMapReadyCallback) {
        Preconditions.checkMainThread("getMapAsync() must be called on the main thread");
        Preconditions.checkNotNull(onMapReadyCallback, "callback must not be null.");
        this.h.g(onMapReadyCallback);
    }

    public void onCreate(@Nullable Bundle bundle) {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitAll().build());
        try {
            this.h.onCreate(bundle);
            if (this.h.getDelegate() == null) {
                DeferredLifecycleHelper.showGooglePlayUnavailableMessage(this);
            }
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public void onDestroy() {
        this.h.onDestroy();
    }

    public void onEnterAmbient(@Nullable Bundle bundle) {
        Preconditions.checkMainThread("onEnterAmbient() must be called on the main thread");
        h hVar = this.h;
        if (hVar.getDelegate() != null) {
            hVar.getDelegate().a(bundle);
        }
    }

    public void onExitAmbient() {
        Preconditions.checkMainThread("onExitAmbient() must be called on the main thread");
        h hVar = this.h;
        if (hVar.getDelegate() != null) {
            hVar.getDelegate().b();
        }
    }

    public void onLowMemory() {
        this.h.onLowMemory();
    }

    public void onPause() {
        this.h.onPause();
    }

    public void onResume() {
        this.h.onResume();
    }

    public void onSaveInstanceState(@NonNull Bundle bundle) {
        this.h.onSaveInstanceState(bundle);
    }

    public void onStart() {
        this.h.onStart();
    }

    public void onStop() {
        this.h.onStop();
    }

    public MapView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = new h(this, context, GoogleMapOptions.createFromAttributes(context, attributeSet));
        setClickable(true);
    }

    public MapView(@NonNull Context context, @NonNull AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = new h(this, context, GoogleMapOptions.createFromAttributes(context, attributeSet));
        setClickable(true);
    }

    public MapView(@NonNull Context context, @Nullable GoogleMapOptions googleMapOptions) {
        super(context);
        this.h = new h(this, context, googleMapOptions);
        setClickable(true);
    }
}
