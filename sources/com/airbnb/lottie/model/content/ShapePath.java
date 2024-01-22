package com.airbnb.lottie.model.content;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.ShapeContent;
import com.airbnb.lottie.model.animatable.AnimatableShapeValue;
import com.airbnb.lottie.model.layer.BaseLayer;
/* loaded from: classes.dex */
public class ShapePath implements ContentModel {

    /* renamed from: a  reason: collision with root package name */
    public final String f2048a;
    public final int b;
    public final AnimatableShapeValue c;
    public final boolean d;

    public ShapePath(String str, int i, AnimatableShapeValue animatableShapeValue, boolean z) {
        this.f2048a = str;
        this.b = i;
        this.c = animatableShapeValue;
        this.d = z;
    }

    public String getName() {
        return this.f2048a;
    }

    public AnimatableShapeValue getShapePath() {
        return this.c;
    }

    public boolean isHidden() {
        return this.d;
    }

    @Override // com.airbnb.lottie.model.content.ContentModel
    public Content toContent(LottieDrawable lottieDrawable, LottieComposition lottieComposition, BaseLayer baseLayer) {
        return new ShapeContent(lottieDrawable, baseLayer, this);
    }

    public String toString() {
        return "ShapePath{name=" + this.f2048a + ", index=" + this.b + '}';
    }
}
