package com.airbnb.lottie.animation.keyframe;

import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.parser.DropShadowEffect;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;
/* loaded from: classes.dex */
public class DropShadowKeyframeAnimation implements BaseKeyframeAnimation.AnimationListener {

    /* renamed from: a  reason: collision with root package name */
    public final BaseKeyframeAnimation.AnimationListener f2010a;
    public final BaseKeyframeAnimation<Integer, Integer> b;
    public final BaseKeyframeAnimation<Float, Float> c;
    public final BaseKeyframeAnimation<Float, Float> d;
    public final BaseKeyframeAnimation<Float, Float> e;
    public final BaseKeyframeAnimation<Float, Float> f;
    public boolean g = true;

    /* loaded from: classes.dex */
    public class a extends LottieValueCallback<Float> {
        public final /* synthetic */ LottieValueCallback c;

        public a(DropShadowKeyframeAnimation dropShadowKeyframeAnimation, LottieValueCallback lottieValueCallback) {
            this.c = lottieValueCallback;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.airbnb.lottie.value.LottieValueCallback
        @Nullable
        public Float getValue(LottieFrameInfo<Float> lottieFrameInfo) {
            Float f = (Float) this.c.getValue(lottieFrameInfo);
            if (f == null) {
                return null;
            }
            return Float.valueOf(f.floatValue() * 2.55f);
        }
    }

    public DropShadowKeyframeAnimation(BaseKeyframeAnimation.AnimationListener animationListener, BaseLayer baseLayer, DropShadowEffect dropShadowEffect) {
        this.f2010a = animationListener;
        BaseKeyframeAnimation<Integer, Integer> createAnimation = dropShadowEffect.getColor().createAnimation();
        this.b = createAnimation;
        createAnimation.addUpdateListener(this);
        baseLayer.addAnimation(createAnimation);
        BaseKeyframeAnimation<Float, Float> createAnimation2 = dropShadowEffect.getOpacity().createAnimation();
        this.c = createAnimation2;
        createAnimation2.addUpdateListener(this);
        baseLayer.addAnimation(createAnimation2);
        BaseKeyframeAnimation<Float, Float> createAnimation3 = dropShadowEffect.getDirection().createAnimation();
        this.d = createAnimation3;
        createAnimation3.addUpdateListener(this);
        baseLayer.addAnimation(createAnimation3);
        BaseKeyframeAnimation<Float, Float> createAnimation4 = dropShadowEffect.getDistance().createAnimation();
        this.e = createAnimation4;
        createAnimation4.addUpdateListener(this);
        baseLayer.addAnimation(createAnimation4);
        BaseKeyframeAnimation<Float, Float> createAnimation5 = dropShadowEffect.getRadius().createAnimation();
        this.f = createAnimation5;
        createAnimation5.addUpdateListener(this);
        baseLayer.addAnimation(createAnimation5);
    }

    public void applyTo(Paint paint) {
        if (this.g) {
            this.g = false;
            double floatValue = this.d.getValue().floatValue() * 0.017453292519943295d;
            float floatValue2 = this.e.getValue().floatValue();
            float sin = ((float) Math.sin(floatValue)) * floatValue2;
            float cos = ((float) Math.cos(floatValue + 3.141592653589793d)) * floatValue2;
            int intValue = this.b.getValue().intValue();
            paint.setShadowLayer(this.f.getValue().floatValue(), sin, cos, Color.argb(Math.round(this.c.getValue().floatValue()), Color.red(intValue), Color.green(intValue), Color.blue(intValue)));
        }
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void onValueChanged() {
        this.g = true;
        this.f2010a.onValueChanged();
    }

    public void setColorCallback(@Nullable LottieValueCallback<Integer> lottieValueCallback) {
        this.b.setValueCallback(lottieValueCallback);
    }

    public void setDirectionCallback(@Nullable LottieValueCallback<Float> lottieValueCallback) {
        this.d.setValueCallback(lottieValueCallback);
    }

    public void setDistanceCallback(@Nullable LottieValueCallback<Float> lottieValueCallback) {
        this.e.setValueCallback(lottieValueCallback);
    }

    public void setOpacityCallback(@Nullable LottieValueCallback<Float> lottieValueCallback) {
        if (lottieValueCallback == null) {
            this.c.setValueCallback(null);
        } else {
            this.c.setValueCallback(new a(this, lottieValueCallback));
        }
    }

    public void setRadiusCallback(@Nullable LottieValueCallback<Float> lottieValueCallback) {
        this.f.setValueCallback(lottieValueCallback);
    }
}
