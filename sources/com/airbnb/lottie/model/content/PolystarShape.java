package com.airbnb.lottie.model.content;

import android.graphics.PointF;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.PolystarContent;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.model.layer.BaseLayer;
/* loaded from: classes.dex */
public class PolystarShape implements ContentModel {

    /* renamed from: a  reason: collision with root package name */
    public final String f2041a;
    public final Type b;
    public final AnimatableFloatValue c;
    public final AnimatableValue<PointF, PointF> d;
    public final AnimatableFloatValue e;
    public final AnimatableFloatValue f;
    public final AnimatableFloatValue g;
    public final AnimatableFloatValue h;
    public final AnimatableFloatValue i;
    public final boolean j;
    public final boolean k;

    /* loaded from: classes.dex */
    public enum Type {
        STAR(1),
        POLYGON(2);
        
        private final int value;

        Type(int i) {
            this.value = i;
        }

        public static Type forValue(int i) {
            Type[] values;
            for (Type type : values()) {
                if (type.value == i) {
                    return type;
                }
            }
            return null;
        }
    }

    public PolystarShape(String str, Type type, AnimatableFloatValue animatableFloatValue, AnimatableValue<PointF, PointF> animatableValue, AnimatableFloatValue animatableFloatValue2, AnimatableFloatValue animatableFloatValue3, AnimatableFloatValue animatableFloatValue4, AnimatableFloatValue animatableFloatValue5, AnimatableFloatValue animatableFloatValue6, boolean z, boolean z2) {
        this.f2041a = str;
        this.b = type;
        this.c = animatableFloatValue;
        this.d = animatableValue;
        this.e = animatableFloatValue2;
        this.f = animatableFloatValue3;
        this.g = animatableFloatValue4;
        this.h = animatableFloatValue5;
        this.i = animatableFloatValue6;
        this.j = z;
        this.k = z2;
    }

    public AnimatableFloatValue getInnerRadius() {
        return this.f;
    }

    public AnimatableFloatValue getInnerRoundedness() {
        return this.h;
    }

    public String getName() {
        return this.f2041a;
    }

    public AnimatableFloatValue getOuterRadius() {
        return this.g;
    }

    public AnimatableFloatValue getOuterRoundedness() {
        return this.i;
    }

    public AnimatableFloatValue getPoints() {
        return this.c;
    }

    public AnimatableValue<PointF, PointF> getPosition() {
        return this.d;
    }

    public AnimatableFloatValue getRotation() {
        return this.e;
    }

    public Type getType() {
        return this.b;
    }

    public boolean isHidden() {
        return this.j;
    }

    public boolean isReversed() {
        return this.k;
    }

    @Override // com.airbnb.lottie.model.content.ContentModel
    public Content toContent(LottieDrawable lottieDrawable, LottieComposition lottieComposition, BaseLayer baseLayer) {
        return new PolystarContent(lottieDrawable, baseLayer, this);
    }
}
