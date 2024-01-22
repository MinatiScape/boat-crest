package com.coveiot.android.bleabstract.response;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class CustomWatchFaceLayoutResponse {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Integer f3590a;
    @Nullable
    public Integer b;
    @Nullable
    public Integer c;
    @Nullable
    public Integer d;
    @Nullable
    public String e;
    @Nullable
    public Integer f;
    @Nullable
    public Integer g;
    @Nullable
    public Integer h;
    @Nullable
    public Integer i;
    @Nullable
    public String j;

    @Nullable
    public final String getBackgroundPictureMd5() {
        return this.e;
    }

    @Nullable
    public final Integer getBottomContent() {
        return this.c;
    }

    @Nullable
    public final String getCompressionType() {
        return this.j;
    }

    @Nullable
    public final Integer getHeight() {
        return this.f;
    }

    @Nullable
    public final Integer getPosition() {
        return this.f3590a;
    }

    @Nullable
    public final Integer getTextColor() {
        return this.d;
    }

    @Nullable
    public final Integer getThumbHeight() {
        return this.h;
    }

    @Nullable
    public final Integer getThumbWidth() {
        return this.i;
    }

    @Nullable
    public final Integer getTopContent() {
        return this.b;
    }

    @Nullable
    public final Integer getWidth() {
        return this.g;
    }

    public final void setBackgroundPictureMd5(@Nullable String str) {
        this.e = str;
    }

    public final void setBottomContent(@Nullable Integer num) {
        this.c = num;
    }

    public final void setCompressionType(@Nullable String str) {
        this.j = str;
    }

    public final void setHeight(@Nullable Integer num) {
        this.f = num;
    }

    public final void setPosition(@Nullable Integer num) {
        this.f3590a = num;
    }

    public final void setTextColor(@Nullable Integer num) {
        this.d = num;
    }

    public final void setThumbHeight(@Nullable Integer num) {
        this.h = num;
    }

    public final void setThumbWidth(@Nullable Integer num) {
        this.i = num;
    }

    public final void setTopContent(@Nullable Integer num) {
        this.b = num;
    }

    public final void setWidth(@Nullable Integer num) {
        this.g = num;
    }
}
