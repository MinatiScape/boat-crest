package com.github.mikephil.charting.components;

import android.graphics.Paint;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
/* loaded from: classes9.dex */
public class Description extends ComponentBase {
    public MPPointF b;

    /* renamed from: a  reason: collision with root package name */
    public String f7935a = "Description Label";
    public Paint.Align c = Paint.Align.RIGHT;

    public Description() {
        this.mTextSize = Utils.convertDpToPixel(8.0f);
    }

    public MPPointF getPosition() {
        return this.b;
    }

    public String getText() {
        return this.f7935a;
    }

    public Paint.Align getTextAlign() {
        return this.c;
    }

    public void setPosition(float f, float f2) {
        MPPointF mPPointF = this.b;
        if (mPPointF == null) {
            this.b = MPPointF.getInstance(f, f2);
            return;
        }
        mPPointF.x = f;
        mPPointF.y = f2;
    }

    public void setText(String str) {
        this.f7935a = str;
    }

    public void setTextAlign(Paint.Align align) {
        this.c = align;
    }
}
