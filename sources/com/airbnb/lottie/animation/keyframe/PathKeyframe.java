package com.airbnb.lottie.animation.keyframe;

import android.graphics.Path;
import android.graphics.PointF;
import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.Keyframe;
/* loaded from: classes.dex */
public class PathKeyframe extends Keyframe<PointF> {
    @Nullable
    public Path h;
    public final Keyframe<PointF> i;

    public PathKeyframe(LottieComposition lottieComposition, Keyframe<PointF> keyframe) {
        super(lottieComposition, keyframe.startValue, keyframe.endValue, keyframe.interpolator, keyframe.xInterpolator, keyframe.yInterpolator, keyframe.startFrame, keyframe.endFrame);
        this.i = keyframe;
        createPath();
    }

    @Nullable
    public Path a() {
        return this.h;
    }

    public void createPath() {
        T t;
        T t2;
        T t3 = this.endValue;
        boolean z = (t3 == 0 || (t2 = this.startValue) == 0 || !((PointF) t2).equals(((PointF) t3).x, ((PointF) t3).y)) ? false : true;
        T t4 = this.startValue;
        if (t4 == 0 || (t = this.endValue) == 0 || z) {
            return;
        }
        Keyframe<PointF> keyframe = this.i;
        this.h = Utils.createPath((PointF) t4, (PointF) t, keyframe.pathCp1, keyframe.pathCp2);
    }
}
