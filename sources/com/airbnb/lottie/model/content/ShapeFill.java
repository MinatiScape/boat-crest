package com.airbnb.lottie.model.content;

import android.graphics.Path;
import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.FillContent;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.layer.BaseLayer;
/* loaded from: classes.dex */
public class ShapeFill implements ContentModel {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f2046a;
    public final Path.FillType b;
    public final String c;
    @Nullable
    public final AnimatableColorValue d;
    @Nullable
    public final AnimatableIntegerValue e;
    public final boolean f;

    public ShapeFill(String str, boolean z, Path.FillType fillType, @Nullable AnimatableColorValue animatableColorValue, @Nullable AnimatableIntegerValue animatableIntegerValue, boolean z2) {
        this.c = str;
        this.f2046a = z;
        this.b = fillType;
        this.d = animatableColorValue;
        this.e = animatableIntegerValue;
        this.f = z2;
    }

    @Nullable
    public AnimatableColorValue getColor() {
        return this.d;
    }

    public Path.FillType getFillType() {
        return this.b;
    }

    public String getName() {
        return this.c;
    }

    @Nullable
    public AnimatableIntegerValue getOpacity() {
        return this.e;
    }

    public boolean isHidden() {
        return this.f;
    }

    @Override // com.airbnb.lottie.model.content.ContentModel
    public Content toContent(LottieDrawable lottieDrawable, LottieComposition lottieComposition, BaseLayer baseLayer) {
        return new FillContent(lottieDrawable, baseLayer, this);
    }

    public String toString() {
        return "ShapeFill{color=, fillEnabled=" + this.f2046a + '}';
    }
}
