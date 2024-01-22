package com.google.android.gms.common.images;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes6.dex */
public final class Size {

    /* renamed from: a  reason: collision with root package name */
    public final int f8320a;
    public final int b;

    public Size(int i, int i2) {
        this.f8320a = i;
        this.b = i2;
    }

    public static NumberFormatException a(String str) {
        throw new NumberFormatException("Invalid Size: \"" + str + "\"");
    }

    @NonNull
    public static Size parseSize(@NonNull String str) throws NumberFormatException {
        if (str != null) {
            int indexOf = str.indexOf(42);
            if (indexOf < 0) {
                indexOf = str.indexOf(120);
            }
            if (indexOf >= 0) {
                try {
                    return new Size(Integer.parseInt(str.substring(0, indexOf)), Integer.parseInt(str.substring(indexOf + 1)));
                } catch (NumberFormatException unused) {
                    throw a(str);
                }
            }
            throw a(str);
        }
        throw new IllegalArgumentException("string must not be null");
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof Size) {
            Size size = (Size) obj;
            if (this.f8320a == size.f8320a && this.b == size.b) {
                return true;
            }
        }
        return false;
    }

    public int getHeight() {
        return this.b;
    }

    public int getWidth() {
        return this.f8320a;
    }

    public int hashCode() {
        int i = this.b;
        int i2 = this.f8320a;
        return i ^ ((i2 >>> 16) | (i2 << 16));
    }

    @NonNull
    public String toString() {
        int i = this.f8320a;
        int i2 = this.b;
        return i + "x" + i2;
    }
}
