package com.bumptech.glide.load.engine.prefill;

import android.graphics.Bitmap;
import androidx.annotation.Nullable;
import com.bumptech.glide.util.Preconditions;
/* loaded from: classes2.dex */
public final class PreFillType {

    /* renamed from: a  reason: collision with root package name */
    public final int f2392a;
    public final int b;
    public final Bitmap.Config c;
    public final int d;

    /* loaded from: classes2.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final int f2393a;
        public final int b;
        public Bitmap.Config c;
        public int d;

        public Builder(int i) {
            this(i, i);
        }

        public PreFillType a() {
            return new PreFillType(this.f2393a, this.b, this.c, this.d);
        }

        public Bitmap.Config b() {
            return this.c;
        }

        public Builder setConfig(@Nullable Bitmap.Config config) {
            this.c = config;
            return this;
        }

        public Builder setWeight(int i) {
            if (i > 0) {
                this.d = i;
                return this;
            }
            throw new IllegalArgumentException("Weight must be > 0");
        }

        public Builder(int i, int i2) {
            this.d = 1;
            if (i <= 0) {
                throw new IllegalArgumentException("Width must be > 0");
            }
            if (i2 > 0) {
                this.f2393a = i;
                this.b = i2;
                return;
            }
            throw new IllegalArgumentException("Height must be > 0");
        }
    }

    static {
        Bitmap.Config config = Bitmap.Config.RGB_565;
    }

    public PreFillType(int i, int i2, Bitmap.Config config, int i3) {
        this.c = (Bitmap.Config) Preconditions.checkNotNull(config, "Config must not be null");
        this.f2392a = i;
        this.b = i2;
        this.d = i3;
    }

    public Bitmap.Config a() {
        return this.c;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.d;
    }

    public int d() {
        return this.f2392a;
    }

    public boolean equals(Object obj) {
        if (obj instanceof PreFillType) {
            PreFillType preFillType = (PreFillType) obj;
            return this.b == preFillType.b && this.f2392a == preFillType.f2392a && this.d == preFillType.d && this.c == preFillType.c;
        }
        return false;
    }

    public int hashCode() {
        return (((((this.f2392a * 31) + this.b) * 31) + this.c.hashCode()) * 31) + this.d;
    }

    public String toString() {
        return "PreFillSize{width=" + this.f2392a + ", height=" + this.b + ", config=" + this.c + ", weight=" + this.d + '}';
    }
}
