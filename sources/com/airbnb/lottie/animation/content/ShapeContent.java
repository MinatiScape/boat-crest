package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ShapeKeyframeAnimation;
import com.airbnb.lottie.model.content.ShapePath;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.layer.BaseLayer;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class ShapeContent implements b, BaseKeyframeAnimation.AnimationListener {
    public final String b;
    public final boolean c;
    public final LottieDrawable d;
    public final ShapeKeyframeAnimation e;
    public boolean f;

    /* renamed from: a  reason: collision with root package name */
    public final Path f2005a = new Path();
    public final CompoundTrimPathContent g = new CompoundTrimPathContent();

    public ShapeContent(LottieDrawable lottieDrawable, BaseLayer baseLayer, ShapePath shapePath) {
        this.b = shapePath.getName();
        this.c = shapePath.isHidden();
        this.d = lottieDrawable;
        ShapeKeyframeAnimation createAnimation = shapePath.getShapePath().createAnimation();
        this.e = createAnimation;
        baseLayer.addAnimation(createAnimation);
        createAnimation.addUpdateListener(this);
    }

    public final void a() {
        this.f = false;
        this.d.invalidateSelf();
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.b;
    }

    @Override // com.airbnb.lottie.animation.content.b
    public Path getPath() {
        if (this.f) {
            return this.f2005a;
        }
        this.f2005a.reset();
        if (this.c) {
            this.f = true;
            return this.f2005a;
        }
        Path value = this.e.getValue();
        if (value == null) {
            return this.f2005a;
        }
        this.f2005a.set(value);
        this.f2005a.setFillType(Path.FillType.EVEN_ODD);
        this.g.apply(this.f2005a);
        this.f = true;
        return this.f2005a;
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void onValueChanged() {
        a();
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void setContents(List<Content> list, List<Content> list2) {
        ArrayList arrayList = null;
        for (int i = 0; i < list.size(); i++) {
            Content content = list.get(i);
            if (content instanceof TrimPathContent) {
                TrimPathContent trimPathContent = (TrimPathContent) content;
                if (trimPathContent.b() == ShapeTrimPath.Type.SIMULTANEOUSLY) {
                    this.g.a(trimPathContent);
                    trimPathContent.a(this);
                }
            }
            if (content instanceof ShapeModifierContent) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add((ShapeModifierContent) content);
            }
        }
        this.e.setShapeModifiers(arrayList);
    }
}
