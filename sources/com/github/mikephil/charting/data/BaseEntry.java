package com.github.mikephil.charting.data;

import android.graphics.drawable.Drawable;
/* loaded from: classes9.dex */
public abstract class BaseEntry {
    public float h;
    public Object i;
    public Drawable j;

    public BaseEntry() {
        this.h = 0.0f;
        this.i = null;
        this.j = null;
    }

    public Object getData() {
        return this.i;
    }

    public Drawable getIcon() {
        return this.j;
    }

    public float getY() {
        return this.h;
    }

    public void setData(Object obj) {
        this.i = obj;
    }

    public void setIcon(Drawable drawable) {
        this.j = drawable;
    }

    public void setY(float f) {
        this.h = f;
    }

    public BaseEntry(float f) {
        this.h = 0.0f;
        this.i = null;
        this.j = null;
        this.h = f;
    }

    public BaseEntry(float f, Object obj) {
        this(f);
        this.i = obj;
    }

    public BaseEntry(float f, Drawable drawable) {
        this(f);
        this.j = drawable;
    }

    public BaseEntry(float f, Drawable drawable, Object obj) {
        this(f);
        this.j = drawable;
        this.i = obj;
    }
}
