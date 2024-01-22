package com.airbnb.lottie.value;

import android.graphics.PointF;
import androidx.annotation.NonNull;
import com.airbnb.lottie.utils.MiscUtils;
/* loaded from: classes.dex */
public class LottieRelativePointValueCallback extends LottieValueCallback<PointF> {
    public final PointF c;

    public LottieRelativePointValueCallback() {
        this.c = new PointF();
    }

    public PointF getOffset(LottieFrameInfo<PointF> lottieFrameInfo) {
        T t = this.value;
        if (t != 0) {
            return (PointF) t;
        }
        throw new IllegalArgumentException("You must provide a static value in the constructor , call setValue, or override getValue.");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.airbnb.lottie.value.LottieValueCallback
    public final PointF getValue(LottieFrameInfo<PointF> lottieFrameInfo) {
        this.c.set(MiscUtils.lerp(lottieFrameInfo.getStartValue().x, lottieFrameInfo.getEndValue().x, lottieFrameInfo.getInterpolatedKeyframeProgress()), MiscUtils.lerp(lottieFrameInfo.getStartValue().y, lottieFrameInfo.getEndValue().y, lottieFrameInfo.getInterpolatedKeyframeProgress()));
        PointF offset = getOffset(lottieFrameInfo);
        this.c.offset(offset.x, offset.y);
        return this.c;
    }

    public LottieRelativePointValueCallback(@NonNull PointF pointF) {
        super(pointF);
        this.c = new PointF();
    }
}
