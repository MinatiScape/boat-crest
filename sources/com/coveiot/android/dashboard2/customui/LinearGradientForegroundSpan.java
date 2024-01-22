package com.coveiot.android.dashboard2.customui;

import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class LinearGradientForegroundSpan extends CharacterStyle implements UpdateAppearance {
    public int h;
    public int i;
    public int j;

    public LinearGradientForegroundSpan(int i, int i2, int i3) {
        this.h = i;
        this.i = i2;
        this.j = i3;
    }

    public final int getEndColor() {
        return this.i;
    }

    public final int getLineHeight() {
        return this.j;
    }

    public final int getStartColor() {
        return this.h;
    }

    public final void setEndColor(int i) {
        this.i = i;
    }

    public final void setLineHeight(int i) {
        this.j = i;
    }

    public final void setStartColor(int i) {
        this.h = i;
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(@Nullable TextPaint textPaint) {
        if (textPaint == null) {
            return;
        }
        textPaint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, this.j, this.h, this.i, Shader.TileMode.REPEAT));
    }
}
