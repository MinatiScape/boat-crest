package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.DropShadowKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.IntegerKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public abstract class BaseStrokeContent implements BaseKeyframeAnimation.AnimationListener, KeyPathElementContent, DrawingContent {
    public final LottieDrawable e;
    public final float[] g;
    public final Paint h;
    public final BaseKeyframeAnimation<?, Float> i;
    public final BaseKeyframeAnimation<?, Integer> j;
    public final List<BaseKeyframeAnimation<?, Float>> k;
    @Nullable
    public final BaseKeyframeAnimation<?, Float> l;
    public final BaseLayer layer;
    @Nullable
    public BaseKeyframeAnimation<ColorFilter, ColorFilter> m;
    @Nullable
    public BaseKeyframeAnimation<Float, Float> n;
    public float o;
    @Nullable
    public DropShadowKeyframeAnimation p;

    /* renamed from: a  reason: collision with root package name */
    public final PathMeasure f1991a = new PathMeasure();
    public final Path b = new Path();
    public final Path c = new Path();
    public final RectF d = new RectF();
    public final List<b> f = new ArrayList();

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public final List<com.airbnb.lottie.animation.content.b> f1992a;
        @Nullable
        public final TrimPathContent b;

        public b(@Nullable TrimPathContent trimPathContent) {
            this.f1992a = new ArrayList();
            this.b = trimPathContent;
        }
    }

    public BaseStrokeContent(LottieDrawable lottieDrawable, BaseLayer baseLayer, Paint.Cap cap, Paint.Join join, float f, AnimatableIntegerValue animatableIntegerValue, AnimatableFloatValue animatableFloatValue, List<AnimatableFloatValue> list, AnimatableFloatValue animatableFloatValue2) {
        LPaint lPaint = new LPaint(1);
        this.h = lPaint;
        this.o = 0.0f;
        this.e = lottieDrawable;
        this.layer = baseLayer;
        lPaint.setStyle(Paint.Style.STROKE);
        lPaint.setStrokeCap(cap);
        lPaint.setStrokeJoin(join);
        lPaint.setStrokeMiter(f);
        this.j = animatableIntegerValue.createAnimation();
        this.i = animatableFloatValue.createAnimation();
        if (animatableFloatValue2 == null) {
            this.l = null;
        } else {
            this.l = animatableFloatValue2.createAnimation();
        }
        this.k = new ArrayList(list.size());
        this.g = new float[list.size()];
        for (int i = 0; i < list.size(); i++) {
            this.k.add(list.get(i).createAnimation());
        }
        baseLayer.addAnimation(this.j);
        baseLayer.addAnimation(this.i);
        for (int i2 = 0; i2 < this.k.size(); i2++) {
            baseLayer.addAnimation(this.k.get(i2));
        }
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation = this.l;
        if (baseKeyframeAnimation != null) {
            baseLayer.addAnimation(baseKeyframeAnimation);
        }
        this.j.addUpdateListener(this);
        this.i.addUpdateListener(this);
        for (int i3 = 0; i3 < list.size(); i3++) {
            this.k.get(i3).addUpdateListener(this);
        }
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation2 = this.l;
        if (baseKeyframeAnimation2 != null) {
            baseKeyframeAnimation2.addUpdateListener(this);
        }
        if (baseLayer.getBlurEffect() != null) {
            BaseKeyframeAnimation<Float, Float> createAnimation = baseLayer.getBlurEffect().getBlurriness().createAnimation();
            this.n = createAnimation;
            createAnimation.addUpdateListener(this);
            baseLayer.addAnimation(this.n);
        }
        if (baseLayer.getDropShadowEffect() != null) {
            this.p = new DropShadowKeyframeAnimation(this, baseLayer, baseLayer.getDropShadowEffect());
        }
    }

    public final void a(Matrix matrix) {
        L.beginSection("StrokeContent#applyDashPattern");
        if (this.k.isEmpty()) {
            L.endSection("StrokeContent#applyDashPattern");
            return;
        }
        float scale = Utils.getScale(matrix);
        for (int i = 0; i < this.k.size(); i++) {
            this.g[i] = this.k.get(i).getValue().floatValue();
            if (i % 2 == 0) {
                float[] fArr = this.g;
                if (fArr[i] < 1.0f) {
                    fArr[i] = 1.0f;
                }
            } else {
                float[] fArr2 = this.g;
                if (fArr2[i] < 0.1f) {
                    fArr2[i] = 0.1f;
                }
            }
            float[] fArr3 = this.g;
            fArr3[i] = fArr3[i] * scale;
        }
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation = this.l;
        this.h.setPathEffect(new DashPathEffect(this.g, baseKeyframeAnimation == null ? 0.0f : scale * baseKeyframeAnimation.getValue().floatValue()));
        L.endSection("StrokeContent#applyDashPattern");
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    @CallSuper
    public <T> void addValueCallback(T t, @Nullable LottieValueCallback<T> lottieValueCallback) {
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation;
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation2;
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation3;
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation4;
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation5;
        if (t == LottieProperty.OPACITY) {
            this.j.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.STROKE_WIDTH) {
            this.i.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.COLOR_FILTER) {
            BaseKeyframeAnimation<ColorFilter, ColorFilter> baseKeyframeAnimation = this.m;
            if (baseKeyframeAnimation != null) {
                this.layer.removeAnimation(baseKeyframeAnimation);
            }
            if (lottieValueCallback == null) {
                this.m = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.m = valueCallbackKeyframeAnimation;
            valueCallbackKeyframeAnimation.addUpdateListener(this);
            this.layer.addAnimation(this.m);
        } else if (t == LottieProperty.BLUR_RADIUS) {
            BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation2 = this.n;
            if (baseKeyframeAnimation2 != null) {
                baseKeyframeAnimation2.setValueCallback(lottieValueCallback);
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation2 = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.n = valueCallbackKeyframeAnimation2;
            valueCallbackKeyframeAnimation2.addUpdateListener(this);
            this.layer.addAnimation(this.n);
        } else if (t == LottieProperty.DROP_SHADOW_COLOR && (dropShadowKeyframeAnimation5 = this.p) != null) {
            dropShadowKeyframeAnimation5.setColorCallback(lottieValueCallback);
        } else if (t == LottieProperty.DROP_SHADOW_OPACITY && (dropShadowKeyframeAnimation4 = this.p) != null) {
            dropShadowKeyframeAnimation4.setOpacityCallback(lottieValueCallback);
        } else if (t == LottieProperty.DROP_SHADOW_DIRECTION && (dropShadowKeyframeAnimation3 = this.p) != null) {
            dropShadowKeyframeAnimation3.setDirectionCallback(lottieValueCallback);
        } else if (t == LottieProperty.DROP_SHADOW_DISTANCE && (dropShadowKeyframeAnimation2 = this.p) != null) {
            dropShadowKeyframeAnimation2.setDistanceCallback(lottieValueCallback);
        } else if (t != LottieProperty.DROP_SHADOW_RADIUS || (dropShadowKeyframeAnimation = this.p) == null) {
        } else {
            dropShadowKeyframeAnimation.setRadiusCallback(lottieValueCallback);
        }
    }

    public final void b(Canvas canvas, b bVar, Matrix matrix) {
        L.beginSection("StrokeContent#applyTrimPath");
        if (bVar.b == null) {
            L.endSection("StrokeContent#applyTrimPath");
            return;
        }
        this.b.reset();
        for (int size = bVar.f1992a.size() - 1; size >= 0; size--) {
            this.b.addPath(((com.airbnb.lottie.animation.content.b) bVar.f1992a.get(size)).getPath(), matrix);
        }
        float floatValue = bVar.b.getStart().getValue().floatValue() / 100.0f;
        float floatValue2 = bVar.b.getEnd().getValue().floatValue() / 100.0f;
        float floatValue3 = bVar.b.getOffset().getValue().floatValue() / 360.0f;
        if (floatValue < 0.01f && floatValue2 > 0.99f) {
            canvas.drawPath(this.b, this.h);
            L.endSection("StrokeContent#applyTrimPath");
            return;
        }
        this.f1991a.setPath(this.b, false);
        float length = this.f1991a.getLength();
        while (this.f1991a.nextContour()) {
            length += this.f1991a.getLength();
        }
        float f = floatValue3 * length;
        float f2 = (floatValue * length) + f;
        float min = Math.min((floatValue2 * length) + f, (f2 + length) - 1.0f);
        float f3 = 0.0f;
        for (int size2 = bVar.f1992a.size() - 1; size2 >= 0; size2--) {
            this.c.set(((com.airbnb.lottie.animation.content.b) bVar.f1992a.get(size2)).getPath());
            this.c.transform(matrix);
            this.f1991a.setPath(this.c, false);
            float length2 = this.f1991a.getLength();
            if (min > length) {
                float f4 = min - length;
                if (f4 < f3 + length2 && f3 < f4) {
                    Utils.applyTrimPathIfNeeded(this.c, f2 > length ? (f2 - length) / length2 : 0.0f, Math.min(f4 / length2, 1.0f), 0.0f);
                    canvas.drawPath(this.c, this.h);
                    f3 += length2;
                }
            }
            float f5 = f3 + length2;
            if (f5 >= f2 && f3 <= min) {
                if (f5 <= min && f2 < f3) {
                    canvas.drawPath(this.c, this.h);
                } else {
                    Utils.applyTrimPathIfNeeded(this.c, f2 < f3 ? 0.0f : (f2 - f3) / length2, min > f5 ? 1.0f : (min - f3) / length2, 0.0f);
                    canvas.drawPath(this.c, this.h);
                }
            }
            f3 += length2;
        }
        L.endSection("StrokeContent#applyTrimPath");
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public void draw(Canvas canvas, Matrix matrix, int i) {
        L.beginSection("StrokeContent#draw");
        if (Utils.hasZeroScaleAxis(matrix)) {
            L.endSection("StrokeContent#draw");
            return;
        }
        this.h.setAlpha(MiscUtils.clamp((int) ((((i / 255.0f) * ((IntegerKeyframeAnimation) this.j).getIntValue()) / 100.0f) * 255.0f), 0, 255));
        this.h.setStrokeWidth(((FloatKeyframeAnimation) this.i).getFloatValue() * Utils.getScale(matrix));
        if (this.h.getStrokeWidth() <= 0.0f) {
            L.endSection("StrokeContent#draw");
            return;
        }
        a(matrix);
        BaseKeyframeAnimation<ColorFilter, ColorFilter> baseKeyframeAnimation = this.m;
        if (baseKeyframeAnimation != null) {
            this.h.setColorFilter(baseKeyframeAnimation.getValue());
        }
        BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation2 = this.n;
        if (baseKeyframeAnimation2 != null) {
            float floatValue = baseKeyframeAnimation2.getValue().floatValue();
            if (floatValue == 0.0f) {
                this.h.setMaskFilter(null);
            } else if (floatValue != this.o) {
                this.h.setMaskFilter(this.layer.getBlurMaskFilter(floatValue));
            }
            this.o = floatValue;
        }
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation = this.p;
        if (dropShadowKeyframeAnimation != null) {
            dropShadowKeyframeAnimation.applyTo(this.h);
        }
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            b bVar = this.f.get(i2);
            if (bVar.b != null) {
                b(canvas, bVar, matrix);
            } else {
                L.beginSection("StrokeContent#buildPath");
                this.b.reset();
                for (int size = bVar.f1992a.size() - 1; size >= 0; size--) {
                    this.b.addPath(((com.airbnb.lottie.animation.content.b) bVar.f1992a.get(size)).getPath(), matrix);
                }
                L.endSection("StrokeContent#buildPath");
                L.beginSection("StrokeContent#drawPath");
                canvas.drawPath(this.b, this.h);
                L.endSection("StrokeContent#drawPath");
            }
        }
        L.endSection("StrokeContent#draw");
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public void getBounds(RectF rectF, Matrix matrix, boolean z) {
        L.beginSection("StrokeContent#getBounds");
        this.b.reset();
        for (int i = 0; i < this.f.size(); i++) {
            b bVar = this.f.get(i);
            for (int i2 = 0; i2 < bVar.f1992a.size(); i2++) {
                this.b.addPath(((com.airbnb.lottie.animation.content.b) bVar.f1992a.get(i2)).getPath(), matrix);
            }
        }
        this.b.computeBounds(this.d, false);
        float floatValue = ((FloatKeyframeAnimation) this.i).getFloatValue();
        RectF rectF2 = this.d;
        float f = floatValue / 2.0f;
        rectF2.set(rectF2.left - f, rectF2.top - f, rectF2.right + f, rectF2.bottom + f);
        rectF.set(this.d);
        rectF.set(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f);
        L.endSection("StrokeContent#getBounds");
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void onValueChanged() {
        this.e.invalidateSelf();
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void resolveKeyPath(KeyPath keyPath, int i, List<KeyPath> list, KeyPath keyPath2) {
        MiscUtils.resolveKeyPath(keyPath, i, list, keyPath2, this);
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void setContents(List<Content> list, List<Content> list2) {
        TrimPathContent trimPathContent = null;
        for (int size = list.size() - 1; size >= 0; size--) {
            Content content = list.get(size);
            if (content instanceof TrimPathContent) {
                TrimPathContent trimPathContent2 = (TrimPathContent) content;
                if (trimPathContent2.b() == ShapeTrimPath.Type.INDIVIDUALLY) {
                    trimPathContent = trimPathContent2;
                }
            }
        }
        if (trimPathContent != null) {
            trimPathContent.a(this);
        }
        b bVar = null;
        for (int size2 = list2.size() - 1; size2 >= 0; size2--) {
            Content content2 = list2.get(size2);
            if (content2 instanceof TrimPathContent) {
                TrimPathContent trimPathContent3 = (TrimPathContent) content2;
                if (trimPathContent3.b() == ShapeTrimPath.Type.INDIVIDUALLY) {
                    if (bVar != null) {
                        this.f.add(bVar);
                    }
                    bVar = new b(trimPathContent3);
                    trimPathContent3.a(this);
                }
            }
            if (content2 instanceof com.airbnb.lottie.animation.content.b) {
                if (bVar == null) {
                    bVar = new b(trimPathContent);
                }
                bVar.f1992a.add((com.airbnb.lottie.animation.content.b) content2);
            }
        }
        if (bVar != null) {
            this.f.add(bVar);
        }
    }
}
