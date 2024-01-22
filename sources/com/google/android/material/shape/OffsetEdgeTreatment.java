package com.google.android.material.shape;

import androidx.annotation.NonNull;
/* loaded from: classes10.dex */
public final class OffsetEdgeTreatment extends EdgeTreatment {

    /* renamed from: a  reason: collision with root package name */
    public final EdgeTreatment f10353a;
    public final float b;

    public OffsetEdgeTreatment(@NonNull EdgeTreatment edgeTreatment, float f) {
        this.f10353a = edgeTreatment;
        this.b = f;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public boolean a() {
        return this.f10353a.a();
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float f, float f2, float f3, @NonNull ShapePath shapePath) {
        this.f10353a.getEdgePath(f, f2 - this.b, f3, shapePath);
    }
}
