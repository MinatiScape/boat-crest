package com.google.mlkit.vision.common;
/* loaded from: classes10.dex */
public final class a extends PointF3D {

    /* renamed from: a  reason: collision with root package name */
    public final float f11635a;
    public final float b;
    public final float c;

    public a(float f, float f2, float f3) {
        this.f11635a = f;
        this.b = f2;
        this.c = f3;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PointF3D) {
            PointF3D pointF3D = (PointF3D) obj;
            if (Float.floatToIntBits(this.f11635a) == Float.floatToIntBits(pointF3D.getX()) && Float.floatToIntBits(this.b) == Float.floatToIntBits(pointF3D.getY()) && Float.floatToIntBits(this.c) == Float.floatToIntBits(pointF3D.getZ())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.mlkit.vision.common.PointF3D
    public final float getX() {
        return this.f11635a;
    }

    @Override // com.google.mlkit.vision.common.PointF3D
    public final float getY() {
        return this.b;
    }

    @Override // com.google.mlkit.vision.common.PointF3D
    public final float getZ() {
        return this.c;
    }

    public final int hashCode() {
        return ((((Float.floatToIntBits(this.f11635a) ^ 1000003) * 1000003) ^ Float.floatToIntBits(this.b)) * 1000003) ^ Float.floatToIntBits(this.c);
    }

    public final String toString() {
        float f = this.f11635a;
        float f2 = this.b;
        float f3 = this.c;
        return "PointF3D{x=" + f + ", y=" + f2 + ", z=" + f3 + "}";
    }
}
