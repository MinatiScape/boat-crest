package com.mappls.sdk.maps.location;

import android.view.animation.Interpolator;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.location.o;
/* loaded from: classes11.dex */
public final class p {

    /* renamed from: a  reason: collision with root package name */
    public static p f12780a;

    public static p c() {
        if (f12780a == null) {
            f12780a = new p();
        }
        return f12780a;
    }

    public r a(Float[] fArr, o.b bVar, @Nullable MapplsMap.CancelableCallback cancelableCallback) {
        return new r(fArr, bVar, cancelableCallback);
    }

    public s b(Float[] fArr, o.b bVar, int i) {
        return new s(fArr, bVar, i);
    }

    public t d(LatLng[] latLngArr, o.b bVar, int i) {
        return new t(latLngArr, bVar, i);
    }

    public PulsingLocationCircleAnimator e(o.b bVar, int i, float f, float f2, Interpolator interpolator) {
        PulsingLocationCircleAnimator pulsingLocationCircleAnimator = new PulsingLocationCircleAnimator(bVar, i, f2);
        pulsingLocationCircleAnimator.setDuration(f);
        pulsingLocationCircleAnimator.setRepeatMode(1);
        pulsingLocationCircleAnimator.setRepeatCount(-1);
        pulsingLocationCircleAnimator.setInterpolator(interpolator);
        return pulsingLocationCircleAnimator;
    }
}
