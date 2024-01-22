package com.coveiot.android.theme.compundview;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class h implements CharSequence {
    @NotNull
    public CharSequence h;

    public h(@NotNull CharSequence mSource) {
        Intrinsics.checkNotNullParameter(mSource, "mSource");
        this.h = mSource;
    }

    public char a(int i) {
        return '*';
    }

    public int b() {
        return this.h.length();
    }

    @Override // java.lang.CharSequence
    public final /* bridge */ char charAt(int i) {
        return a(i);
    }

    @Override // java.lang.CharSequence
    public final /* bridge */ int length() {
        return b();
    }

    @Override // java.lang.CharSequence
    @NotNull
    public CharSequence subSequence(int i, int i2) {
        return this.h.subSequence(i, i2);
    }
}
