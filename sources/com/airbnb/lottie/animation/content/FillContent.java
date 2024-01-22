package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ColorKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.DropShadowKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.ShapeFill;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class FillContent implements DrawingContent, BaseKeyframeAnimation.AnimationListener, KeyPathElementContent {

    /* renamed from: a  reason: collision with root package name */
    public final Path f1996a;
    public final Paint b;
    public final BaseLayer c;
    public final String d;
    public final boolean e;
    public final List<b> f;
    public final BaseKeyframeAnimation<Integer, Integer> g;
    public final BaseKeyframeAnimation<Integer, Integer> h;
    @Nullable
    public BaseKeyframeAnimation<ColorFilter, ColorFilter> i;
    public final LottieDrawable j;
    @Nullable
    public BaseKeyframeAnimation<Float, Float> k;
    public float l;
    @Nullable
    public DropShadowKeyframeAnimation m;

    public FillContent(LottieDrawable lottieDrawable, BaseLayer baseLayer, ShapeFill shapeFill) {
        Path path = new Path();
        this.f1996a = path;
        this.b = new LPaint(1);
        this.f = new ArrayList();
        this.c = baseLayer;
        this.d = shapeFill.getName();
        this.e = shapeFill.isHidden();
        this.j = lottieDrawable;
        if (baseLayer.getBlurEffect() != null) {
            BaseKeyframeAnimation<Float, Float> createAnimation = baseLayer.getBlurEffect().getBlurriness().createAnimation();
            this.k = createAnimation;
            createAnimation.addUpdateListener(this);
            baseLayer.addAnimation(this.k);
        }
        if (baseLayer.getDropShadowEffect() != null) {
            this.m = new DropShadowKeyframeAnimation(this, baseLayer, baseLayer.getDropShadowEffect());
        }
        if (shapeFill.getColor() != null && shapeFill.getOpacity() != null) {
            path.setFillType(shapeFill.getFillType());
            BaseKeyframeAnimation<Integer, Integer> createAnimation2 = shapeFill.getColor().createAnimation();
            this.g = createAnimation2;
            createAnimation2.addUpdateListener(this);
            baseLayer.addAnimation(createAnimation2);
            BaseKeyframeAnimation<Integer, Integer> createAnimation3 = shapeFill.getOpacity().createAnimation();
            this.h = createAnimation3;
            createAnimation3.addUpdateListener(this);
            baseLayer.addAnimation(createAnimation3);
            return;
        }
        this.g = null;
        this.h = null;
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public <T> void addValueCallback(T t, @Nullable LottieValueCallback<T> lottieValueCallback) {
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation;
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation2;
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation3;
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation4;
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation5;
        if (t == LottieProperty.COLOR) {
            this.g.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.OPACITY) {
            this.h.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.COLOR_FILTER) {
            BaseKeyframeAnimation<ColorFilter, ColorFilter> baseKeyframeAnimation = this.i;
            if (baseKeyframeAnimation != null) {
                this.c.removeAnimation(baseKeyframeAnimation);
            }
            if (lottieValueCallback == null) {
                this.i = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.i = valueCallbackKeyframeAnimation;
            valueCallbackKeyframeAnimation.addUpdateListener(this);
            this.c.addAnimation(this.i);
        } else if (t == LottieProperty.BLUR_RADIUS) {
            BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation2 = this.k;
            if (baseKeyframeAnimation2 != null) {
                baseKeyframeAnimation2.setValueCallback(lottieValueCallback);
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation2 = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.k = valueCallbackKeyframeAnimation2;
            valueCallbackKeyframeAnimation2.addUpdateListener(this);
            this.c.addAnimation(this.k);
        } else if (t == LottieProperty.DROP_SHADOW_COLOR && (dropShadowKeyframeAnimation5 = this.m) != null) {
            dropShadowKeyframeAnimation5.setColorCallback(lottieValueCallback);
        } else if (t == LottieProperty.DROP_SHADOW_OPACITY && (dropShadowKeyframeAnimation4 = this.m) != null) {
            dropShadowKeyframeAnimation4.setOpacityCallback(lottieValueCallback);
        } else if (t == LottieProperty.DROP_SHADOW_DIRECTION && (dropShadowKeyframeAnimation3 = this.m) != null) {
            dropShadowKeyframeAnimation3.setDirectionCallback(lottieValueCallback);
        } else if (t == LottieProperty.DROP_SHADOW_DISTANCE && (dropShadowKeyframeAnimation2 = this.m) != null) {
            dropShadowKeyframeAnimation2.setDistanceCallback(lottieValueCallback);
        } else if (t != LottieProperty.DROP_SHADOW_RADIUS || (dropShadowKeyframeAnimation = this.m) == null) {
        } else {
            dropShadowKeyframeAnimation.setRadiusCallback(lottieValueCallback);
        }
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public void draw(Canvas canvas, Matrix matrix, int i) {
        if (this.e) {
            return;
        }
        L.beginSection("FillContent#draw");
        this.b.setColor((MiscUtils.clamp((int) ((((i / 255.0f) * this.h.getValue().intValue()) / 100.0f) * 255.0f), 0, 255) << 24) | (((ColorKeyframeAnimation) this.g).getIntValue() & 16777215));
        BaseKeyframeAnimation<ColorFilter, ColorFilter> baseKeyframeAnimation = this.i;
        if (baseKeyframeAnimation != null) {
            this.b.setColorFilter(baseKeyframeAnimation.getValue());
        }
        BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation2 = this.k;
        if (baseKeyframeAnimation2 != null) {
            float floatValue = baseKeyframeAnimation2.getValue().floatValue();
            if (floatValue == 0.0f) {
                this.b.setMaskFilter(null);
            } else if (floatValue != this.l) {
                this.b.setMaskFilter(this.c.getBlurMaskFilter(floatValue));
            }
            this.l = floatValue;
        }
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation = this.m;
        if (dropShadowKeyframeAnimation != null) {
            dropShadowKeyframeAnimation.applyTo(this.b);
        }
        this.f1996a.reset();
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            this.f1996a.addPath(this.f.get(i2).getPath(), matrix);
        }
        canvas.drawPath(this.f1996a, this.b);
        L.endSection("FillContent#draw");
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public void getBounds(RectF rectF, Matrix matrix, boolean z) {
        this.f1996a.reset();
        for (int i = 0; i < this.f.size(); i++) {
            this.f1996a.addPath(this.f.get(i).getPath(), matrix);
        }
        this.f1996a.computeBounds(rectF, false);
        rectF.set(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f);
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.d;
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void onValueChanged() {
        this.j.invalidateSelf();
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void resolveKeyPath(KeyPath keyPath, int i, List<KeyPath> list, KeyPath keyPath2) {
        MiscUtils.resolveKeyPath(keyPath, i, list, keyPath2, this);
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void setContents(List<Content> list, List<Content> list2) {
        for (int i = 0; i < list2.size(); i++) {
            Content content = list2.get(i);
            if (content instanceof b) {
                this.f.add((b) content);
            }
        }
    }
}
