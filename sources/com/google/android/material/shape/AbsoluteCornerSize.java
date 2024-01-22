package com.google.android.material.shape;

import android.graphics.RectF;
import androidx.annotation.NonNull;
import java.util.Arrays;
/* loaded from: classes10.dex */
public final class AbsoluteCornerSize implements CornerSize {

    /* renamed from: a  reason: collision with root package name */
    public final float f10345a;

    public AbsoluteCornerSize(float f) {
        this.f10345a = f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof AbsoluteCornerSize) && this.f10345a == ((AbsoluteCornerSize) obj).f10345a;
    }

    @Override // com.google.android.material.shape.CornerSize
    public float getCornerSize(@NonNull RectF rectF) {
        return this.f10345a;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f10345a)});
    }

    public float getCornerSize() {
        return this.f10345a;
    }
}
