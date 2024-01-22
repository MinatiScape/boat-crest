package com.mappls.sdk.maps.location;

import android.animation.FloatEvaluator;
import android.animation.TypeEvaluator;
import androidx.annotation.NonNull;
import androidx.annotation.Size;
import com.mappls.sdk.maps.location.o;
/* loaded from: classes11.dex */
public class s extends o<Float> {
    public s(@NonNull @Size(min = 2) Float[] fArr, @NonNull o.b bVar, int i) {
        super(fArr, bVar, i);
    }

    @Override // com.mappls.sdk.maps.location.o
    @NonNull
    public TypeEvaluator d() {
        return new FloatEvaluator();
    }
}
