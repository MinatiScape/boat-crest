package com.shockwave.pdfium.util;
/* loaded from: classes12.dex */
public class Size {

    /* renamed from: a  reason: collision with root package name */
    public final int f13693a;
    public final int b;

    public Size(int i, int i2) {
        this.f13693a = i;
        this.b = i2;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof Size) {
            Size size = (Size) obj;
            return this.f13693a == size.f13693a && this.b == size.b;
        }
        return false;
    }

    public int getHeight() {
        return this.b;
    }

    public int getWidth() {
        return this.f13693a;
    }

    public int hashCode() {
        int i = this.b;
        int i2 = this.f13693a;
        return i ^ ((i2 >>> 16) | (i2 << 16));
    }

    public String toString() {
        return this.f13693a + "x" + this.b;
    }
}
