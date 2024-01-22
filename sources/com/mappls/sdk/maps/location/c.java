package com.mappls.sdk.maps.location;

import android.animation.TypeEvaluator;
import androidx.annotation.NonNull;
import com.mappls.sdk.maps.geometry.LatLng;
/* loaded from: classes11.dex */
public class c implements TypeEvaluator<LatLng> {

    /* renamed from: a  reason: collision with root package name */
    public final LatLng f12751a = new LatLng();

    @Override // android.animation.TypeEvaluator
    @NonNull
    /* renamed from: a */
    public LatLng evaluate(float f, @NonNull LatLng latLng, @NonNull LatLng latLng2) {
        double d = f;
        this.f12751a.setLatitude(latLng.getLatitude() + ((latLng2.getLatitude() - latLng.getLatitude()) * d));
        this.f12751a.setLongitude(latLng.getLongitude() + ((latLng2.getLongitude() - latLng.getLongitude()) * d));
        return this.f12751a;
    }
}
