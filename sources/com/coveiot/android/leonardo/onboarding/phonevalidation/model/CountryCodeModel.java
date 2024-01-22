package com.coveiot.android.leonardo.onboarding.phonevalidation.model;

import android.graphics.drawable.Drawable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class CountryCodeModel {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f5291a;
    @Nullable
    public String b;
    @Nullable
    public Drawable c;

    @Nullable
    public final String getCountryCodeInfo() {
        return this.f5291a;
    }

    @Nullable
    public final Drawable getFlagDrawable() {
        return this.c;
    }

    @Nullable
    public final String getFlagInfo() {
        return this.b;
    }

    public final void setCountryCodeInfo(@Nullable String str) {
        this.f5291a = str;
    }

    public final void setFlagDrawable(@Nullable Drawable drawable) {
        this.c = drawable;
    }

    public final void setFlagInfo(@Nullable String str) {
        this.b = str;
    }
}
