package com.yalantis.ucrop.model;

import android.graphics.RectF;
/* loaded from: classes12.dex */
public class ImageState {

    /* renamed from: a  reason: collision with root package name */
    public RectF f13878a;
    public RectF b;
    public float c;
    public float d;

    public ImageState(RectF rectF, RectF rectF2, float f, float f2) {
        this.f13878a = rectF;
        this.b = rectF2;
        this.c = f;
        this.d = f2;
    }

    public RectF getCropRect() {
        return this.f13878a;
    }

    public float getCurrentAngle() {
        return this.d;
    }

    public RectF getCurrentImageRect() {
        return this.b;
    }

    public float getCurrentScale() {
        return this.c;
    }
}
