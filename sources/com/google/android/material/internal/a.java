package com.google.android.material.internal;

import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import java.lang.reflect.Constructor;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes10.dex */
public final class a {
    public static final int n;
    public static boolean o;
    @Nullable
    public static Constructor<StaticLayout> p;
    @Nullable
    public static Object q;

    /* renamed from: a  reason: collision with root package name */
    public CharSequence f10327a;
    public final TextPaint b;
    public final int c;
    public int e;
    public boolean l;
    public int d = 0;
    public Layout.Alignment f = Layout.Alignment.ALIGN_NORMAL;
    public int g = Integer.MAX_VALUE;
    public float h = 0.0f;
    public float i = 1.0f;
    public int j = n;
    public boolean k = true;
    @Nullable
    public TextUtils.TruncateAt m = null;

    /* renamed from: com.google.android.material.internal.a$a  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public static class C0430a extends Exception {
        public C0430a(Throwable th) {
            super("Error thrown initializing StaticLayout " + th.getMessage(), th);
        }
    }

    static {
        n = Build.VERSION.SDK_INT >= 23 ? 1 : 0;
    }

    public a(CharSequence charSequence, TextPaint textPaint, int i) {
        this.f10327a = charSequence;
        this.b = textPaint;
        this.c = i;
        this.e = charSequence.length();
    }

    @NonNull
    public static a c(@NonNull CharSequence charSequence, @NonNull TextPaint textPaint, @IntRange(from = 0) int i) {
        return new a(charSequence, textPaint, i);
    }

    public StaticLayout a() throws C0430a {
        TextDirectionHeuristic textDirectionHeuristic;
        if (this.f10327a == null) {
            this.f10327a = "";
        }
        int max = Math.max(0, this.c);
        CharSequence charSequence = this.f10327a;
        if (this.g == 1) {
            charSequence = TextUtils.ellipsize(charSequence, this.b, max, this.m);
        }
        int min = Math.min(charSequence.length(), this.e);
        this.e = min;
        if (Build.VERSION.SDK_INT >= 23) {
            if (this.l && this.g == 1) {
                this.f = Layout.Alignment.ALIGN_OPPOSITE;
            }
            StaticLayout.Builder obtain = StaticLayout.Builder.obtain(charSequence, this.d, min, this.b, max);
            obtain.setAlignment(this.f);
            obtain.setIncludePad(this.k);
            if (this.l) {
                textDirectionHeuristic = TextDirectionHeuristics.RTL;
            } else {
                textDirectionHeuristic = TextDirectionHeuristics.LTR;
            }
            obtain.setTextDirection(textDirectionHeuristic);
            TextUtils.TruncateAt truncateAt = this.m;
            if (truncateAt != null) {
                obtain.setEllipsize(truncateAt);
            }
            obtain.setMaxLines(this.g);
            float f = this.h;
            if (f != 0.0f || this.i != 1.0f) {
                obtain.setLineSpacing(f, this.i);
            }
            if (this.g > 1) {
                obtain.setHyphenationFrequency(this.j);
            }
            return obtain.build();
        }
        b();
        try {
            return (StaticLayout) ((Constructor) Preconditions.checkNotNull(p)).newInstance(charSequence, Integer.valueOf(this.d), Integer.valueOf(this.e), this.b, Integer.valueOf(max), this.f, Preconditions.checkNotNull(q), Float.valueOf(1.0f), Float.valueOf(0.0f), Boolean.valueOf(this.k), null, Integer.valueOf(max), Integer.valueOf(this.g));
        } catch (Exception e) {
            throw new C0430a(e);
        }
    }

    public final void b() throws C0430a {
        Class<?> cls;
        if (o) {
            return;
        }
        try {
            boolean z = this.l && Build.VERSION.SDK_INT >= 23;
            if (Build.VERSION.SDK_INT >= 18) {
                cls = TextDirectionHeuristic.class;
                q = z ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR;
            } else {
                ClassLoader classLoader = a.class.getClassLoader();
                String str = this.l ? "RTL" : "LTR";
                Class<?> loadClass = classLoader.loadClass("android.text.TextDirectionHeuristic");
                Class<?> loadClass2 = classLoader.loadClass("android.text.TextDirectionHeuristics");
                q = loadClass2.getField(str).get(loadClass2);
                cls = loadClass;
            }
            Class cls2 = Integer.TYPE;
            Class cls3 = Float.TYPE;
            Constructor<StaticLayout> declaredConstructor = StaticLayout.class.getDeclaredConstructor(CharSequence.class, cls2, cls2, TextPaint.class, cls2, Layout.Alignment.class, cls, cls3, cls3, Boolean.TYPE, TextUtils.TruncateAt.class, cls2, cls2);
            p = declaredConstructor;
            declaredConstructor.setAccessible(true);
            o = true;
        } catch (Exception e) {
            throw new C0430a(e);
        }
    }

    @NonNull
    public a d(@NonNull Layout.Alignment alignment) {
        this.f = alignment;
        return this;
    }

    @NonNull
    public a e(@Nullable TextUtils.TruncateAt truncateAt) {
        this.m = truncateAt;
        return this;
    }

    @NonNull
    public a f(int i) {
        this.j = i;
        return this;
    }

    @NonNull
    public a g(boolean z) {
        this.k = z;
        return this;
    }

    public a h(boolean z) {
        this.l = z;
        return this;
    }

    @NonNull
    public a i(float f, float f2) {
        this.h = f;
        this.i = f2;
        return this;
    }

    @NonNull
    public a j(@IntRange(from = 0) int i) {
        this.g = i;
        return this;
    }
}
