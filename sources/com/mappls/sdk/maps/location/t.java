package com.mappls.sdk.maps.location;

import android.animation.TypeEvaluator;
import androidx.annotation.NonNull;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.location.o;
/* loaded from: classes11.dex */
public class t extends o<LatLng> {
    public t(@NonNull LatLng[] latLngArr, @NonNull o.b bVar, int i) {
        super(latLngArr, bVar, i);
    }

    @Override // com.mappls.sdk.maps.location.o
    @NonNull
    public TypeEvaluator d() {
        return new c();
    }
}
