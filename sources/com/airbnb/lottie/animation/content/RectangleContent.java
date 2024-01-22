package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.RectangleShape;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;
/* loaded from: classes.dex */
public class RectangleContent implements BaseKeyframeAnimation.AnimationListener, KeyPathElementContent, b {
    public final String c;
    public final boolean d;
    public final LottieDrawable e;
    public final BaseKeyframeAnimation<?, PointF> f;
    public final BaseKeyframeAnimation<?, PointF> g;
    public final BaseKeyframeAnimation<?, Float> h;
    public boolean k;

    /* renamed from: a  reason: collision with root package name */
    public final Path f2002a = new Path();
    public final RectF b = new RectF();
    public final CompoundTrimPathContent i = new CompoundTrimPathContent();
    @Nullable
    public BaseKeyframeAnimation<Float, Float> j = null;

    public RectangleContent(LottieDrawable lottieDrawable, BaseLayer baseLayer, RectangleShape rectangleShape) {
        this.c = rectangleShape.getName();
        this.d = rectangleShape.isHidden();
        this.e = lottieDrawable;
        BaseKeyframeAnimation<PointF, PointF> createAnimation = rectangleShape.getPosition().createAnimation();
        this.f = createAnimation;
        BaseKeyframeAnimation<PointF, PointF> createAnimation2 = rectangleShape.getSize().createAnimation();
        this.g = createAnimation2;
        BaseKeyframeAnimation<Float, Float> createAnimation3 = rectangleShape.getCornerRadius().createAnimation();
        this.h = createAnimation3;
        baseLayer.addAnimation(createAnimation);
        baseLayer.addAnimation(createAnimation2);
        baseLayer.addAnimation(createAnimation3);
        createAnimation.addUpdateListener(this);
        createAnimation2.addUpdateListener(this);
        createAnimation3.addUpdateListener(this);
    }

    public final void a() {
        this.k = false;
        this.e.invalidateSelf();
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public <T> void addValueCallback(T t, @Nullable LottieValueCallback<T> lottieValueCallback) {
        if (t == LottieProperty.RECTANGLE_SIZE) {
            this.g.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.POSITION) {
            this.f.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.CORNER_RADIUS) {
            this.h.setValueCallback(lottieValueCallback);
        }
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.c;
    }

    @Override // com.airbnb.lottie.animation.content.b
    public Path getPath() {
        BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation;
        if (this.k) {
            return this.f2002a;
        }
        this.f2002a.reset();
        if (this.d) {
            this.k = true;
            return this.f2002a;
        }
        PointF value = this.g.getValue();
        float f = value.x / 2.0f;
        float f2 = value.y / 2.0f;
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation2 = this.h;
        float floatValue = baseKeyframeAnimation2 == null ? 0.0f : ((FloatKeyframeAnimation) baseKeyframeAnimation2).getFloatValue();
        if (floatValue == 0.0f && (baseKeyframeAnimation = this.j) != null) {
            floatValue = Math.min(baseKeyframeAnimation.getValue().floatValue(), Math.min(f, f2));
        }
        float min = Math.min(f, f2);
        if (floatValue > min) {
            floatValue = min;
        }
        PointF value2 = this.f.getValue();
        this.f2002a.moveTo(value2.x + f, (value2.y - f2) + floatValue);
        this.f2002a.lineTo(value2.x + f, (value2.y + f2) - floatValue);
        int i = (floatValue > 0.0f ? 1 : (floatValue == 0.0f ? 0 : -1));
        if (i > 0) {
            RectF rectF = this.b;
            float f3 = value2.x;
            float f4 = floatValue * 2.0f;
            float f5 = value2.y;
            rectF.set((f3 + f) - f4, (f5 + f2) - f4, f3 + f, f5 + f2);
            this.f2002a.arcTo(this.b, 0.0f, 90.0f, false);
        }
        this.f2002a.lineTo((value2.x - f) + floatValue, value2.y + f2);
        if (i > 0) {
            RectF rectF2 = this.b;
            float f6 = value2.x;
            float f7 = value2.y;
            float f8 = floatValue * 2.0f;
            rectF2.set(f6 - f, (f7 + f2) - f8, (f6 - f) + f8, f7 + f2);
            this.f2002a.arcTo(this.b, 90.0f, 90.0f, false);
        }
        this.f2002a.lineTo(value2.x - f, (value2.y - f2) + floatValue);
        if (i > 0) {
            RectF rectF3 = this.b;
            float f9 = value2.x;
            float f10 = value2.y;
            float f11 = floatValue * 2.0f;
            rectF3.set(f9 - f, f10 - f2, (f9 - f) + f11, (f10 - f2) + f11);
            this.f2002a.arcTo(this.b, 180.0f, 90.0f, false);
        }
        this.f2002a.lineTo((value2.x + f) - floatValue, value2.y - f2);
        if (i > 0) {
            RectF rectF4 = this.b;
            float f12 = value2.x;
            float f13 = floatValue * 2.0f;
            float f14 = value2.y;
            rectF4.set((f12 + f) - f13, f14 - f2, f12 + f, (f14 - f2) + f13);
            this.f2002a.arcTo(this.b, 270.0f, 90.0f, false);
        }
        this.f2002a.close();
        this.i.apply(this.f2002a);
        this.k = true;
        return this.f2002a;
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
                    this.i.a(trimPathContent);
                    trimPathContent.a(this);
                }
            }
            if (content instanceof RoundedCornersContent) {
                this.j = ((RoundedCornersContent) content).getRoundedCorners();
            }
        }
    }
}
