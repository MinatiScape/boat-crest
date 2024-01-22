package com.airbnb.lottie.parser;

import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
/* loaded from: classes.dex */
public class DropShadowEffect {

    /* renamed from: a  reason: collision with root package name */
    public final AnimatableColorValue f2065a;
    public final AnimatableFloatValue b;
    public final AnimatableFloatValue c;
    public final AnimatableFloatValue d;
    public final AnimatableFloatValue e;

    public DropShadowEffect(AnimatableColorValue animatableColorValue, AnimatableFloatValue animatableFloatValue, AnimatableFloatValue animatableFloatValue2, AnimatableFloatValue animatableFloatValue3, AnimatableFloatValue animatableFloatValue4) {
        this.f2065a = animatableColorValue;
        this.b = animatableFloatValue;
        this.c = animatableFloatValue2;
        this.d = animatableFloatValue3;
        this.e = animatableFloatValue4;
    }

    public AnimatableColorValue getColor() {
        return this.f2065a;
    }

    public AnimatableFloatValue getDirection() {
        return this.c;
    }

    public AnimatableFloatValue getDistance() {
        return this.d;
    }

    public AnimatableFloatValue getOpacity() {
        return this.b;
    }

    public AnimatableFloatValue getRadius() {
        return this.e;
    }
}
