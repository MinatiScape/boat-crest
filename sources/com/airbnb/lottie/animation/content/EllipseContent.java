package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import android.graphics.PointF;
import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.CircleShape;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;
/* loaded from: classes.dex */
public class EllipseContent implements b, BaseKeyframeAnimation.AnimationListener, KeyPathElementContent {
    public final String b;
    public final LottieDrawable c;
    public final BaseKeyframeAnimation<?, PointF> d;
    public final BaseKeyframeAnimation<?, PointF> e;
    public final CircleShape f;
    public boolean h;

    /* renamed from: a  reason: collision with root package name */
    public final Path f1995a = new Path();
    public final CompoundTrimPathContent g = new CompoundTrimPathContent();

    public EllipseContent(LottieDrawable lottieDrawable, BaseLayer baseLayer, CircleShape circleShape) {
        this.b = circleShape.getName();
        this.c = lottieDrawable;
        BaseKeyframeAnimation<PointF, PointF> createAnimation = circleShape.getSize().createAnimation();
        this.d = createAnimation;
        BaseKeyframeAnimation<PointF, PointF> createAnimation2 = circleShape.getPosition().createAnimation();
        this.e = createAnimation2;
        this.f = circleShape;
        baseLayer.addAnimation(createAnimation);
        baseLayer.addAnimation(createAnimation2);
        createAnimation.addUpdateListener(this);
        createAnimation2.addUpdateListener(this);
    }

    public final void a() {
        this.h = false;
        this.c.invalidateSelf();
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public <T> void addValueCallback(T t, @Nullable LottieValueCallback<T> lottieValueCallback) {
        if (t == LottieProperty.ELLIPSE_SIZE) {
            this.d.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.POSITION) {
            this.e.setValueCallback(lottieValueCallback);
        }
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.b;
    }

    @Override // com.airbnb.lottie.animation.content.b
    public Path getPath() {
        if (this.h) {
            return this.f1995a;
        }
        this.f1995a.reset();
        if (this.f.isHidden()) {
            this.h = true;
            return this.f1995a;
        }
        PointF value = this.d.getValue();
        float f = value.x / 2.0f;
        float f2 = value.y / 2.0f;
        float f3 = f * 0.55228f;
        float f4 = 0.55228f * f2;
        this.f1995a.reset();
        if (this.f.isReversed()) {
            float f5 = -f2;
            this.f1995a.moveTo(0.0f, f5);
            float f6 = 0.0f - f3;
            float f7 = -f;
            float f8 = 0.0f - f4;
            this.f1995a.cubicTo(f6, f5, f7, f8, f7, 0.0f);
            float f9 = f4 + 0.0f;
            this.f1995a.cubicTo(f7, f9, f6, f2, 0.0f, f2);
            float f10 = f3 + 0.0f;
            this.f1995a.cubicTo(f10, f2, f, f9, f, 0.0f);
            this.f1995a.cubicTo(f, f8, f10, f5, 0.0f, f5);
        } else {
            float f11 = -f2;
            this.f1995a.moveTo(0.0f, f11);
            float f12 = f3 + 0.0f;
            float f13 = 0.0f - f4;
            this.f1995a.cubicTo(f12, f11, f, f13, f, 0.0f);
            float f14 = f4 + 0.0f;
            this.f1995a.cubicTo(f, f14, f12, f2, 0.0f, f2);
            float f15 = 0.0f - f3;
            float f16 = -f;
            this.f1995a.cubicTo(f15, f2, f16, f14, f16, 0.0f);
            this.f1995a.cubicTo(f16, f13, f15, f11, 0.0f, f11);
        }
        PointF value2 = this.e.getValue();
        this.f1995a.offset(value2.x, value2.y);
        this.f1995a.close();
        this.g.apply(this.f1995a);
        this.h = true;
        return this.f1995a;
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void onValueChanged() {
        a();
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void resolveKeyPath(KeyPath keyPath, int i, List<KeyPath> list, KeyPath keyPath2) {
        MiscUtils.resolveKeyPath(keyPath, i, list, keyPath2, this);
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void setContents(List<Content> list, List<Content> list2) {
        for (int i = 0; i < list.size(); i++) {
            Content content = list.get(i);
            if (content instanceof TrimPathContent) {
                TrimPathContent trimPathContent = (TrimPathContent) content;
                if (trimPathContent.b() == ShapeTrimPath.Type.SIMULTANEOUSLY) {
                    this.g.a(trimPathContent);
                    trimPathContent.a(this);
                }
            }
        }
    }
}
