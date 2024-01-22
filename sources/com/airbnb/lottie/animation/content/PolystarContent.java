package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import android.graphics.PointF;
import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.PolystarShape;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;
/* loaded from: classes.dex */
public class PolystarContent implements b, BaseKeyframeAnimation.AnimationListener, KeyPathElementContent {
    public final String b;
    public final LottieDrawable c;
    public final PolystarShape.Type d;
    public final boolean e;
    public final boolean f;
    public final BaseKeyframeAnimation<?, Float> g;
    public final BaseKeyframeAnimation<?, PointF> h;
    public final BaseKeyframeAnimation<?, Float> i;
    @Nullable
    public final BaseKeyframeAnimation<?, Float> j;
    public final BaseKeyframeAnimation<?, Float> k;
    @Nullable
    public final BaseKeyframeAnimation<?, Float> l;
    public final BaseKeyframeAnimation<?, Float> m;
    public boolean o;

    /* renamed from: a  reason: collision with root package name */
    public final Path f2000a = new Path();
    public final CompoundTrimPathContent n = new CompoundTrimPathContent();

    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f2001a;

        static {
            int[] iArr = new int[PolystarShape.Type.values().length];
            f2001a = iArr;
            try {
                iArr[PolystarShape.Type.STAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2001a[PolystarShape.Type.POLYGON.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public PolystarContent(LottieDrawable lottieDrawable, BaseLayer baseLayer, PolystarShape polystarShape) {
        this.c = lottieDrawable;
        this.b = polystarShape.getName();
        PolystarShape.Type type = polystarShape.getType();
        this.d = type;
        this.e = polystarShape.isHidden();
        this.f = polystarShape.isReversed();
        BaseKeyframeAnimation<Float, Float> createAnimation = polystarShape.getPoints().createAnimation();
        this.g = createAnimation;
        BaseKeyframeAnimation<PointF, PointF> createAnimation2 = polystarShape.getPosition().createAnimation();
        this.h = createAnimation2;
        BaseKeyframeAnimation<Float, Float> createAnimation3 = polystarShape.getRotation().createAnimation();
        this.i = createAnimation3;
        BaseKeyframeAnimation<Float, Float> createAnimation4 = polystarShape.getOuterRadius().createAnimation();
        this.k = createAnimation4;
        BaseKeyframeAnimation<Float, Float> createAnimation5 = polystarShape.getOuterRoundedness().createAnimation();
        this.m = createAnimation5;
        PolystarShape.Type type2 = PolystarShape.Type.STAR;
        if (type == type2) {
            this.j = polystarShape.getInnerRadius().createAnimation();
            this.l = polystarShape.getInnerRoundedness().createAnimation();
        } else {
            this.j = null;
            this.l = null;
        }
        baseLayer.addAnimation(createAnimation);
        baseLayer.addAnimation(createAnimation2);
        baseLayer.addAnimation(createAnimation3);
        baseLayer.addAnimation(createAnimation4);
        baseLayer.addAnimation(createAnimation5);
        if (type == type2) {
            baseLayer.addAnimation(this.j);
            baseLayer.addAnimation(this.l);
        }
        createAnimation.addUpdateListener(this);
        createAnimation2.addUpdateListener(this);
        createAnimation3.addUpdateListener(this);
        createAnimation4.addUpdateListener(this);
        createAnimation5.addUpdateListener(this);
        if (type == type2) {
            this.j.addUpdateListener(this);
            this.l.addUpdateListener(this);
        }
    }

    public final void a() {
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation;
        double d;
        double d2;
        double d3;
        int i;
        int floor = (int) Math.floor(this.g.getValue().floatValue());
        double radians = Math.toRadians((this.i == null ? 0.0d : baseKeyframeAnimation.getValue().floatValue()) - 90.0d);
        double d4 = floor;
        float floatValue = this.m.getValue().floatValue() / 100.0f;
        float floatValue2 = this.k.getValue().floatValue();
        double d5 = floatValue2;
        float cos = (float) (Math.cos(radians) * d5);
        float sin = (float) (Math.sin(radians) * d5);
        this.f2000a.moveTo(cos, sin);
        double d6 = (float) (6.283185307179586d / d4);
        double d7 = radians + d6;
        double ceil = Math.ceil(d4);
        int i2 = 0;
        while (i2 < ceil) {
            float cos2 = (float) (Math.cos(d7) * d5);
            double d8 = ceil;
            float sin2 = (float) (d5 * Math.sin(d7));
            if (floatValue != 0.0f) {
                d2 = d5;
                i = i2;
                d = d7;
                double atan2 = (float) (Math.atan2(sin, cos) - 1.5707963267948966d);
                float cos3 = (float) Math.cos(atan2);
                d3 = d6;
                double atan22 = (float) (Math.atan2(sin2, cos2) - 1.5707963267948966d);
                float f = floatValue2 * floatValue * 0.25f;
                this.f2000a.cubicTo(cos - (cos3 * f), sin - (((float) Math.sin(atan2)) * f), cos2 + (((float) Math.cos(atan22)) * f), sin2 + (f * ((float) Math.sin(atan22))), cos2, sin2);
            } else {
                d = d7;
                d2 = d5;
                d3 = d6;
                i = i2;
                this.f2000a.lineTo(cos2, sin2);
            }
            d7 = d + d3;
            i2 = i + 1;
            sin = sin2;
            cos = cos2;
            ceil = d8;
            d5 = d2;
            d6 = d3;
        }
        PointF value = this.h.getValue();
        this.f2000a.offset(value.x, value.y);
        this.f2000a.close();
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public <T> void addValueCallback(T t, @Nullable LottieValueCallback<T> lottieValueCallback) {
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation;
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation2;
        if (t == LottieProperty.POLYSTAR_POINTS) {
            this.g.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.POLYSTAR_ROTATION) {
            this.i.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.POSITION) {
            this.h.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.POLYSTAR_INNER_RADIUS && (baseKeyframeAnimation2 = this.j) != null) {
            baseKeyframeAnimation2.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.POLYSTAR_OUTER_RADIUS) {
            this.k.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.POLYSTAR_INNER_ROUNDEDNESS && (baseKeyframeAnimation = this.l) != null) {
            baseKeyframeAnimation.setValueCallback(lottieValueCallback);
        } else if (t == LottieProperty.POLYSTAR_OUTER_ROUNDEDNESS) {
            this.m.setValueCallback(lottieValueCallback);
        }
    }

    public final void b() {
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation;
        double d;
        int i;
        double d2;
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        double d3;
        float f7;
        float f8;
        float f9;
        float floatValue = this.g.getValue().floatValue();
        double radians = Math.toRadians((this.i == null ? 0.0d : baseKeyframeAnimation.getValue().floatValue()) - 90.0d);
        double d4 = floatValue;
        float f10 = (float) (6.283185307179586d / d4);
        if (this.f) {
            f10 *= -1.0f;
        }
        float f11 = f10 / 2.0f;
        float f12 = floatValue - ((int) floatValue);
        int i2 = (f12 > 0.0f ? 1 : (f12 == 0.0f ? 0 : -1));
        if (i2 != 0) {
            radians += (1.0f - f12) * f11;
        }
        float floatValue2 = this.k.getValue().floatValue();
        float floatValue3 = this.j.getValue().floatValue();
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation2 = this.l;
        float floatValue4 = baseKeyframeAnimation2 != null ? baseKeyframeAnimation2.getValue().floatValue() / 100.0f : 0.0f;
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation3 = this.m;
        float floatValue5 = baseKeyframeAnimation3 != null ? baseKeyframeAnimation3.getValue().floatValue() / 100.0f : 0.0f;
        if (i2 != 0) {
            f3 = ((floatValue2 - floatValue3) * f12) + floatValue3;
            i = i2;
            double d5 = f3;
            d = d4;
            f = (float) (d5 * Math.cos(radians));
            f2 = (float) (d5 * Math.sin(radians));
            this.f2000a.moveTo(f, f2);
            d2 = radians + ((f10 * f12) / 2.0f);
        } else {
            d = d4;
            i = i2;
            double d6 = floatValue2;
            float cos = (float) (Math.cos(radians) * d6);
            float sin = (float) (d6 * Math.sin(radians));
            this.f2000a.moveTo(cos, sin);
            d2 = radians + f11;
            f = cos;
            f2 = sin;
            f3 = 0.0f;
        }
        double ceil = Math.ceil(d) * 2.0d;
        int i3 = 0;
        boolean z = false;
        while (true) {
            double d7 = i3;
            if (d7 < ceil) {
                float f13 = z ? floatValue2 : floatValue3;
                int i4 = (f3 > 0.0f ? 1 : (f3 == 0.0f ? 0 : -1));
                if (i4 == 0 || d7 != ceil - 2.0d) {
                    f4 = f10;
                    f5 = f11;
                } else {
                    f4 = f10;
                    f5 = (f10 * f12) / 2.0f;
                }
                if (i4 == 0 || d7 != ceil - 1.0d) {
                    f6 = f11;
                    d3 = d7;
                    f7 = f13;
                } else {
                    f6 = f11;
                    d3 = d7;
                    f7 = f3;
                }
                double d8 = f7;
                double d9 = ceil;
                float cos2 = (float) (d8 * Math.cos(d2));
                float sin2 = (float) (d8 * Math.sin(d2));
                if (floatValue4 == 0.0f && floatValue5 == 0.0f) {
                    this.f2000a.lineTo(cos2, sin2);
                    f8 = floatValue4;
                    f9 = f3;
                } else {
                    f8 = floatValue4;
                    f9 = f3;
                    double atan2 = (float) (Math.atan2(f2, f) - 1.5707963267948966d);
                    float cos3 = (float) Math.cos(atan2);
                    float sin3 = (float) Math.sin(atan2);
                    double atan22 = (float) (Math.atan2(sin2, cos2) - 1.5707963267948966d);
                    float cos4 = (float) Math.cos(atan22);
                    float sin4 = (float) Math.sin(atan22);
                    float f14 = z ? f8 : floatValue5;
                    float f15 = z ? floatValue5 : f8;
                    float f16 = (z ? floatValue3 : floatValue2) * f14 * 0.47829f;
                    float f17 = cos3 * f16;
                    float f18 = f16 * sin3;
                    float f19 = (z ? floatValue2 : floatValue3) * f15 * 0.47829f;
                    float f20 = cos4 * f19;
                    float f21 = f19 * sin4;
                    if (i != 0) {
                        if (i3 == 0) {
                            f17 *= f12;
                            f18 *= f12;
                        } else if (d3 == d9 - 1.0d) {
                            f20 *= f12;
                            f21 *= f12;
                        }
                    }
                    this.f2000a.cubicTo(f - f17, f2 - f18, cos2 + f20, sin2 + f21, cos2, sin2);
                }
                d2 += f5;
                z = !z;
                i3++;
                f = cos2;
                f2 = sin2;
                floatValue4 = f8;
                f3 = f9;
                f11 = f6;
                f10 = f4;
                ceil = d9;
            } else {
                PointF value = this.h.getValue();
                this.f2000a.offset(value.x, value.y);
                this.f2000a.close();
                return;
            }
        }
    }

    public final void c() {
        this.o = false;
        this.c.invalidateSelf();
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.b;
    }

    @Override // com.airbnb.lottie.animation.content.b
    public Path getPath() {
        if (this.o) {
            return this.f2000a;
        }
        this.f2000a.reset();
        if (this.e) {
            this.o = true;
            return this.f2000a;
        }
        int i = a.f2001a[this.d.ordinal()];
        if (i == 1) {
            b();
        } else if (i == 2) {
            a();
        }
        this.f2000a.close();
        this.n.apply(this.f2000a);
        this.o = true;
        return this.f2000a;
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void onValueChanged() {
        c();
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
                    this.n.a(trimPathContent);
                    trimPathContent.a(this);
                }
            }
        }
    }
}
