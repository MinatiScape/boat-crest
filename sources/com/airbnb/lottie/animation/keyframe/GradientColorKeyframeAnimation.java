package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;
/* loaded from: classes.dex */
public class GradientColorKeyframeAnimation extends a<GradientColor> {
    public final GradientColor g;

    public GradientColorKeyframeAnimation(List<Keyframe<GradientColor>> list) {
        super(list);
        GradientColor gradientColor = list.get(0).startValue;
        int size = gradientColor != null ? gradientColor.getSize() : 0;
        this.g = new GradientColor(new float[size], new int[size]);
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    /* renamed from: e */
    public GradientColor getValue(Keyframe<GradientColor> keyframe, float f) {
        this.g.lerp(keyframe.startValue, keyframe.endValue, f);
        return this.g;
    }
}
