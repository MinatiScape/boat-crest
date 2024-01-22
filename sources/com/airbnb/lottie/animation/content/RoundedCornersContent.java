package com.airbnb.lottie.animation.content;

import android.graphics.PointF;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.CubicCurveData;
import com.airbnb.lottie.model.content.RoundedCorners;
import com.airbnb.lottie.model.content.ShapeData;
import com.airbnb.lottie.model.layer.BaseLayer;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class RoundedCornersContent implements ShapeModifierContent, BaseKeyframeAnimation.AnimationListener {

    /* renamed from: a  reason: collision with root package name */
    public final LottieDrawable f2004a;
    public final String b;
    public final BaseKeyframeAnimation<Float, Float> c;
    @Nullable
    public ShapeData d;

    public RoundedCornersContent(LottieDrawable lottieDrawable, BaseLayer baseLayer, RoundedCorners roundedCorners) {
        this.f2004a = lottieDrawable;
        this.b = roundedCorners.getName();
        BaseKeyframeAnimation<Float, Float> createAnimation = roundedCorners.getCornerRadius().createAnimation();
        this.c = createAnimation;
        baseLayer.addAnimation(createAnimation);
        createAnimation.addUpdateListener(this);
    }

    public static int a(int i, int i2) {
        int i3 = i / i2;
        return ((i ^ i2) >= 0 || i2 * i3 == i) ? i3 : i3 - 1;
    }

    public static int b(int i, int i2) {
        return i - (a(i, i2) * i2);
    }

    @NonNull
    public final ShapeData c(ShapeData shapeData) {
        List<CubicCurveData> curves = shapeData.getCurves();
        boolean isClosed = shapeData.isClosed();
        int size = curves.size() - 1;
        int i = 0;
        while (size >= 0) {
            CubicCurveData cubicCurveData = curves.get(size);
            CubicCurveData cubicCurveData2 = curves.get(b(size - 1, curves.size()));
            PointF vertex = (size != 0 || isClosed) ? cubicCurveData2.getVertex() : shapeData.getInitialPoint();
            i = (((size != 0 || isClosed) ? cubicCurveData2.getControlPoint2() : vertex).equals(vertex) && cubicCurveData.getControlPoint1().equals(vertex) && !(!shapeData.isClosed() && size == 0 && size == curves.size() - 1)) ? i + 2 : i + 1;
            size--;
        }
        ShapeData shapeData2 = this.d;
        if (shapeData2 == null || shapeData2.getCurves().size() != i) {
            ArrayList arrayList = new ArrayList(i);
            for (int i2 = 0; i2 < i; i2++) {
                arrayList.add(new CubicCurveData());
            }
            this.d = new ShapeData(new PointF(0.0f, 0.0f), false, arrayList);
        }
        this.d.setClosed(isClosed);
        return this.d;
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.b;
    }

    public BaseKeyframeAnimation<Float, Float> getRoundedCorners() {
        return this.c;
    }

    @Override // com.airbnb.lottie.animation.content.ShapeModifierContent
    public ShapeData modifyShape(ShapeData shapeData) {
        List<CubicCurveData> list;
        List<CubicCurveData> curves = shapeData.getCurves();
        if (curves.size() <= 2) {
            return shapeData;
        }
        float floatValue = this.c.getValue().floatValue();
        if (floatValue == 0.0f) {
            return shapeData;
        }
        ShapeData c = c(shapeData);
        c.setInitialPoint(shapeData.getInitialPoint().x, shapeData.getInitialPoint().y);
        List<CubicCurveData> curves2 = c.getCurves();
        boolean isClosed = shapeData.isClosed();
        int i = 0;
        int i2 = 0;
        while (i < curves.size()) {
            CubicCurveData cubicCurveData = curves.get(i);
            CubicCurveData cubicCurveData2 = curves.get(b(i - 1, curves.size()));
            CubicCurveData cubicCurveData3 = curves.get(b(i - 2, curves.size()));
            PointF vertex = (i != 0 || isClosed) ? cubicCurveData2.getVertex() : shapeData.getInitialPoint();
            PointF controlPoint2 = (i != 0 || isClosed) ? cubicCurveData2.getControlPoint2() : vertex;
            PointF controlPoint1 = cubicCurveData.getControlPoint1();
            PointF vertex2 = cubicCurveData3.getVertex();
            PointF vertex3 = cubicCurveData.getVertex();
            boolean z = !shapeData.isClosed() && i == 0 && i == curves.size() + (-1);
            if (controlPoint2.equals(vertex) && controlPoint1.equals(vertex) && !z) {
                float f = vertex.x;
                float f2 = f - vertex2.x;
                float f3 = vertex.y;
                float f4 = vertex3.x - f;
                list = curves;
                float min = Math.min(floatValue / ((float) Math.hypot(f2, f3 - vertex2.y)), 0.5f);
                float min2 = Math.min(floatValue / ((float) Math.hypot(f4, vertex3.y - f3)), 0.5f);
                float f5 = vertex.x;
                float f6 = ((vertex2.x - f5) * min) + f5;
                float f7 = vertex.y;
                float f8 = ((vertex2.y - f7) * min) + f7;
                float f9 = ((vertex3.x - f5) * min2) + f5;
                float f10 = ((vertex3.y - f7) * min2) + f7;
                float f11 = f6 - ((f6 - f5) * 0.5519f);
                float f12 = f8 - ((f8 - f7) * 0.5519f);
                float f13 = f9 - ((f9 - f5) * 0.5519f);
                float f14 = f10 - ((f10 - f7) * 0.5519f);
                CubicCurveData cubicCurveData4 = curves2.get(b(i2 - 1, curves2.size()));
                CubicCurveData cubicCurveData5 = curves2.get(i2);
                cubicCurveData4.setControlPoint2(f6, f8);
                cubicCurveData4.setVertex(f6, f8);
                if (i == 0) {
                    c.setInitialPoint(f6, f8);
                }
                cubicCurveData5.setControlPoint1(f11, f12);
                i2++;
                cubicCurveData5.setControlPoint2(f13, f14);
                cubicCurveData5.setVertex(f9, f10);
                curves2.get(i2).setControlPoint1(f9, f10);
            } else {
                list = curves;
                CubicCurveData cubicCurveData6 = curves2.get(b(i2 - 1, curves2.size()));
                cubicCurveData6.setControlPoint2(cubicCurveData2.getControlPoint2().x, cubicCurveData2.getControlPoint2().y);
                cubicCurveData6.setVertex(cubicCurveData2.getVertex().x, cubicCurveData2.getVertex().y);
                curves2.get(i2).setControlPoint1(cubicCurveData.getControlPoint1().x, cubicCurveData.getControlPoint1().y);
            }
            i2++;
            i++;
            curves = list;
        }
        return c;
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void onValueChanged() {
        this.f2004a.invalidateSelf();
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void setContents(List<Content> list, List<Content> list2) {
    }
}
