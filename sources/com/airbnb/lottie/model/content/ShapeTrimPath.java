package com.airbnb.lottie.model.content;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.TrimPathContent;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.layer.BaseLayer;
/* loaded from: classes.dex */
public class ShapeTrimPath implements ContentModel {

    /* renamed from: a  reason: collision with root package name */
    public final String f2051a;
    public final Type b;
    public final AnimatableFloatValue c;
    public final AnimatableFloatValue d;
    public final AnimatableFloatValue e;
    public final boolean f;

    /* loaded from: classes.dex */
    public enum Type {
        SIMULTANEOUSLY,
        INDIVIDUALLY;

        public static Type forId(int i) {
            if (i != 1) {
                if (i == 2) {
                    return INDIVIDUALLY;
                }
                throw new IllegalArgumentException("Unknown trim path type " + i);
            }
            return SIMULTANEOUSLY;
        }
    }

    public ShapeTrimPath(String str, Type type, AnimatableFloatValue animatableFloatValue, AnimatableFloatValue animatableFloatValue2, AnimatableFloatValue animatableFloatValue3, boolean z) {
        this.f2051a = str;
        this.b = type;
        this.c = animatableFloatValue;
        this.d = animatableFloatValue2;
        this.e = animatableFloatValue3;
        this.f = z;
    }

    public AnimatableFloatValue getEnd() {
        return this.d;
    }

    public String getName() {
        return this.f2051a;
    }

    public AnimatableFloatValue getOffset() {
        return this.e;
    }

    public AnimatableFloatValue getStart() {
        return this.c;
    }

    public Type getType() {
        return this.b;
    }

    public boolean isHidden() {
        return this.f;
    }

    @Override // com.airbnb.lottie.model.content.ContentModel
    public Content toContent(LottieDrawable lottieDrawable, LottieComposition lottieComposition, BaseLayer baseLayer) {
        return new TrimPathContent(baseLayer, this);
    }

    public String toString() {
        return "Trim Path: {start: " + this.c + ", end: " + this.d + ", offset: " + this.e + "}";
    }
}
