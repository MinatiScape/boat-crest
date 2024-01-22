package com.airbnb.lottie.model.content;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.ContentGroup;
import com.airbnb.lottie.model.layer.BaseLayer;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
public class ShapeGroup implements ContentModel {

    /* renamed from: a  reason: collision with root package name */
    public final String f2047a;
    public final List<ContentModel> b;
    public final boolean c;

    public ShapeGroup(String str, List<ContentModel> list, boolean z) {
        this.f2047a = str;
        this.b = list;
        this.c = z;
    }

    public List<ContentModel> getItems() {
        return this.b;
    }

    public String getName() {
        return this.f2047a;
    }

    public boolean isHidden() {
        return this.c;
    }

    @Override // com.airbnb.lottie.model.content.ContentModel
    public Content toContent(LottieDrawable lottieDrawable, LottieComposition lottieComposition, BaseLayer baseLayer) {
        return new ContentGroup(lottieDrawable, baseLayer, this, lottieComposition);
    }

    public String toString() {
        return "ShapeGroup{name='" + this.f2047a + "' Shapes: " + Arrays.toString(this.b.toArray()) + '}';
    }
}
