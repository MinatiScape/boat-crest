package com.google.android.material.shape;

import android.graphics.RectF;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.Arrays;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes10.dex */
public final class AdjustedCornerSize implements CornerSize {

    /* renamed from: a  reason: collision with root package name */
    public final CornerSize f10346a;
    public final float b;

    public AdjustedCornerSize(float f, @NonNull CornerSize cornerSize) {
        while (cornerSize instanceof AdjustedCornerSize) {
            cornerSize = ((AdjustedCornerSize) cornerSize).f10346a;
            f += ((AdjustedCornerSize) cornerSize).b;
        }
        this.f10346a = cornerSize;
        this.b = f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AdjustedCornerSize) {
            AdjustedCornerSize adjustedCornerSize = (AdjustedCornerSize) obj;
            return this.f10346a.equals(adjustedCornerSize.f10346a) && this.b == adjustedCornerSize.b;
        }
        return false;
    }

    @Override // com.google.android.material.shape.CornerSize
    public float getCornerSize(@NonNull RectF rectF) {
        return Math.max(0.0f, this.f10346a.getCornerSize(rectF) + this.b);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f10346a, Float.valueOf(this.b)});
    }
}
