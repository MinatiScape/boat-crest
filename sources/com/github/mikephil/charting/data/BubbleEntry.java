package com.github.mikephil.charting.data;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
@SuppressLint({"ParcelCreator"})
/* loaded from: classes9.dex */
public class BubbleEntry extends Entry {
    public float l;

    public BubbleEntry(float f, float f2, float f3) {
        super(f, f2);
        this.l = 0.0f;
        this.l = f3;
    }

    public float getSize() {
        return this.l;
    }

    public void setSize(float f) {
        this.l = f;
    }

    @Override // com.github.mikephil.charting.data.Entry
    public BubbleEntry copy() {
        return new BubbleEntry(getX(), getY(), this.l, getData());
    }

    public BubbleEntry(float f, float f2, float f3, Object obj) {
        super(f, f2, obj);
        this.l = 0.0f;
        this.l = f3;
    }

    public BubbleEntry(float f, float f2, float f3, Drawable drawable) {
        super(f, f2, drawable);
        this.l = 0.0f;
        this.l = f3;
    }

    public BubbleEntry(float f, float f2, float f3, Drawable drawable, Object obj) {
        super(f, f2, drawable, obj);
        this.l = 0.0f;
        this.l = f3;
    }
}
