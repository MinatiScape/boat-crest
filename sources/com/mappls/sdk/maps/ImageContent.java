package com.mappls.sdk.maps;
/* loaded from: classes11.dex */
public final class ImageContent {

    /* renamed from: a  reason: collision with root package name */
    public final float f12626a;
    public final float b;
    public final float c;
    public final float d;

    public ImageContent(float f, float f2, float f3, float f4) {
        this.f12626a = f;
        this.b = f2;
        this.c = f3;
        this.d = f4;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ImageContent) {
            ImageContent imageContent = (ImageContent) obj;
            return this.f12626a == imageContent.f12626a && this.b == imageContent.b && this.c == imageContent.c && this.d == imageContent.d;
        }
        return false;
    }

    public float[] getContentArray() {
        return new float[]{this.f12626a, this.b, this.c, this.d};
    }

    public int hashCode() {
        float f = this.f12626a;
        int floatToIntBits = (f != 0.0f ? Float.floatToIntBits(f) : 0) * 31;
        float f2 = this.b;
        int floatToIntBits2 = (floatToIntBits + (f2 != 0.0f ? Float.floatToIntBits(f2) : 0)) * 31;
        float f3 = this.c;
        int floatToIntBits3 = (floatToIntBits2 + (f3 != 0.0f ? Float.floatToIntBits(f3) : 0)) * 31;
        float f4 = this.d;
        return floatToIntBits3 + (f4 != 0.0f ? Float.floatToIntBits(f4) : 0);
    }

    public String toString() {
        return "[ left: " + this.f12626a + ", top: " + this.b + ", right: " + this.c + ", bottom: " + this.d + " ]";
    }
}
