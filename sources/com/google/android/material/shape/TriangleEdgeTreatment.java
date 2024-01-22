package com.google.android.material.shape;

import androidx.annotation.NonNull;
/* loaded from: classes10.dex */
public class TriangleEdgeTreatment extends EdgeTreatment {

    /* renamed from: a  reason: collision with root package name */
    public final float f10366a;
    public final boolean b;

    public TriangleEdgeTreatment(float f, boolean z) {
        this.f10366a = f;
        this.b = z;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float f, float f2, float f3, @NonNull ShapePath shapePath) {
        shapePath.lineTo(f2 - (this.f10366a * f3), 0.0f);
        shapePath.lineTo(f2, (this.b ? this.f10366a : -this.f10366a) * f3);
        shapePath.lineTo(f2 + (this.f10366a * f3), 0.0f);
        shapePath.lineTo(f, 0.0f);
    }
}
