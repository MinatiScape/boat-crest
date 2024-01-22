package com.airbnb.lottie.animation.keyframe;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;
/* loaded from: classes.dex */
public class PathKeyframeAnimation extends a<PointF> {
    public final PointF g;
    public final float[] h;
    public final PathMeasure i;
    public PathKeyframe j;

    public PathKeyframeAnimation(List<? extends Keyframe<PointF>> list) {
        super(list);
        this.g = new PointF();
        this.h = new float[2];
        this.i = new PathMeasure();
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public /* bridge */ /* synthetic */ Object getValue(Keyframe keyframe, float f) {
        return getValue((Keyframe<PointF>) keyframe, f);
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public PointF getValue(Keyframe<PointF> keyframe, float f) {
        PointF pointF;
        PathKeyframe pathKeyframe = (PathKeyframe) keyframe;
        Path a2 = pathKeyframe.a();
        if (a2 == null) {
            return keyframe.startValue;
        }
        LottieValueCallback<A> lottieValueCallback = this.valueCallback;
        if (lottieValueCallback == 0 || (pointF = (PointF) lottieValueCallback.getValueInternal(pathKeyframe.startFrame, pathKeyframe.endFrame.floatValue(), (PointF) pathKeyframe.startValue, (PointF) pathKeyframe.endValue, b(), f, getProgress())) == null) {
            if (this.j != pathKeyframe) {
                this.i.setPath(a2, false);
                this.j = pathKeyframe;
            }
            PathMeasure pathMeasure = this.i;
            pathMeasure.getPosTan(f * pathMeasure.getLength(), this.h, null);
            PointF pointF2 = this.g;
            float[] fArr = this.h;
            pointF2.set(fArr[0], fArr[1]);
            return this.g;
        }
        return pointF;
    }
}
