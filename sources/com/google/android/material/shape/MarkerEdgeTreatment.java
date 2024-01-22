package com.google.android.material.shape;

import androidx.annotation.NonNull;
/* loaded from: classes10.dex */
public final class MarkerEdgeTreatment extends EdgeTreatment {

    /* renamed from: a  reason: collision with root package name */
    public final float f10349a;

    public MarkerEdgeTreatment(float f) {
        this.f10349a = f - 0.001f;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public boolean a() {
        return true;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float f, float f2, float f3, @NonNull ShapePath shapePath) {
        float sqrt = (float) ((this.f10349a * Math.sqrt(2.0d)) / 2.0d);
        float sqrt2 = (float) Math.sqrt(Math.pow(this.f10349a, 2.0d) - Math.pow(sqrt, 2.0d));
        shapePath.reset(f2 - sqrt, ((float) (-((this.f10349a * Math.sqrt(2.0d)) - this.f10349a))) + sqrt2);
        shapePath.lineTo(f2, (float) (-((this.f10349a * Math.sqrt(2.0d)) - this.f10349a)));
        shapePath.lineTo(f2 + sqrt, ((float) (-((this.f10349a * Math.sqrt(2.0d)) - this.f10349a))) + sqrt2);
    }
}
