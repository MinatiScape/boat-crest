package com.airbnb.lottie.model.content;

import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatableShapeValue;
/* loaded from: classes.dex */
public class Mask {

    /* renamed from: a  reason: collision with root package name */
    public final MaskMode f2039a;
    public final AnimatableShapeValue b;
    public final AnimatableIntegerValue c;
    public final boolean d;

    /* loaded from: classes.dex */
    public enum MaskMode {
        MASK_MODE_ADD,
        MASK_MODE_SUBTRACT,
        MASK_MODE_INTERSECT,
        MASK_MODE_NONE
    }

    public Mask(MaskMode maskMode, AnimatableShapeValue animatableShapeValue, AnimatableIntegerValue animatableIntegerValue, boolean z) {
        this.f2039a = maskMode;
        this.b = animatableShapeValue;
        this.c = animatableIntegerValue;
        this.d = z;
    }

    public MaskMode getMaskMode() {
        return this.f2039a;
    }

    public AnimatableShapeValue getMaskPath() {
        return this.b;
    }

    public AnimatableIntegerValue getOpacity() {
        return this.c;
    }

    public boolean isInverted() {
        return this.d;
    }
}
