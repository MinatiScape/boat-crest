package com.coveiot.android.leonardo.utils;

import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public class FontSpan extends MetricAffectingSpan {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int WRONG_TYPEFACE = 0;
    @Nullable
    public final Typeface h;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public FontSpan(@Nullable Typeface typeface) {
        this.h = typeface;
    }

    public final int a(Typeface typeface) {
        if (typeface != null) {
            return typeface.getStyle();
        }
        return 0;
    }

    public final void b(TextPaint textPaint) {
        int a2 = a(textPaint.getTypeface());
        if (a2 == 0) {
            return;
        }
        textPaint.setTypeface(Typeface.create(this.h, a2));
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(@NotNull TextPaint textPaint) {
        Intrinsics.checkNotNullParameter(textPaint, "textPaint");
        b(textPaint);
    }

    @Override // android.text.style.MetricAffectingSpan
    public void updateMeasureState(@NotNull TextPaint textPaint) {
        Intrinsics.checkNotNullParameter(textPaint, "textPaint");
        b(textPaint);
    }
}
