package com.airbnb.lottie.model.content;

import android.graphics.PointF;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.RectangleContent;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.model.layer.BaseLayer;
/* loaded from: classes.dex */
public class RectangleShape implements ContentModel {

    /* renamed from: a  reason: collision with root package name */
    public final String f2042a;
    public final AnimatableValue<PointF, PointF> b;
    public final AnimatableValue<PointF, PointF> c;
    public final AnimatableFloatValue d;
    public final boolean e;

    public RectangleShape(String str, AnimatableValue<PointF, PointF> animatableValue, AnimatableValue<PointF, PointF> animatableValue2, AnimatableFloatValue animatableFloatValue, boolean z) {
        this.f2042a = str;
        this.b = animatableValue;
        this.c = animatableValue2;
        this.d = animatableFloatValue;
        this.e = z;
    }

    public AnimatableFloatValue getCornerRadius() {
        return this.d;
    }

    public String getName() {
        return this.f2042a;
    }

    public AnimatableValue<PointF, PointF> getPosition() {
        return this.b;
    }

    public AnimatableValue<PointF, PointF> getSize() {
        return this.c;
    }

    public boolean isHidden() {
        return this.e;
    }

    @Override // com.airbnb.lottie.model.content.ContentModel
    public Content toContent(LottieDrawable lottieDrawable, LottieComposition lottieComposition, BaseLayer baseLayer) {
        return new RectangleContent(lottieDrawable, baseLayer, this);
    }

    public String toString() {
        return "RectangleShape{position=" + this.b + ", size=" + this.c + '}';
    }
}
