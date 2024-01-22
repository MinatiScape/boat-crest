package com.airbnb.lottie.model;

import android.graphics.Typeface;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class Font {

    /* renamed from: a  reason: collision with root package name */
    public final String f2024a;
    public final String b;
    public final String c;
    @Nullable
    public Typeface d;

    public Font(String str, String str2, String str3, float f) {
        this.f2024a = str;
        this.b = str2;
        this.c = str3;
    }

    public String getFamily() {
        return this.f2024a;
    }

    public String getName() {
        return this.b;
    }

    public String getStyle() {
        return this.c;
    }

    @Nullable
    public Typeface getTypeface() {
        return this.d;
    }

    public void setTypeface(@Nullable Typeface typeface) {
        this.d = typeface;
    }
}
