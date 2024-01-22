package com.airbnb.lottie.model;

import android.annotation.SuppressLint;
import android.graphics.PointF;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class CubicCurveData {

    /* renamed from: a  reason: collision with root package name */
    public final PointF f2023a;
    public final PointF b;
    public final PointF c;

    public CubicCurveData() {
        this.f2023a = new PointF();
        this.b = new PointF();
        this.c = new PointF();
    }

    public PointF getControlPoint1() {
        return this.f2023a;
    }

    public PointF getControlPoint2() {
        return this.b;
    }

    public PointF getVertex() {
        return this.c;
    }

    public void setControlPoint1(float f, float f2) {
        this.f2023a.set(f, f2);
    }

    public void setControlPoint2(float f, float f2) {
        this.b.set(f, f2);
    }

    public void setFrom(CubicCurveData cubicCurveData) {
        PointF pointF = cubicCurveData.c;
        setVertex(pointF.x, pointF.y);
        PointF pointF2 = cubicCurveData.f2023a;
        setControlPoint1(pointF2.x, pointF2.y);
        PointF pointF3 = cubicCurveData.b;
        setControlPoint2(pointF3.x, pointF3.y);
    }

    public void setVertex(float f, float f2) {
        this.c.set(f, f2);
    }

    @NonNull
    @SuppressLint({"DefaultLocale"})
    public String toString() {
        return String.format("v=%.2f,%.2f cp1=%.2f,%.2f cp2=%.2f,%.2f", Float.valueOf(this.c.x), Float.valueOf(this.c.y), Float.valueOf(this.f2023a.x), Float.valueOf(this.f2023a.y), Float.valueOf(this.b.x), Float.valueOf(this.b.y));
    }

    public CubicCurveData(PointF pointF, PointF pointF2, PointF pointF3) {
        this.f2023a = pointF;
        this.b = pointF2;
        this.c = pointF3;
    }
}
