package com.mappls.sdk.maps;
/* loaded from: classes11.dex */
public final class ImageStretches {

    /* renamed from: a  reason: collision with root package name */
    public final float f12627a;
    public final float b;

    public ImageStretches(float f, float f2) {
        this.f12627a = f;
        this.b = f2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ImageStretches) {
            ImageStretches imageStretches = (ImageStretches) obj;
            return this.f12627a == imageStretches.f12627a && this.b == imageStretches.b;
        }
        return false;
    }

    public float getFirst() {
        return this.f12627a;
    }

    public float getSecond() {
        return this.b;
    }

    public int hashCode() {
        float f = this.f12627a;
        int floatToIntBits = (f != 0.0f ? Float.floatToIntBits(f) : 0) * 31;
        float f2 = this.b;
        return floatToIntBits + (f2 != 0.0f ? Float.floatToIntBits(f2) : 0);
    }

    public String toString() {
        return "[ first: " + this.f12627a + ", second: " + this.b + " ]";
    }
}
