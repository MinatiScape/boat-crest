package com.google.zxing;
/* loaded from: classes11.dex */
public final class Dimension {

    /* renamed from: a  reason: collision with root package name */
    public final int f11763a;
    public final int b;

    public Dimension(int i, int i2) {
        if (i >= 0 && i2 >= 0) {
            this.f11763a = i;
            this.b = i2;
            return;
        }
        throw new IllegalArgumentException();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Dimension) {
            Dimension dimension = (Dimension) obj;
            if (this.f11763a == dimension.f11763a && this.b == dimension.b) {
                return true;
            }
        }
        return false;
    }

    public int getHeight() {
        return this.b;
    }

    public int getWidth() {
        return this.f11763a;
    }

    public int hashCode() {
        return (this.f11763a * 32713) + this.b;
    }

    public String toString() {
        return this.f11763a + "x" + this.b;
    }
}
