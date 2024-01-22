package com.github.mikephil.charting.highlight;

import com.github.mikephil.charting.components.YAxis;
/* loaded from: classes9.dex */
public class Highlight {

    /* renamed from: a  reason: collision with root package name */
    public float f7948a;
    public float b;
    public float c;
    public float d;
    public int e;
    public int f;
    public int g;
    public YAxis.AxisDependency h;
    public float i;
    public float j;

    public Highlight(float f, float f2, int i) {
        this.f7948a = Float.NaN;
        this.b = Float.NaN;
        this.e = -1;
        this.g = -1;
        this.f7948a = f;
        this.b = f2;
        this.f = i;
    }

    public boolean equalTo(Highlight highlight) {
        return highlight != null && this.f == highlight.f && this.f7948a == highlight.f7948a && this.g == highlight.g && this.e == highlight.e;
    }

    public YAxis.AxisDependency getAxis() {
        return this.h;
    }

    public int getDataIndex() {
        return this.e;
    }

    public int getDataSetIndex() {
        return this.f;
    }

    public float getDrawX() {
        return this.i;
    }

    public float getDrawY() {
        return this.j;
    }

    public int getStackIndex() {
        return this.g;
    }

    public float getX() {
        return this.f7948a;
    }

    public float getXPx() {
        return this.c;
    }

    public float getY() {
        return this.b;
    }

    public float getYPx() {
        return this.d;
    }

    public boolean isStacked() {
        return this.g >= 0;
    }

    public void setDataIndex(int i) {
        this.e = i;
    }

    public void setDraw(float f, float f2) {
        this.i = f;
        this.j = f2;
    }

    public String toString() {
        return "Highlight, x: " + this.f7948a + ", y: " + this.b + ", dataSetIndex: " + this.f + ", stackIndex (only stacked barentry): " + this.g;
    }

    public Highlight(float f, int i, int i2) {
        this(f, Float.NaN, i);
        this.g = i2;
    }

    public Highlight(float f, float f2, float f3, float f4, int i, YAxis.AxisDependency axisDependency) {
        this.f7948a = Float.NaN;
        this.b = Float.NaN;
        this.e = -1;
        this.g = -1;
        this.f7948a = f;
        this.b = f2;
        this.c = f3;
        this.d = f4;
        this.f = i;
        this.h = axisDependency;
    }

    public Highlight(float f, float f2, float f3, float f4, int i, int i2, YAxis.AxisDependency axisDependency) {
        this(f, f2, f3, f4, i, axisDependency);
        this.g = i2;
    }
}
