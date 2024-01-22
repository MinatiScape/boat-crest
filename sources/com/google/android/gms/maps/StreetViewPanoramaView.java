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
public class StreetViewPanoramaView extends FrameLayout {
    public final s h;

    public StreetViewPanoramaView(@NonNull Context context) {
        super((Context) Preconditions.checkNotNull(context, "context must not be null"));
        this.h = new s(this, context, null);
    }

    public void getStreetViewPanoramaAsync(@NonNull OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        Preconditions.checkNotNull(onStreetViewPanoramaReadyCallback, "callback must not be null");
        Preconditions.checkMainThread("getStreetViewPanoramaAsync() must be called on the main thread");
        this.h.g(onStreetViewPanoramaReadyCallback);
    }

    public final void onCreate(@Nullable Bundle bundle) {
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

    public final void onLowMemory() {
        this.h.onLowMemory();
    }

    public final void onPause() {
        this.h.onPause();
    }

    public void onResume() {
        this.h.onResume();
    }

    public final void onSaveInstanceState(@NonNull Bundle bundle) {
        this.h.onSaveInstanceState(bundle);
    }

    public void onStart() {
        this.h.onStart();
    }

    public void onStop() {
        this.h.onStop();
    }

    public StreetViewPanoramaView(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        super((Context) Preconditions.checkNotNull(context, "context must not be null"), attributeSet);
        this.h = new s(this, context, null);
    }

    public StreetViewPanoramaView(@NonNull Context context, @NonNull AttributeSet attributeSet, int i) {
        super((Context) Preconditions.checkNotNull(context, "context must not be null"), attributeSet, i);
        this.h = new s(this, context, null);
    }

    public StreetViewPanoramaView(@NonNull Context context, @Nullable StreetViewPanoramaOptions streetViewPanoramaOptions) {
        super((Context) Preconditions.checkNotNull(context, "context must not be null"));
        this.h = new s(this, context, streetViewPanoramaOptions);
    }
}
