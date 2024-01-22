package com.bumptech.glide.load.engine.cache;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import com.bumptech.glide.util.Preconditions;
/* loaded from: classes2.dex */
public final class MemorySizeCalculator {

    /* renamed from: a  reason: collision with root package name */
    public final int f2371a;
    public final int b;
    public final Context c;
    public final int d;

    /* loaded from: classes2.dex */
    public static final class Builder {
        public static final int i;

        /* renamed from: a  reason: collision with root package name */
        public final Context f2372a;
        public ActivityManager b;
        public b c;
        public float e;
        public float d = 2.0f;
        public float f = 0.4f;
        public float g = 0.33f;
        public int h = 4194304;

        static {
            i = Build.VERSION.SDK_INT < 26 ? 4 : 1;
        }

        public Builder(Context context) {
            this.e = i;
            this.f2372a = context;
            this.b = (ActivityManager) context.getSystemService("activity");
            this.c = new a(context.getResources().getDisplayMetrics());
            if (Build.VERSION.SDK_INT < 26 || !MemorySizeCalculator.b(this.b)) {
                return;
            }
            this.e = 0.0f;
        }

        public MemorySizeCalculator build() {
            return new MemorySizeCalculator(this);
        }

        public Builder setArrayPoolSize(int i2) {
            this.h = i2;
            return this;
        }

        public Builder setBitmapPoolScreens(float f) {
            Preconditions.checkArgument(f >= 0.0f, "Bitmap pool screens must be greater than or equal to 0");
            this.e = f;
            return this;
        }

        public Builder setLowMemoryMaxSizeMultiplier(float f) {
            Preconditions.checkArgument(f >= 0.0f && f <= 1.0f, "Low memory max size multiplier must be between 0 and 1");
            this.g = f;
            return this;
        }

        public Builder setMaxSizeMultiplier(float f) {
            Preconditions.checkArgument(f >= 0.0f && f <= 1.0f, "Size multiplier must be between 0 and 1");
            this.f = f;
            return this;
        }

        public Builder setMemoryCacheScreens(float f) {
            Preconditions.checkArgument(f >= 0.0f, "Memory cache screens must be greater than or equal to 0");
            this.d = f;
            return this;
        }
    }

    /* loaded from: classes2.dex */
    public static final class a implements b {

        /* renamed from: a  reason: collision with root package name */
        public final DisplayMetrics f2373a;

        public a(DisplayMetrics displayMetrics) {
            this.f2373a = displayMetrics;
        }

        @Override // com.bumptech.glide.load.engine.cache.MemorySizeCalculator.b
        public int a() {
            return this.f2373a.heightPixels;
        }

        @Override // com.bumptech.glide.load.engine.cache.MemorySizeCalculator.b
        public int b() {
            return this.f2373a.widthPixels;
        }
    }

    /* loaded from: classes2.dex */
    public interface b {
        int a();

        int b();
    }

    public MemorySizeCalculator(Builder builder) {
        int i;
        this.c = builder.f2372a;
        if (b(builder.b)) {
            i = builder.h / 2;
        } else {
            i = builder.h;
        }
        this.d = i;
        int a2 = a(builder.b, builder.f, builder.g);
        float b2 = builder.c.b() * builder.c.a() * 4;
        int round = Math.round(builder.e * b2);
        int round2 = Math.round(b2 * builder.d);
        int i2 = a2 - i;
        int i3 = round2 + round;
        if (i3 <= i2) {
            this.b = round2;
            this.f2371a = round;
        } else {
            float f = i2;
            float f2 = builder.e;
            float f3 = builder.d;
            float f4 = f / (f2 + f3);
            this.b = Math.round(f3 * f4);
            this.f2371a = Math.round(f4 * builder.e);
        }
        if (Log.isLoggable("MemorySizeCalculator", 3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Calculation complete, Calculated memory cache size: ");
            sb.append(c(this.b));
            sb.append(", pool size: ");
            sb.append(c(this.f2371a));
            sb.append(", byte array size: ");
            sb.append(c(i));
            sb.append(", memory class limited? ");
            sb.append(i3 > a2);
            sb.append(", max size: ");
            sb.append(c(a2));
            sb.append(", memoryClass: ");
            sb.append(builder.b.getMemoryClass());
            sb.append(", isLowMemoryDevice: ");
            sb.append(b(builder.b));
            Log.d("MemorySizeCalculator", sb.toString());
        }
    }

    public static int a(ActivityManager activityManager, float f, float f2) {
        boolean b2 = b(activityManager);
        float memoryClass = activityManager.getMemoryClass() * 1024 * 1024;
        if (b2) {
            f = f2;
        }
        return Math.round(memoryClass * f);
    }

    @TargetApi(19)
    public static boolean b(ActivityManager activityManager) {
        if (Build.VERSION.SDK_INT >= 19) {
            return activityManager.isLowRamDevice();
        }
        return true;
    }

    public final String c(int i) {
        return Formatter.formatFileSize(this.c, i);
    }

    public int getArrayPoolSizeInBytes() {
        return this.d;
    }

    public int getBitmapPoolSize() {
        return this.f2371a;
    }

    public int getMemoryCacheSize() {
        return this.b;
    }
}
