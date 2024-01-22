package com.airbnb.lottie.model.content;

import android.graphics.PointF;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.EllipseContent;
import com.airbnb.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.model.layer.BaseLayer;
/* loaded from: classes.dex */
public class CircleShape implements ContentModel {

    /* renamed from: a  reason: collision with root package name */
    public final String f2035a;
    public final AnimatableValue<PointF, PointF> b;
    public final AnimatablePointValue c;
    public final boolean d;
    public final boolean e;

    public CircleShape(String str, AnimatableValue<PointF, PointF> animatableValue, AnimatablePointValue animatablePointValue, boolean z, boolean z2) {
        this.f2035a = str;
        this.b = animatableValue;
        this.c = animatablePointValue;
        this.d = z;
        this.e = z2;
    }

    public String getName() {
        return this.f2035a;
    }

    public AnimatableValue<PointF, PointF> getPosition() {
        return this.b;
    }

    public AnimatablePointValue getSize() {
        return this.c;
    }

    public boolean isHidden() {
        return this.e;
    }

    public boolean isReversed() {
        return this.d;
    }

    @Override // com.airbnb.lottie.model.content.ContentModel
    public Content toContent(LottieDrawable lottieDrawable, LottieComposition lottieComposition, BaseLayer baseLayer) {
        return new EllipseContent(lottieDrawable, baseLayer, this);
    }
}
