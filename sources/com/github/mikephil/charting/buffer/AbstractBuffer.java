package com.github.mikephil.charting.buffer;
/* loaded from: classes9.dex */
public abstract class AbstractBuffer<T> {
    public final float[] buffer;
    public int index;
    public float phaseX = 1.0f;
    public float phaseY = 1.0f;
    public int mFrom = 0;
    public int mTo = 0;

    public AbstractBuffer(int i) {
        this.index = 0;
        this.index = 0;
        this.buffer = new float[i];
    }

    public abstract void feed(T t);

    public void limitFrom(int i) {
        if (i < 0) {
            i = 0;
        }
        this.mFrom = i;
    }

    public void limitTo(int i) {
        if (i < 0) {
            i = 0;
        }
        this.mTo = i;
    }

    public void reset() {
        this.index = 0;
    }

    public void setPhases(float f, float f2) {
        this.phaseX = f;
        this.phaseY = f2;
    }

    public int size() {
        return this.buffer.length;
    }
}
