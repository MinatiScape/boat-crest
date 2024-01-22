package com.mappls.sdk.maps.attribution;

import android.graphics.Bitmap;
import android.graphics.PointF;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes11.dex */
public class AttributionLayout {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Bitmap f12674a;
    @Nullable
    public PointF b;
    public boolean c;

    public AttributionLayout(@Nullable Bitmap bitmap, @Nullable PointF pointF, boolean z) {
        this.f12674a = bitmap;
        this.b = pointF;
        this.c = z;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AttributionLayout attributionLayout = (AttributionLayout) obj;
        Bitmap bitmap = this.f12674a;
        if (bitmap == null ? attributionLayout.f12674a == null : bitmap.equals(attributionLayout.f12674a)) {
            PointF pointF = this.b;
            PointF pointF2 = attributionLayout.b;
            return pointF != null ? pointF.equals(pointF2) : pointF2 == null;
        }
        return false;
    }

    @Nullable
    public PointF getAnchorPoint() {
        return this.b;
    }

    @Nullable
    public Bitmap getLogo() {
        return this.f12674a;
    }

    public int hashCode() {
        Bitmap bitmap = this.f12674a;
        int hashCode = (bitmap != null ? bitmap.hashCode() : 0) * 31;
        PointF pointF = this.b;
        return hashCode + (pointF != null ? pointF.hashCode() : 0);
    }

    public boolean isShortText() {
        return this.c;
    }

    @NonNull
    public String toString() {
        return "AttributionLayout{logo=" + this.f12674a + ", anchorPoint=" + this.b + '}';
    }
}
