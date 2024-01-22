package com.github.mikephil.charting.data;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
@SuppressLint({"ParcelCreator"})
/* loaded from: classes9.dex */
public class CandleEntry extends Entry {
    public float l;
    public float m;
    public float n;
    public float o;

    public CandleEntry(float f, float f2, float f3, float f4, float f5) {
        super(f, (f2 + f3) / 2.0f);
        this.l = 0.0f;
        this.m = 0.0f;
        this.n = 0.0f;
        this.o = 0.0f;
        this.l = f2;
        this.m = f3;
        this.o = f4;
        this.n = f5;
    }

    public float getBodyRange() {
        return Math.abs(this.o - this.n);
    }

    public float getClose() {
        return this.n;
    }

    public float getHigh() {
        return this.l;
    }

    public float getLow() {
        return this.m;
    }

    public float getOpen() {
        return this.o;
    }

    public float getShadowRange() {
        return Math.abs(this.l - this.m);
    }

    @Override // com.github.mikephil.charting.data.BaseEntry
    public float getY() {
        return super.getY();
    }

    public void setClose(float f) {
        this.n = f;
    }

    public void setHigh(float f) {
        this.l = f;
    }

    public void setLow(float f) {
        this.m = f;
    }

    public void setOpen(float f) {
        this.o = f;
    }

    @Override // com.github.mikephil.charting.data.Entry
    public CandleEntry copy() {
        return new CandleEntry(getX(), this.l, this.m, this.o, this.n, getData());
    }

    public CandleEntry(float f, float f2, float f3, float f4, float f5, Object obj) {
        super(f, (f2 + f3) / 2.0f, obj);
        this.l = 0.0f;
        this.m = 0.0f;
        this.n = 0.0f;
        this.o = 0.0f;
        this.l = f2;
        this.m = f3;
        this.o = f4;
        this.n = f5;
    }

    public CandleEntry(float f, float f2, float f3, float f4, float f5, Drawable drawable) {
        super(f, (f2 + f3) / 2.0f, drawable);
        this.l = 0.0f;
        this.m = 0.0f;
        this.n = 0.0f;
        this.o = 0.0f;
        this.l = f2;
        this.m = f3;
        this.o = f4;
        this.n = f5;
    }

    public CandleEntry(float f, float f2, float f3, float f4, float f5, Drawable drawable, Object obj) {
        super(f, (f2 + f3) / 2.0f, drawable, obj);
        this.l = 0.0f;
        this.m = 0.0f;
        this.n = 0.0f;
        this.o = 0.0f;
        this.l = f2;
        this.m = f3;
        this.o = f4;
        this.n = f5;
    }
}
