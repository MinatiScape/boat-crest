package com.google.android.gms.vision.face;

import android.graphics.PointF;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes10.dex */
public class Face {
    public static final float UNCOMPUTED_PROBABILITY = -1.0f;

    /* renamed from: a  reason: collision with root package name */
    public int f10192a;
    public PointF b;
    public float c;
    public float d;
    public float e;
    public float f;
    public List<Landmark> g;
    public final List<Contour> h;
    public float i;
    public float j;
    public float k;

    public Face(int i, PointF pointF, float f, float f2, float f3, float f4, float f5, Landmark[] landmarkArr, Contour[] contourArr, float f6, float f7, float f8, float f9) {
        this.f10192a = i;
        this.b = pointF;
        this.c = f;
        this.d = f2;
        this.e = f3;
        this.f = f4;
        this.g = Arrays.asList(landmarkArr);
        this.h = Arrays.asList(contourArr);
        this.i = a(f6);
        this.j = a(f7);
        this.k = a(f8);
        a(f9);
    }

    public static float a(float f) {
        if (f < 0.0f || f > 1.0f) {
            return -1.0f;
        }
        return f;
    }

    public List<Contour> getContours() {
        return this.h;
    }

    public float getEulerY() {
        return this.e;
    }

    public float getEulerZ() {
        return this.f;
    }

    public float getHeight() {
        return this.d;
    }

    public int getId() {
        return this.f10192a;
    }

    public float getIsLeftEyeOpenProbability() {
        return this.i;
    }

    public float getIsRightEyeOpenProbability() {
        return this.j;
    }

    public float getIsSmilingProbability() {
        return this.k;
    }

    public List<Landmark> getLandmarks() {
        return this.g;
    }

    public PointF getPosition() {
        PointF pointF = this.b;
        return new PointF(pointF.x - (this.c / 2.0f), pointF.y - (this.d / 2.0f));
    }

    public float getWidth() {
        return this.c;
    }
}
