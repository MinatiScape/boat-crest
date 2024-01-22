package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.model.DocumentData;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;
/* loaded from: classes.dex */
public class TextKeyframeAnimation extends com.airbnb.lottie.animation.keyframe.a<DocumentData> {

    /* loaded from: classes.dex */
    public class a extends LottieValueCallback<DocumentData> {
        public final /* synthetic */ LottieFrameInfo c;
        public final /* synthetic */ LottieValueCallback d;
        public final /* synthetic */ DocumentData e;

        public a(TextKeyframeAnimation textKeyframeAnimation, LottieFrameInfo lottieFrameInfo, LottieValueCallback lottieValueCallback, DocumentData documentData) {
            this.c = lottieFrameInfo;
            this.d = lottieValueCallback;
            this.e = documentData;
        }

        @Override // com.airbnb.lottie.value.LottieValueCallback
        /* renamed from: a */
        public DocumentData getValue(LottieFrameInfo<DocumentData> lottieFrameInfo) {
            this.c.set(lottieFrameInfo.getStartFrame(), lottieFrameInfo.getEndFrame(), lottieFrameInfo.getStartValue().text, lottieFrameInfo.getEndValue().text, lottieFrameInfo.getLinearKeyframeProgress(), lottieFrameInfo.getInterpolatedKeyframeProgress(), lottieFrameInfo.getOverallProgress());
            String str = (String) this.d.getValue(this.c);
            DocumentData endValue = lottieFrameInfo.getInterpolatedKeyframeProgress() == 1.0f ? lottieFrameInfo.getEndValue() : lottieFrameInfo.getStartValue();
            this.e.set(str, endValue.fontName, endValue.size, endValue.justification, endValue.tracking, endValue.lineHeight, endValue.baselineShift, endValue.color, endValue.strokeColor, endValue.strokeWidth, endValue.strokeOverFill, endValue.boxPosition, endValue.boxSize);
            return this.e;
        }
    }

    public TextKeyframeAnimation(List<Keyframe<DocumentData>> list) {
        super(list);
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    /* renamed from: e */
    public DocumentData getValue(Keyframe<DocumentData> keyframe, float f) {
        DocumentData documentData;
        LottieValueCallback<A> lottieValueCallback = this.valueCallback;
        if (lottieValueCallback == 0) {
            if (f == 1.0f && (documentData = keyframe.endValue) != null) {
                return documentData;
            }
            return keyframe.startValue;
        }
        float f2 = keyframe.startFrame;
        Float f3 = keyframe.endFrame;
        float floatValue = f3 == null ? Float.MAX_VALUE : f3.floatValue();
        DocumentData documentData2 = keyframe.startValue;
        DocumentData documentData3 = documentData2;
        DocumentData documentData4 = keyframe.endValue;
        return (DocumentData) lottieValueCallback.getValueInternal(f2, floatValue, documentData3, documentData4 == null ? documentData2 : documentData4, f, getInterpolatedCurrentKeyframeProgress(), getProgress());
    }

    public void setStringValueCallback(LottieValueCallback<String> lottieValueCallback) {
        super.setValueCallback(new a(this, new LottieFrameInfo(), lottieValueCallback, new DocumentData()));
    }
}
