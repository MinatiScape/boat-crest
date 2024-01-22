package com.airbnb.lottie.model.content;

import android.graphics.Path;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.GradientFillContent;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableGradientColorValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.lottie.model.layer.BaseLayer;
/* loaded from: classes.dex */
public class GradientFill implements ContentModel {

    /* renamed from: a  reason: collision with root package name */
    public final GradientType f2037a;
    public final Path.FillType b;
    public final AnimatableGradientColorValue c;
    public final AnimatableIntegerValue d;
    public final AnimatablePointValue e;
    public final AnimatablePointValue f;
    public final String g;
    public final boolean h;

    public GradientFill(String str, GradientType gradientType, Path.FillType fillType, AnimatableGradientColorValue animatableGradientColorValue, AnimatableIntegerValue animatableIntegerValue, AnimatablePointValue animatablePointValue, AnimatablePointValue animatablePointValue2, AnimatableFloatValue animatableFloatValue, AnimatableFloatValue animatableFloatValue2, boolean z) {
        this.f2037a = gradientType;
        this.b = fillType;
        this.c = animatableGradientColorValue;
        this.d = animatableIntegerValue;
        this.e = animatablePointValue;
        this.f = animatablePointValue2;
        this.g = str;
        this.h = z;
    }

    public AnimatablePointValue getEndPoint() {
        return this.f;
    }

    public Path.FillType getFillType() {
        return this.b;
    }

    public AnimatableGradientColorValue getGradientColor() {
        return this.c;
    }

    public GradientType getGradientType() {
        return this.f2037a;
    }

    public String getName() {
        return this.g;
    }

    public AnimatableIntegerValue getOpacity() {
        return this.d;
    }

    public AnimatablePointValue getStartPoint() {
        return this.e;
    }

    public boolean isHidden() {
        return this.h;
    }

    @Override // com.airbnb.lottie.model.content.ContentModel
    public Content toContent(LottieDrawable lottieDrawable, LottieComposition lottieComposition, BaseLayer baseLayer) {
        return new GradientFillContent(lottieDrawable, lottieComposition, baseLayer, this);
    }
}
