package com.airbnb.lottie.model.content;

import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.RoundedCornersContent;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.model.layer.BaseLayer;
/* loaded from: classes.dex */
public class RoundedCorners implements ContentModel {

    /* renamed from: a  reason: collision with root package name */
    public final String f2044a;
    public final AnimatableValue<Float, Float> b;

    public RoundedCorners(String str, AnimatableValue<Float, Float> animatableValue) {
        this.f2044a = str;
        this.b = animatableValue;
    }

    public AnimatableValue<Float, Float> getCornerRadius() {
        return this.b;
    }

    public String getName() {
        return this.f2044a;
    }

    @Override // com.airbnb.lottie.model.content.ContentModel
    @Nullable
    public Content toContent(LottieDrawable lottieDrawable, LottieComposition lottieComposition, BaseLayer baseLayer) {
        return new RoundedCornersContent(lottieDrawable, baseLayer, this);
    }
}
