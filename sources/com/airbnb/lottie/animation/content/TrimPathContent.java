package com.airbnb.lottie.animation.content;

import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.layer.BaseLayer;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class TrimPathContent implements Content, BaseKeyframeAnimation.AnimationListener {

    /* renamed from: a  reason: collision with root package name */
    public final String f2006a;
    public final boolean b;
    public final List<BaseKeyframeAnimation.AnimationListener> c = new ArrayList();
    public final ShapeTrimPath.Type d;
    public final BaseKeyframeAnimation<?, Float> e;
    public final BaseKeyframeAnimation<?, Float> f;
    public final BaseKeyframeAnimation<?, Float> g;

    public TrimPathContent(BaseLayer baseLayer, ShapeTrimPath shapeTrimPath) {
        this.f2006a = shapeTrimPath.getName();
        this.b = shapeTrimPath.isHidden();
        this.d = shapeTrimPath.getType();
        BaseKeyframeAnimation<Float, Float> createAnimation = shapeTrimPath.getStart().createAnimation();
        this.e = createAnimation;
        BaseKeyframeAnimation<Float, Float> createAnimation2 = shapeTrimPath.getEnd().createAnimation();
        this.f = createAnimation2;
        BaseKeyframeAnimation<Float, Float> createAnimation3 = shapeTrimPath.getOffset().createAnimation();
        this.g = createAnimation3;
        baseLayer.addAnimation(createAnimation);
        baseLayer.addAnimation(createAnimation2);
        baseLayer.addAnimation(createAnimation3);
        createAnimation.addUpdateListener(this);
        createAnimation2.addUpdateListener(this);
        createAnimation3.addUpdateListener(this);
    }

    public void a(BaseKeyframeAnimation.AnimationListener animationListener) {
        this.c.add(animationListener);
    }

    public ShapeTrimPath.Type b() {
        return this.d;
    }

    public BaseKeyframeAnimation<?, Float> getEnd() {
        return this.f;
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.f2006a;
    }

    public BaseKeyframeAnimation<?, Float> getOffset() {
        return this.g;
    }

    public BaseKeyframeAnimation<?, Float> getStart() {
        return this.e;
    }

    public boolean isHidden() {
        return this.b;
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void onValueChanged() {
        for (int i = 0; i < this.c.size(); i++) {
            this.c.get(i).onValueChanged();
        }
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void setContents(List<Content> list, List<Content> list2) {
    }
}
