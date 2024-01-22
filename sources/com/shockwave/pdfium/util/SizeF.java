package com.shockwave.pdfium.util;
/* loaded from: classes12.dex */
public class SizeF {

    /* renamed from: a  reason: collision with root package name */
    public final float f13694a;
    public final float b;

    public SizeF(float f, float f2) {
        this.f13694a = f;
        this.b = f2;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof SizeF) {
            SizeF sizeF = (SizeF) obj;
            return this.f13694a == sizeF.f13694a && this.b == sizeF.b;
        }
        return false;
    }

    public float getHeight() {
        return this.b;
    }

    public float getWidth() {
        return this.f13694a;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.f13694a) ^ Float.floatToIntBits(this.b);
    }

    public Size toSize() {
        return new Size((int) this.f13694a, (int) this.b);
    }

    public String toString() {
        return this.f13694a + "x" + this.b;
    }
}
