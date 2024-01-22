package com.airbnb.lottie.model.animatable;

import android.graphics.PointF;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.PathKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.PointKeyframeAnimation;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;
/* loaded from: classes.dex */
public class AnimatablePathValue implements AnimatableValue<PointF, PointF> {

    /* renamed from: a  reason: collision with root package name */
    public final List<Keyframe<PointF>> f2030a;

    public AnimatablePathValue(List<Keyframe<PointF>> list) {
        this.f2030a = list;
    }

    @Override // com.airbnb.lottie.model.animatable.AnimatableValue
    public BaseKeyframeAnimation<PointF, PointF> createAnimation() {
        if (this.f2030a.get(0).isStatic()) {
            return new PointKeyframeAnimation(this.f2030a);
        }
        return new PathKeyframeAnimation(this.f2030a);
    }

    @Override // com.airbnb.lottie.model.animatable.AnimatableValue
    public List<Keyframe<PointF>> getKeyframes() {
        return this.f2030a;
    }

    @Override // com.airbnb.lottie.model.animatable.AnimatableValue
    public boolean isStatic() {
        return this.f2030a.size() == 1 && this.f2030a.get(0).isStatic();
    }
}
